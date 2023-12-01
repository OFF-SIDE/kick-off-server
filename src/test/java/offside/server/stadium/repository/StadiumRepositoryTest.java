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
        // given
        Stadium stadium1 = new Stadium(new StadiumDto("마포구","01038665979","서강축구장","신수동","굿굿",15000,""));
        Stadium stadium2 = new Stadium(new StadiumDto("마포구","01028994421","서강대후문운동장","마포구 대흥동","잔디 좋아요",30000,""));

        // when
        stadiumRepository.save(stadium1);
        stadiumRepository.save(stadium2);

        // then
        Stadium result1 = stadiumRepository.findById(stadium1.getId()).get();
        Stadium result2 = stadiumRepository.findById(stadium2.getId()).get();

        assertThat(stadium1).isEqualTo(result1);
        assertThat(stadium2).isEqualTo(result2);
    }

    @Test
    void 구장_검색() {
    }

    @Test
    void 구장_장소로_검색() {
        // given
        String searchLocation = "마포구";
        Stadium stadium1 = new Stadium(new StadiumDto(searchLocation,"01038665979","서강축구장","마포구 신수동","굿굿",15000,""));
        Stadium stadium2 = new Stadium(new StadiumDto(searchLocation,"01028994421","서강대후문운동장","마포구 대흥동","잔디 좋아요",77000,""));
        Stadium stadium3 = new Stadium(new StadiumDto("영등포구","010994421","서강대정문운동장","영등포구 당산동","잔디 안좋아요",23000,""));
        List<Stadium> searchStadiumList = Arrays.asList(stadium1,stadium2);

        // when
        stadiumRepository.save(stadium1);
        stadiumRepository.save(stadium2);
        stadiumRepository.save(stadium3);
        List<Stadium> stadiumList = stadiumRepository.findAllByLocation(searchLocation);

        // then
        Assertions.assertArrayEquals(new List[]{stadiumList}, new List[]{searchStadiumList});
    }

    @Test
    void 구장_id로_삭제() {
        // given
        Stadium stadium1 = new Stadium(new StadiumDto("마포구","01038665979","서강축구장","신수동","굿굿",50000,""));
        Stadium stadium2 = new Stadium(new StadiumDto("마포구","01028994421","서강대후문운동장","마포구 대흥동","잔디 좋아요",15000,""));
        stadiumRepository.save(stadium1);
        stadiumRepository.save(stadium2);

        // when
        stadiumRepository.deleteById(stadium1.getId());
        Optional<Stadium> notFoundStadium = stadiumRepository.findById(stadium1.getId());
        Optional<Stadium> findStadium = stadiumRepository.findById(stadium2.getId());

        // then
        assertThat(findStadium.get()).isEqualTo(stadium2);
        if(notFoundStadium.isPresent()){
            throw new IllegalStateException();
        }
    }
}