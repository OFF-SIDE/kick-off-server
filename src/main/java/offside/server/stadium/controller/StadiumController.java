package offside.server.stadium.controller;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import offside.server.notification.service.NotificationService;
import offside.server.stadium.domain.Matching;
import offside.server.stadium.domain.Reservation;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.MatchingDto;
import offside.server.stadium.dto.MatchingReservationDto;
import offside.server.stadium.dto.ReservationAndStadiumDto;
import offside.server.stadium.dto.ReservationDto;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.dto.StadiumInfoDto;
import offside.server.stadium.dto.StadiumReservationInfoDto;
import offside.server.stadium.service.StadiumCrawlerService;
import offside.server.stadium.service.StadiumService;
import offside.server.util.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StadiumController {
    private final StadiumService stadiumService;
    private final UtilService utilService;
    private final StadiumCrawlerService stadiumCrawlerService;
    private final NotificationService notificationService;

    @Autowired
    public StadiumController(StadiumService stadiumService, UtilService utilService,StadiumCrawlerService stadiumCrawlerService,NotificationService notificationService) {
        this.stadiumService = stadiumService;
        this.utilService = utilService;
        this.stadiumCrawlerService = stadiumCrawlerService;
        this.notificationService = notificationService;
    }
    @GetMapping("/home")
    public String HomePage(Model model) {
        
        return "SearchHomePage";
    }
    
    @GetMapping("/search")
    public String StadiumSearchWeb(Model model, @RequestParam("location") String location) {
        if (location.isEmpty() || location.equals("전체"))
            location = "";
        var tmpStadiumList = stadiumService.requestStadium(location,"");
        if (tmpStadiumList.size() > 30)
            tmpStadiumList = tmpStadiumList.subList(0,30);
        
        final var stadiumList = tmpStadiumList.stream().filter(stadium -> stadium.getExternalUrl().length() > 5).toList();
        model.addAttribute("stadiumList", stadiumList);
        model.addAttribute("location", location.equals("") ? "전체" : location);
        model.addAttribute("outputCnt", stadiumList.size());
        return "StadiumSearchPage";
    }

    // Stadium 등록 요청
    @PostMapping("/stadium")
    @ResponseBody
    public Stadium registerStadium(@RequestBody @Valid StadiumDto stadiumData, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        if(stadiumData.externalUrl == null)
            stadiumData.externalUrl = "";
        
        final var stadium = stadiumService.registerStadium(stadiumData);
        notificationService.createNotification(stadiumData.contactPhone,"구장 등록","요청하신 '"+ stadium.getName() +"' 구장의 등록이 완료되었습니다.");
        return stadium;
    }
    
    // Stadium 목록 요청 (with 장소, 사람(전화번호))
    @GetMapping("/stadium")
    @ResponseBody
    public List<Stadium> requestStadium(@RequestParam("location") String location, @RequestParam("contactPhone") String contact_number){
        // 서비스단에 해당 조건에 맞는 stadium을 달라고 요청
        final var stadiumList = stadiumService.requestStadium(location,contact_number);
        Collections.reverse(stadiumList);
        if(stadiumList.size() > 30){
            return stadiumList.subList(0,30);
        } else{
            return stadiumList;
        }
    }
    
    // Stadium의 상세 정보 요청 -> 특정 구장을 클릭했을 경우
    @GetMapping("/stadium/{stadiumId}")
    @ResponseBody
    public StadiumInfoDto requestStadiumInfo(@PathVariable("stadiumId") Integer stadiumId){
        if(stadiumId == null)
            throw new IllegalArgumentException("stadiumId가 주어지지 않았습니다");
        return stadiumService.getStadiumInfo(stadiumId);
    }
    
    // Stadium 예약하기
    @PostMapping("/stadium/reservation")
    @ResponseBody
    public Reservation stadiumReservation(@RequestBody @Valid ReservationDto reservationData, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
    
        final var reservation = stadiumService.stadiumReservation(reservationData);
        final var stadium = stadiumService.getStadiumInfo(reservation.getStadiumId());
        notificationService.createNotification(reservationData.userPhone,"구장 예약","요청하신 '" + stadium.getName()+"' 구장의 예약이 완료되었습니다. 예약 날짜 :"+reservation.getDate() + " 예약 시간 :" + reservation.getTime());
        return reservation;
    }

    // Stadium 예약 현황 보기 ---> 불가능한 시간을 return
    @GetMapping("/stadium/reservation")
    @ResponseBody
    public StadiumReservationInfoDto requestStadiumReservation(@RequestParam("stadiumId") Integer stadiumId, @RequestParam("date") String date){
        if(stadiumId == null)
            throw new IllegalArgumentException("stadiumId가 주어지지 않았습니다");
        if(date == null || date == "")
            date = utilService.getDateFromToday();
        
        return stadiumService.getStadiumReservationList(stadiumId, date);
    }

    //구장 측에서 보는 예약자 정보
    @GetMapping("/stadium/reservation/user")
    @ResponseBody
    public Reservation requestListOfReservationInfo(@RequestParam("stadiumId") Integer stadiumId,  @RequestParam("date") String date, @RequestParam("time")String time){
        if(stadiumId == null)
            throw new IllegalArgumentException("stadiumId가 주어지지 않았습니다");
        if(date == null || date == "")
            date = utilService.getDateFromToday();
        if(time == null)
            throw new IllegalArgumentException("time이 주어지지 않았습니다");
        
        return stadiumService.requestListOfReservationInfo(stadiumId,date,time);
    }
    
    // 매칭 등록
    @PostMapping("/stadium/matching")
    @ResponseBody
    public MatchingReservationDto matchingReservation(@RequestBody @Valid MatchingDto matchingData, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        final var matchingReservation = stadiumService.matchingReservation(matchingData);
        if(matchingReservation.isMatching){
            // 매칭이 완료
            final var stadium = stadiumService.getStadiumInfo(matchingReservation.matchingData.getStadiumId());
            notificationService.createNotification(matchingData.contactPhone,"매칭 대기 완료","요청하신 '" + stadium.getName()+"' 구장에서의 매칭 등록이 완료되었습니다. 등록 날짜 :"+matchingReservation.matchingData.getDate() + " 등록 시간 :" + matchingReservation.matchingData.getTime());
        } else{
            // 예약이 완료
            final var stadium = stadiumService.getStadiumInfo(matchingReservation.reservationData.getStadiumId());
            notificationService.createNotification(matchingData.contactPhone,"매칭 완료","요청하신 '" + stadium.getName()+"' 구장에서의 예약이 완료되었습니다. 예약 날짜 :"+matchingReservation.reservationData.getDate() + " 예약 시간 :" + matchingReservation.reservationData.getTime());
        }
        return matchingReservation;
    }
    
    @GetMapping("/stadium/matching")
    @ResponseBody
    public Matching getMatchingInfo(@RequestParam("stadiumId") Integer stadiumId,  @RequestParam("date") String date, @RequestParam("time")String time){
        if(stadiumId == null)
            throw new IllegalArgumentException("stadiumId가 주어지지 않았습니다");
        if(date == null || date == "")
            date = utilService.getDateFromToday();
        if(time == null)
            throw new IllegalArgumentException("time이 주어지지 않았습니다");
        
        return stadiumService.getMatchingInfo(stadiumId, date, time);
    }
    
    
    // 내 예약 관리 페이지 보기
    @GetMapping("/stadium/myReservation")
    @ResponseBody
    public List<ReservationAndStadiumDto> requestMyReservationInfo(@RequestParam("userPhone") String userPhone){
        if(userPhone == null)
            throw new IllegalArgumentException("userPhone이 주어지지 않았습니다");
        
        return stadiumService.requestMyReservationInfo(userPhone);
    }
    
    @GetMapping("/stadium/apitest")
    @ResponseBody
    public String apiTest() throws IOException {
        stadiumCrawlerService.testAPI();
        return "hihi";
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
    
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIllegalStateException(IOException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
}
