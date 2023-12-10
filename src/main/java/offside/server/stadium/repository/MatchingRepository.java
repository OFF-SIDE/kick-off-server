package offside.server.stadium.repository;

import java.util.List;
import java.util.Optional;
import offside.server.stadium.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingRepository extends JpaRepository<Matching, Integer> {
    
    Optional<Matching> findByStadiumIdAndDateAndTime(Integer stadiumId, String date, String time);
    List<Matching> findAllByStadiumIdAndDate(Integer stadiumId, String date);
}
