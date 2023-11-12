package offside.offside.server.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import offside.offside.server.dto.StadiumDto;

@Entity
public class Stadium{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stadium_id;
    private String location;
    private String contact_phone;
    private String name;
    private String address;
    private String comment;
    private String image;
    public Stadium(StadiumDto stadiumData){
        location = stadiumData.location;
        contact_phone = stadiumData.contact_phone;
        name = stadiumData.name;
        address = stadiumData.address;
        comment = stadiumData.comment;
        image = stadiumData.image;
    }
    
    public Stadium() {
    }
    
    public int getStadium_id() {
        return stadium_id;
    }
    
    public void setStadium_id(int stadium_id) {
        this.stadium_id = stadium_id;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getContact_phone() {
        return contact_phone;
    }
    
    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
}