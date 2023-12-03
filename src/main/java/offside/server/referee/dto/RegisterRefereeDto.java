package offside.server.referee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RegisterRefereeDto {
    @NotBlank(message = "location이 비어있습니다.")
    @Size(max = 255)
    public String location;

    @NotBlank(message = "contactPhone이 비어있습니다.")
    @Size(max = 255)
    public String contactPhone;

    @NotBlank(message = "name이 비어있습니다.")
    @Size(max = 255)
    public String name;

    @NotBlank(message = "comment가 비어있습니다.")
    @Size(max = 255)
    public String comment;

    @NotNull(message = "price가 비어있습니다.")
    public Integer price;

    @NotBlank(message = "image가 비어있습니다.")
    @Size(max = 255)
    public String image;

    public List<String> availableTime;
    public RegisterRefereeDto(){}

    public RegisterRefereeDto(String location, String contactPhone, String name, String comment, Integer price, String image) {
        this.location = location;
        this.contactPhone = contactPhone;
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.image = image;
    }
}
