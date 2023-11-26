package offside.server.stadium.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
            FieldError fieldError = bindingResult.getFieldError();
            throw new IllegalArgumentException(fieldError.getDefaultMessage());
        }
        stadiumService.validateLocation(stadiumData.location);
        
        return stadiumService.registerStadium(stadiumData);
    }
    
    // Stadium 목록 요청 (with 장소, 사람(전화번호))
    @GetMapping("/stadium")
    @ResponseBody
    public List<Stadium> requestStadium(@RequestParam("location") String location, @RequestParam("contact_phone") String contact_number){
        // 서비스단에 해당 조건에 맞는 stadium을 달라고 요청
        return stadiumService.requestStadium(location,contact_number);
    }
    
    // Stadium의 상세 정보 요청
    
    
    // Stadium 삭제 요청
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleNotFoundException(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
