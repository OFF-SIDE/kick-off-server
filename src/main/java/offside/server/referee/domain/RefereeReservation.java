package offside.server.referee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RefereeReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer refereeId;
    private String date;

    public RefereeReservation(){}

    public RefereeReservation(Integer refereeId, String date, String time, String userPhone, String userName) {
        this.refereeId = refereeId;
        this.date = date;
        this.time = time;
        this.userPhone = userPhone;
        this.userName = userName;
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

    private String time;
    private String userPhone;
    private String userName;


}
