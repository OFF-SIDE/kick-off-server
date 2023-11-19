package offside.server.stadium.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import offside.server.stadium.domain.Stadium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StadiumRepository implements StadiumRepositoryInterface {
    
    private final EntityManager em;

    @Autowired
    public StadiumRepository(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Stadium save(Stadium stadium) {
        em.persist(stadium);
        return stadium;
    }
    
    @Override
    public Optional<Stadium> findById(Integer stadium_id) {
        return Optional.ofNullable(em.find(Stadium.class, stadium_id));
    }
    
    @Override
    public List<Stadium> findByLocation(String location) {
        return em.createQuery("select m from Stadium as m where m.location = :location", Stadium.class)
            .setParameter("location", location)
            .getResultList();
    }
    
    @Override
    public List<Stadium> findByContactPhone(String contact_phone) {
        return em.createQuery("select m from Stadium as m where m.contact_phone = :contact_phone", Stadium.class)
            .setParameter("contact_phone", contact_phone)
            .getResultList();
    }
    
    @Override
    public List<Stadium> findByBoth(String location, String contact_phone) {
        
        return em.createQuery("select m from Stadium as m where m.location = :location and m.contact_phone = :contact_phone", Stadium.class)
            .setParameter("contact_phone", contact_phone).setParameter("location",location)
            .getResultList();
    }
    
    
    
    @Override
    public void deleteStadiumById(Integer stadium_id) {
        Optional<Stadium> stadium = findById(stadium_id);
        if(stadium.isPresent()){
            em.remove(stadium.get());
        }
    }
}
