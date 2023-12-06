package offside.server.referee.repository;

import jakarta.transaction.Transactional;
import offside.server.referee.domain.RefereeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RefereeReservationRepository extends JpaRepository<RefereeReservation, Integer> {
    public List<RefereeReservation> findAllByRefereeIdAndDate(Integer refereeId, String date);
    public Optional<RefereeReservation> findByRefereeIdAndDateAndTime(Integer refereeId, String date, String time);
}
