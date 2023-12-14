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
    void 구장_해당날짜_상세보기() {}
}