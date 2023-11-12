package offside.offside.server.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import offside.offside.server.domain.Stadium;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class StadiumRepository implements StadiumRepositoryInterface {
    
    private final EntityManager em;
    
    public StadiumRepository(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Stadium save(Stadium stadium) {
        em.persist(stadium);
        return stadium;
    }
    
    @Override
    public Stadium findById(Integer stadium_id) {
        Stadium stadium = em.find(Stadium.class, stadium_id);
        return stadium;
    }
    
    @Override
    public void deleteStadiumById(Integer stadium_id) {
    
    }
}
