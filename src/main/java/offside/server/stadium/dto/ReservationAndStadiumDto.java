package offside.server.stadium.dto;

import offside.server.stadium.domain.Reservation;
import offside.server.stadium.domain.Stadium;

public class ReservationAndStadiumDto {
    public Stadium stadium;
    public String date;
    public String time;
    public String userName;
    public String userPhone;
    
    public ReservationAndStadiumDto() {}
    
    public ReservationAndStadiumDto(Reservation reservation, Stadium stadium) {
        this.stadium = stadium;
        this.date = reservation.getDate();
        this.time = reservation.getTime();
        this.userName = reservation.getUserName();
        this.userPhone = reservation.getUserPhone();
    }
    
    public ReservationAndStadiumDto(Stadium stadium, String date, String time, String userName,
        String userPhone) {
        this.stadium = stadium;
        this.date = date;
        this.time = time;
        this.userName = userName;
        this.userPhone = userPhone;
    }
}
