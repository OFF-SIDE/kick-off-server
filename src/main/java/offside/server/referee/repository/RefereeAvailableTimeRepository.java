package offside.server.referee.repository;

import jakarta.transaction.Transactional;
import offside.server.referee.domain.RefereeAvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface RefereeAvailableTimeRepository extends JpaRepository<RefereeAvailableTime, Integer> {
    public List<RefereeAvailableTime> findAllByRefereeId(Integer refereeId);
}
