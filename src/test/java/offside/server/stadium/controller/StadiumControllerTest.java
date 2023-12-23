package offside.server.stadium.controller;

import jakarta.transaction.Transactional;
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
@Transactional
class StadiumControllerTest {
    private StadiumService stadiumService;

    @Autowired
    public StadiumControllerTest(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @Test
    void 구장_등록하기() {
        final var stadium1 = new StadiumDto("마포구","0231539874","서울특별시 산악문화체험센터>난지천인조잔디축구장","서울시 마포구 신수동 12-32","1. 공공시설 예약서비스 이용시 필수 준수사항모든 서비스의 이용은 담당 기관의 규정에 따릅니다. 각 시설의 규정 및 허가조건을 반드시 준수하여야 합니다",50000,"https://yeyak.seoul.go.kr/web/common/file/FileDown.do?file_id=1617239932085QQ2HH7Q6BFUUKKLU129S2081E","");
        final var stadium2 = new StadiumDto("마포구","0231539875","2023년 농구장1-망원한강공원","망원한강공원>농구장(1,2)","1. 공공시설 예약서비스 이용시 필수 준수사항모든 서비스의 이용은 담당 기관의 규정에 따릅니다. 각 시설의 규정 및 허가조건을 반드시 준수하여야 합니다",30000,"https://yeyak.seoul.go.kr/web/common/file/FileDown.do?file_id=16726384080191167IF8ED1YE52CJTBDVFA92K","");
        final var stadium3 = new StadiumDto("영등포구","0231539876","2024년 배구장2-양화한강공원","양화한강공원>배구장(1,2)","1. 공공시설 예약서비스 이용시 필수 준수사항모든 서비스의 이용은 담당 기관의 규정에 따릅니다. 각 시설의 규정 및 허가조건을 반드시 준수하여야 합니다",20000,"https://yeyak.seoul.go.kr/web/reservation/selectReservView.do?rsv_svc_id=S231102162929228428","");
        
        final var newStadium1 = stadiumService.registerStadium(stadium1);
        final var newStadium2 = stadiumService.registerStadium(stadium2);
        final var newStadium3 = stadiumService.registerStadium(stadium3);
        
        final var stadiumInfo1 = stadiumService.getStadiumInfo(newStadium1.getId());
        final var stadiumInfo2 = stadiumService.getStadiumInfo(newStadium2.getId());
        final var stadiumInfo3 = stadiumService.getStadiumInfo(newStadium3.getId());
        
        assertThat(stadium1.name).isEqualTo(stadiumInfo1.getName());
        assertThat(stadium2.address).isEqualTo(newStadium2.getAddress());
        assertThat(stadium3.image).isEqualTo(newStadium3.getImage());
        assertThat(stadium1.price).isEqualTo(newStadium1.getPrice());
        assertThat(stadium2.externalUrl).isEqualTo(stadiumInfo2.getExternalUrl());
        assertThat(stadium3.name).isEqualTo(stadiumInfo3.getName());
    }
}