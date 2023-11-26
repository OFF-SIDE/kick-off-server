package offside.server.stadium.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReservationDto {
    @NotBlank
    public Integer stadium_id;
    @NotBlank
    @Size(max = 6)
    public String date;

    @NotBlank
    @Size(max=4)
    public String time;

    @NotBlank
    @Size(max = 255)
    public String user_name;

    @NotBlank
    @Size(max = 255)
    public String user_phone;

    public ReservationDto(){}
}
