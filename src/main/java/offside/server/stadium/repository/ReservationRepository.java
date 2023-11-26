package offside.server.stadium.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import offside.server.stadium.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ReservationRepository implements ReservationRepositoryInterface{
    private final EntityManager em;

    @Autowired
    public ReservationRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Reservation save(Reservation reservation){
        em.persist(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(Integer reservation_id){
        return Optional.ofNullable(em.find(Reservation.class, reservation_id));
    }

    @Override
    public Optional<Reservation> findByDateAndTime(Integer stadium_id, String date, String time){
        return Optional.ofNullable(em.createQuery("select m from Reservation as m where m.stadium_id = :stadium_id and m.date = :date and m.time = :time",Reservation.class)
                .setParameter("stadium_id", stadium_id)
                .setParameter("date",date)
                .setParameter("time",time)
                .getSingleResult());
    }

    @Override
    public List<Reservation> findByContactPhone(String user_phone){
        return em.createQuery("select m from Reservation as m where m.contact_phone = :contact_phone", Reservation.class)
                .setParameter("contact_phone", user_phone)
                .getResultList();

    }
    @Override
    public List<Reservation> findReservationListByStadiumIdAndDate(Integer stadium_id, String date){
        return em.createQuery("select m from Reservation as m where m.stadium_id = :stadium_id and m.date = :date", Reservation.class)
                .setParameter("stadium_id", stadium_id)
                .setParameter("date",date).
                getResultList();
    }
}
