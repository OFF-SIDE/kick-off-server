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
    private Integer id;
    private Integer stadiumId;
    private String date;
    private String time;
    private String userName;
    private String userPhone;

    public Reservation() {
    }
    public Reservation(ReservationDto reservationData) {
        stadiumId = reservationData.stadiumId;
        date = reservationData.date;
        time = reservationData.time;
        userName = reservationData.userName;
        userPhone = reservationData.userPhone;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getStadiumId() {
        return stadiumId;
    }
    
    public void setStadiumId(Integer stadiumId) {
        this.stadiumId = stadiumId;
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
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserPhone() {
        return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
