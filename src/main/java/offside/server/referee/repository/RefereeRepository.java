package offside.server.referee.repository;

import jakarta.transaction.Transactional;
import offside.server.referee.domain.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RefereeRepository extends JpaRepository<Referee, Integer> {

}
