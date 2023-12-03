package offside.server.stadium.controller;

import jakarta.validation.Valid;
import java.util.List;

import offside.server.stadium.domain.Reservation;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.ReservationDto;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.dto.StadiumInfoDto;
import offside.server.stadium.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class StadiumController {
    private final StadiumService stadiumService;

    @Autowired
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    // Stadium 등록 요청
    @PostMapping("/stadium")
    @ResponseBody
    public Stadium registerStadium(@RequestBody @Valid StadiumDto stadiumData, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        stadiumService.validateLocation(stadiumData.location);
        
        return stadiumService.registerStadium(stadiumData);
    }
    
    // Stadium 목록 요청 (with 장소, 사람(전화번호))
    @GetMapping("/stadium")
    @ResponseBody
    public List<Stadium> requestStadium(@RequestParam("location") String location, @RequestParam("contactPhone") String contact_number){
        // 서비스단에 해당 조건에 맞는 stadium을 달라고 요청
        return stadiumService.requestStadium(location,contact_number);
    }
    
    // Stadium의 상세 정보 요청 -> 특정 구장을 클릭했을 경우
    @GetMapping("/stadium/{stadiumId}")
    @ResponseBody
    public StadiumInfoDto requestStadiumInfo(@PathVariable("stadiumId") Integer stadiumId, @RequestParam("date") String date){
        return stadiumService.getStadiumInfo(stadiumId, date);
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

    // Stadium 예약 현황 보기
    @GetMapping("/stadium/reservation")
    @ResponseBody
    public List<String> requestStadiumReservation(@RequestParam("stadiumId") Integer stadiumId, @RequestParam("date") String date){
        return stadiumService.getStadiumReservationList(stadiumId, date);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
}
