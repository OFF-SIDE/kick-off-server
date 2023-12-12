package offside.server.stadium.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class StadiumDto {
    @NotBlank
    @Size(max = 255)
    public String location;
    
    @NotBlank(message = "contact_number is required")
    @Size(max = 16)
    @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자만 적어주세요")
    public String contactPhone;
    
    @NotBlank(message = "Name is required")
    @NotNull(message = "Name is not null")
    @Size(max = 255)
    public String name;
    
    @NotBlank
    @Size(max = 255)
    public String address;
    
    @NotBlank
    @Size(max = 255)
    public String comment;

    @NotNull
    public Integer price;

    public String image;
    
    public String externalUrl;
    
    public StadiumDto() {}

    public StadiumDto(StadiumDto stadiumData) {
        this.location = stadiumData.location;
        this.contactPhone = stadiumData.contactPhone;
        this.name = stadiumData.name;
        this.address = stadiumData.address;
        this.comment = stadiumData.comment;
        this.price = stadiumData.price;
        this.image = stadiumData.image;
        this.externalUrl = stadiumData.externalUrl;
    }
    
    public StadiumDto(String location, String contactPhone, String name, String address,
        String comment, Integer price, String image, String externalUrl) {
        this.location = location;
        this.contactPhone = contactPhone;
        this.name = name;
        this.address = address;
        this.comment = comment;
        this.price = price;
        this.image = image;
        this.externalUrl = externalUrl;
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