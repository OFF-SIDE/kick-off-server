package offside.server.referee.domain;

import jakarta.persistence.*;
import offside.server.referee.dto.RegisterRefereeDto;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class Referee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String location;
    @Column(unique = true)
    private String contactPhone;
    private String name;
    private String comment;
    private Integer price;

    private String image;
    public Referee(){}
    public Referee(RegisterRefereeDto refereeData){
        this.location = refereeData.location;
        this.contactPhone = refereeData.contactPhone;
        this.name = refereeData.name;
        this.comment = refereeData.comment;
        this.price = refereeData.price;
        this.image = refereeData.image;
    }

    public Referee(String location, String contactPhone, String name, String comment, Integer price, String image){
        this.location = location;
        this.contactPhone = contactPhone;
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.image = image;
    }

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
}