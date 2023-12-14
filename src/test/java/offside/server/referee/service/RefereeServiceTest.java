package offside.server.referee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import offside.server.util.service.UtilService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RefereeServiceTest {
    private final UtilService utilService;
    @Autowired
    public RefereeServiceTest(UtilService utilService) {
        this.utilService = utilService;
    }
    
    @Test
    void 심판_등록() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 심판_예약가능시간_불러오기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 심판_목록_불러오기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 심판_이용가능한_시간_불러오기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 심판_예약하기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
    
    @Test
    void 심판_상세정보_불러오기() {
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
        assertThat(utilService.getDateFromToday()).isEqualTo("231214");
    }
}