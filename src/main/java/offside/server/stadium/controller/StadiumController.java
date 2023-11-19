package offside.server.stadium.controller;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
//        if(bindingResult.hasErrors()){
//            return bindingResult.getFieldError().getDefaultMessage();
//        }
//
//        List<String> availableLocationList = Arrays.asList("마포구","서대문구","영등포구","강남구");
//        if(!availableLocationList.contains(stadiumData.location)){
//            return "잘못된 주소입니다";
//        }
        
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
    

}
