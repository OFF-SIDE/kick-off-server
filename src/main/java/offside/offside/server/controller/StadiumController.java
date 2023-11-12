package offside.offside.server.controller;

import offside.offside.server.domain.Stadium;
import offside.offside.server.dto.StadiumDto;
import offside.offside.server.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StadiumController {
    private final StadiumRepository stadiumRepository;
    
    @Autowired
    public StadiumController(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }
    
    // GET kickoff.com/stadium
    @GetMapping("/stadium")
    @ResponseBody
    public String getStadium(@RequestParam("id") Integer stadium_id) {
        System.out.println(stadium_id);
        Stadium stadium = stadiumRepository.findById(stadium_id);
        if(stadium == null) return "해당 유저가 없습니다";
        else
            return stadium.getName();
    }
    
    // POST kickoff.com/stadium
    @PostMapping("stadium")
    @ResponseBody
    public Stadium createStadium(@RequestBody StadiumDto stadiumData) {
        Stadium stadium = new Stadium(stadiumData);
        return stadiumRepository.save(stadium);
    }
}
