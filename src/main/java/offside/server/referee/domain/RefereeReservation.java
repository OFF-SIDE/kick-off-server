package offside.server.referee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import offside.server.referee.dto.ReservationRefereeDto;

@Entity
public class RefereeReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer refereeId;
    private String date;
    private String time;
    private String userPhone;
    private String userName;
    private String comment;

    public RefereeReservation(){}

    public RefereeReservation(Integer refereeId, String date, String time, String userPhone, String userName, String comment) {
        this.refereeId = refereeId;
        this.date = date;
        this.time = time;
        this.userPhone = userPhone;
        this.userName = userName;
        this.comment = comment;
    }

    public RefereeReservation(ReservationRefereeDto reservationRefereeData) {
        this.refereeId = reservationRefereeData.refereeId;
        this.date = reservationRefereeData.date;
        this.time = reservationRefereeData.time;
        this.userPhone = reservationRefereeData.userPhone;
        this.userName = reservationRefereeData.userName;
        this.comment = reservationRefereeData.comment;
    }

    public Integer getRefereeId(){
        return refereeId;
    }

    public void setRefereeId(Integer refereeId) {
        this.refereeId = refereeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
