package offside.server.stadium.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import offside.server.stadium.dto.ReservationDto;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservation_id;
    private Integer stadium_id;
    private String date;
    private String time;
    private String user_name;
    private String user_phone;

    public Reservation() {
    }
    public Reservation(ReservationDto reservationData) {
        stadium_id = reservationData.stadium_id;
        date = reservationData.date;
        time = reservationData.time;
        user_name = reservationData.user_name;
        user_phone = reservationData.user_phone;
    }


    public Integer getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Integer reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Integer getStadium_id() {
        return stadium_id;
    }

    public void setStadium_id(Integer stadium_id) {
        this.stadium_id = stadium_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
