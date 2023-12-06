package offside.server.stadium.service;

import offside.server.util.service.UtilService;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import jakarta.transaction.Transactional;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.StadiumDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
class StadiumServiceTest {
    private final StadiumService stadiumService;
    private final UtilService utilService;
    
    @Autowired
    public StadiumServiceTest(StadiumService stadiumService, UtilService utilService) {
        this.stadiumService = stadiumService;
        this.utilService = utilService;
    }
    
    @Test
    void 구장_등록() {
        // given
        StadiumDto stadiumDto1 = new StadiumDto("마포구","01011112222", "마포구 생활 공원",
            "마포구 신수동 23-12", "상태 최고", 30000, "http://localhost:8080/test");
        StadiumDto stadiumDto2 = new StadiumDto("마포구","01033334444", "마포구 축구 공원",
            "마포구 대흥동 12-34", "잔디 굿", 50000, "http://localhost:8080/test");
    
        // when
        Stadium createdStadium1 = stadiumService.registerStadium(stadiumDto1);
        Stadium createdStadium2 = stadiumService.registerStadium(stadiumDto2);
        
        // then
        assertThat(createdStadium1.getName()).isEqualTo(stadiumDto1.getName());
        assertThat(createdStadium2.getComment()).isEqualTo(stadiumDto2.getComment());
    }

    @Test
    void 날짜가_string_으로_변하는지() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231203");
    }
}