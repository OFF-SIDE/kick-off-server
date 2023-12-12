package offside.server.stadium.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import offside.server.stadium.dto.StadiumDto;

@Entity
public class Stadium{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String location;
    private String contactPhone;
    private String name;
    private String address;
    
    @Column(length = 2047)
    private String comment;
    private Integer price;
    private String image;
    private String externalUrl;

    public Stadium(StadiumDto stadiumData){
        location = stadiumData.location;
        contactPhone = stadiumData.contactPhone;
        name = stadiumData.name;
        address = stadiumData.address;
        comment = stadiumData.comment;
        price = stadiumData.price;
        image = stadiumData.image;
        externalUrl = stadiumData.externalUrl;
    }
    
    public Stadium() {}
    
    public Stadium(String location, String contactPhone, String name, String address,
        String comment, Integer price, String image,String externalUrl) {
        this.location = location;
        this.contactPhone = contactPhone;
        this.name = name;
        this.address = address;
        this.comment = comment;
        this.price = price;
        this.image = image;
        this.externalUrl = externalUrl;
    }
    
    public StadiumDto toStadiumDto(){
        return new StadiumDto(location,contactPhone,name,address,comment,price,image,externalUrl);
    };
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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
    
    public Integer getPrice() {
        return price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getExternalUrl() {
        return externalUrl;
    }
    
    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }
}