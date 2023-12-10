package offside.server.stadium.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import offside.server.stadium.dto.MatchingDto;

@Entity
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer stadiumId;
    private String comment;
    private String date;
    private String time;
    private String userName;
    private String contactPhone;
    
    public Matching() {}
    
    public Matching(MatchingDto matchingData) {
        this.stadiumId = matchingData.stadiumId;
        this.comment = matchingData.comment;
        this.date = matchingData.date;
        this.time = matchingData.time;
        this.userName = matchingData.userName;
        this.contactPhone = matchingData.contactPhone;
    }
    
    public Matching(Integer stadiumId, String comment, String date, String time, String userName, String contactPhone){
        this.stadiumId = stadiumId;
        this.comment = comment;
        this.date = date;
        this.time = time;
        this.userName = userName;
        this.contactPhone = contactPhone;
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
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
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
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
