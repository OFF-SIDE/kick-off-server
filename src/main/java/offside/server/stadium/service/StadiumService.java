package offside.server.stadium.service;

import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StadiumService {
    private final StadiumRepository stadiumRepository;
    
    @Autowired
    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }
    
    public Stadium registerStadium(StadiumDto stadiumData){
        // stadium 등록
        Stadium stadium = new Stadium(stadiumData);
        return stadiumRepository.save(stadium);
    }
    
    public List<Stadium> requestStadium(String location, String contact_phone){
        // if 문으로 다 쪼개서 서로 다른 repo 메소드를 호출
        if (location == "" && contact_phone != "") {
            return stadiumRepository.findByContactPhone(contact_phone);
        } else if (location != "" && contact_phone == "") {
            return stadiumRepository.findByLocation(location);
        } else{
            return stadiumRepository.findByBoth(location, contact_phone);
        }
    }
    
    public void validateLocation(String location) throws IllegalArgumentException {
        List<String> availableLocationList = Arrays.asList("마포구","서대문구","영등포구","강남구");
        if(!availableLocationList.contains(location)){
            throw new IllegalArgumentException("해당 위치 "+location+"은 등록될 수 없습니다.");
        }
    }
    
}