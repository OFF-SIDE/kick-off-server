package offside.server.stadium.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReservationDto {
    @NotBlank
    public Integer stadiumId;
    @NotBlank
    @Size(max = 6)
    public String date;

    @NotBlank
    @Size(max=4)
    public String time;

    @NotBlank
    @Size(max = 255)
    public String userName;

    @NotBlank
    @Size(max = 255)
    public String userPhone;

    public ReservationDto(){}

    public ReservationDto(Integer stadium_id, String date, String time, String user_name, String user_phone){
        this.stadiumId = stadium_id;
        this.date = date;
        this.time = time;
        this.userName = user_name;
        this.userPhone = user_phone;
    }
}
