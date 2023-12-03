package offside.server.stadium.repository;

import java.util.List;
import java.util.Optional;
import offside.server.stadium.domain.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
    List<Stadium> findAllByLocation(String location);
    List<Stadium> findAllByContactPhone(String location);
    List<Stadium> findAllByLocationAndContactPhone(String location, String contactPhone);
    void deleteById(Integer stadiumId);
}
