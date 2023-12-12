package offside.server.stadium.controller;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class StadiumController {
    private final StadiumService stadiumService;
    private final UtilService utilService;
    private final StadiumCrawlerService stadiumCrawlerService;

    @Autowired
    public StadiumController(StadiumService stadiumService, UtilService utilService,StadiumCrawlerService stadiumCrawlerService) {
        this.stadiumService = stadiumService;
        this.utilService = utilService;
        this.stadiumCrawlerService = stadiumCrawlerService;
    }

    // Stadium 등록 요청
    @PostMapping("/stadium")
    @ResponseBody
    public Stadium registerStadium(@RequestBody @Valid StadiumDto stadiumData, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        
        return stadiumService.registerStadium(stadiumData);
    }
    
    // Stadium 목록 요청 (with 장소, 사람(전화번호))
    @GetMapping("/stadium")
    @ResponseBody
    public List<Stadium> requestStadium(@RequestParam("location") String location, @RequestParam("contactPhone") String contact_number){
        // 서비스단에 해당 조건에 맞는 stadium을 달라고 요청
        final var stadiumList = stadiumService.requestStadium(location,contact_number);
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
        return stadiumService.stadiumReservation(reservationData);
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
    public List<Reservation> requestListOfReservationInfo(@RequestParam("stadiumId") Integer stadiumId,  @RequestParam("date") String date, @RequestParam("time")String time){
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
        return stadiumService.matchingReservation(matchingData);
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
