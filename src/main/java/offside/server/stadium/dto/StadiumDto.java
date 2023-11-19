package offside.server.stadium.dto;

import jakarta.validation.constraints.Max;
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
    public String contact_phone;
    
    @NotBlank(message = "Name is required")
    @Size(max = 255)
    public String name;
    
    @NotBlank
    @Size(max = 255)
    public String address;
    
    @NotBlank
    @Size(max = 255)
    public String comment;
    
    @NotNull
    @Size(max = 255)
    public String image;
    
    public StadiumDto() {}
    
    public StadiumDto(String location, String contact_phone, String name, String address,
        String comment, String image) {
        this.location = location;
        this.contact_phone = contact_phone;
        this.name = name;
        this.address = address;
        this.comment = comment;
        this.image = image;
    }
}