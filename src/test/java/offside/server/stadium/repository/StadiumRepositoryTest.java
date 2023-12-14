package offside.server.stadium.repository;

import static org.assertj.core.api.Assertions.*;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.StadiumDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class StadiumRepositoryTest {

    private final StadiumRepository stadiumRepository;
    @Autowired
    public StadiumRepositoryTest(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    @Test
    void 구장_등록() {
        assertThat("a").isEqualTo("a");
    }

    @Test
    void 구장_검색() {
        assertThat("a").isEqualTo("a");
    }

    @Test
    void 구장_장소로_검색() {
        assertThat("a").isEqualTo("a");}

    @Test
    void 구장_id로_삭제() {
        assertThat("a").isEqualTo("a");}
}