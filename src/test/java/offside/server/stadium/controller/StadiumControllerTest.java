package offside.server.stadium.controller;

import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.ReservationDto;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.dto.StadiumInfoDto;
import offside.server.stadium.service.StadiumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class StadiumControllerTest {
    private StadiumService stadiumService;

    @Autowired
    public StadiumControllerTest(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @Test
    void 구장_해당날짜_상세보기() {
        // given

        StadiumDto stadiumdata1 = new StadiumDto("마포구","01038665979","서강대","신수동1-1","잔디좋습니다",15000,"asd");
        Stadium stadium1 = stadiumService.registerStadium(stadiumdata1);

        // when
        Integer stadium1_id = stadium1.getId();
        ReservationDto reservationData1 = new ReservationDto(stadium1_id, "231129","1300","강성준","0102899");
        ReservationDto reservationData2 = new ReservationDto(stadium1_id, "231129","1700","정한샘","0104421");
        ReservationDto reservationData3 = new ReservationDto(stadium1_id, "231130","1200","이창민","0103866");


        stadiumService.stadiumReservation(reservationData1);
        stadiumService.stadiumReservation(reservationData2);

        // then
        List<String> expectedAvailableTime = Arrays.asList("1000","1100","1200","1400","1500","1600","1800","1900","2000","2100","2200");
        StadiumInfoDto stadiumInfo = stadiumService.getStadiumInfo(stadium1_id, "231129");
        assertThat(stadiumInfo.getAvailableTime()).isEqualTo(expectedAvailableTime);
    }
}