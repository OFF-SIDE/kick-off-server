package offside.server.stadium.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MatchingDto {
    
    @NotNull(message = "stadiumId 가 필요합니다")
    public Integer stadiumId;
    
    @NotBlank(message = "comment is required")
    @Size(max = 255)
    public String comment;
    
    @NotBlank(message = "userName is required")
    @Size(max = 255)
    public String userName;
    
    @NotBlank(message = "contactPhone is required")
    @Size(max = 255)
    public String contactPhone;
    
    @NotBlank(message = "date is required")
    @Size(max = 6)
    public String date;
    
    @NotBlank(message = "time is required")
    @Size(max = 4)
    public String time;
    
    public MatchingDto() {}
    
    public MatchingDto(Integer stadiumId, String comment, String userName, String contactPhone,
        String date, String time) {
        this.stadiumId = stadiumId;
        this.comment = comment;
        this.userName = userName;
        this.contactPhone = contactPhone;
        this.date = date;
        this.time = time;
    }
}
