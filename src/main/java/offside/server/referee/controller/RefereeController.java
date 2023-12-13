package offside.server.referee.controller;

import jakarta.validation.Valid;
import offside.server.notification.service.NotificationService;
import offside.server.referee.domain.Referee;
import offside.server.referee.domain.RefereeReservation;
import offside.server.referee.dto.RefereeAvailableTimeDto;
import offside.server.referee.dto.RegisterRefereeDto;
import offside.server.referee.dto.ReservationRefereeDto;
import offside.server.referee.service.RefereeService;
import offside.server.stadium.service.StadiumService;
import offside.server.util.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RefereeController {
    private final RefereeService refereeService;
    private final StadiumService stadiumService;
    private final UtilService utilService;
    private final NotificationService notificationService;

    @Autowired
    public RefereeController(RefereeService refereeService,StadiumService stadiumService,UtilService utilService,NotificationService notificationService) {
        this.refereeService = refereeService;
        this.stadiumService = stadiumService;
        this.utilService = utilService;
        this.notificationService = notificationService;
    }

    // 심판 등록
    @PostMapping("referee")
    @ResponseBody
    public Referee registerReferee(@RequestBody @Valid RegisterRefereeDto refereeData, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        final var referee = refereeService.registerReferee(refereeData);
        notificationService.createNotification(refereeData.contactPhone,"심판 등록","요청하신 '" + referee.getName()+"' 심판의 등록이 완료되었습니다.");
        return referee;
    }
    
    @GetMapping("/referee/{refereeId}")
    @ResponseBody
    public Referee getRefereeInfo(@PathVariable("refereeId") Integer refereeId){
        if(refereeId == null)
            throw new IllegalArgumentException("refereeId 가 비어 있습니다.");
        return refereeService.getRefereeInfo(refereeId);
    }

    // 심판 목록 가져오기 (by location)
    @GetMapping("referee")
    @ResponseBody
    public List<Referee> findRefereeByLocation(@RequestParam("date") String date){
        if(date == null || date == "")
            date = utilService.getDateFromToday();
        return refereeService.findRefereeListByDate(date);
    }

    // 하나의 심판 예약 가능 시간 보기 (by Date)
    @GetMapping("referee/reservation")
    @ResponseBody
    public RefereeAvailableTimeDto getRefereeAvailableTimes(@RequestParam("refereeId") Integer refereeId, @RequestParam("date") String date){
        if(date == null || date == "")
            date = utilService.getDateFromToday();
        utilService.validateDate(date);
        return refereeService.findAvailableTimes(refereeId, date);
    }

    // 심판 예약
    @PostMapping("referee/reservation")
    @ResponseBody
    public RefereeReservation reservationReferee(@RequestBody @Valid ReservationRefereeDto reservationRefereeData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        final var refereeReservation = refereeService.reservationReferee(reservationRefereeData);
        notificationService.createNotification(refereeReservation.getUserPhone(),"심판 예약","요청하신 '" + refereeReservation.getUserName()+"' 심판의 예약이 완료되었습니다. 예약 날짜 :"+refereeReservation.getDate() + " 예약 시간 :" + refereeReservation.getTime());
    
        return refereeReservation;
    }

    // Error Handler
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
}
