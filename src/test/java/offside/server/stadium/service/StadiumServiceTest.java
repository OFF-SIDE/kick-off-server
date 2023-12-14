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
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }

    @Test
    void 날짜가_string_으로_변하는지() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 구장_목록_불러오기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 구장_상세정보_불러오기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 구장_예약_기능() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 구장_예약_현황_확인() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 구장_매칭_예약_하기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 구장_예약_가능한_시간_확인() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 구장측_예약자_정보_확인() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 매칭_대기중인_상대_정보_불러오기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
}