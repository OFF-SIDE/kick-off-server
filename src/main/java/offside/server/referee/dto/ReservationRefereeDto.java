package offside.server.referee.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReservationRefereeDto {
    @NotNull(message = "refereeId가 비어 있습니다")
    public Integer refereeId;

    @NotBlank(message = "date가 비어 있습니다")
    @Size(max = 255)
    public String date;

    @NotBlank(message = "time이 비어 있습니다 ")
    @Size(max = 255)
    public String time;

    @NotBlank(message = "userPhone이 비어 있습니다.")
    @Size(max = 255)
    public String userPhone;

    @NotBlank(message = "userName이 비어 있습니다.")
    @Size(max = 255)
    public String userName;

    @NotBlank(message = "comment가 비어 있습니다.")
    @Size(max = 255)
    public String comment;

    public ReservationRefereeDto() {}

    public ReservationRefereeDto(String date, String time, String userPhone, String userName, String comment) {
        this.date = date;
        this.time = time;
        this.userPhone = userPhone;
        this.userName = userName;
        this.comment = comment;
    }
}
