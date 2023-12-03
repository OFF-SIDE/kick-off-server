package offside.server.stadium.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import offside.server.stadium.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByStadiumIdAndDate(Integer stadium_id, String date);
    Optional<Reservation> findByStadiumIdAndDateAndTime(Integer stadium_id, String date, String time);
}
