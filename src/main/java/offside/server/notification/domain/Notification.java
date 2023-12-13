package offside.server.notification.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String contactPhone;
    private String title; // "구장 등록","예약","매칭","구인"
    private String message;
    @CreatedDate
    private LocalDateTime createdTime;
    
    public Notification() {
    }
    
    public Notification(String contactPhone, String title,
        String message) {
        this.contactPhone = contactPhone;
        this.title = title;
        this.message = message;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String type) {
        this.title = type;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}





