package offside.server.stadium.repository;

import offside.server.stadium.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryInterface {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Integer reservation_id);
    List<Reservation> findByStadiumIdAndDate(Integer stadium_id, String date);
    List<Reservation> findByContactPhone(String location);
    List<Reservation> findReservationListByStadiumIdAndDate(Integer stadium_id, String date);
    Optional<Reservation> findByDateAndTime(Integer stadium_id, String date, String time);

}