package offside.server.referee.controller;

import jakarta.validation.Valid;
import offside.server.referee.domain.Referee;
import offside.server.referee.dto.RegisterRefereeDto;
import offside.server.referee.service.RefereeService;
import offside.server.stadium.service.StadiumService;
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

    @Autowired
    public RefereeController(RefereeService refereeService,StadiumService stadiumService) {
        this.refereeService = refereeService;
        this.stadiumService = stadiumService;
    }

    // 심판 등록
    @PostMapping("referee")
    @ResponseBody
    public Referee registerReferee(@RequestBody @Valid RegisterRefereeDto refereeData, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        return refereeService.registerReferee(refereeData);
    }

    // 심판 목록 가져오기 (by location)
    @GetMapping("referee")
    @ResponseBody
    public List<Referee> findRefereeByLocation(@RequestParam("location") String location, @RequestParam("date") String date){
        if(location == null)
            throw new IllegalArgumentException("stadiumId가 주어지지 않았습니다");
        stadiumService.validateLocation(location);
        if(date == null)
            date = stadiumService.getDateFromToday();

        return refereeService.findAllRefereeByLocationAndDate(location,date);
    }



    // 심판 예약 가능 시간 보기 (by Date)




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
