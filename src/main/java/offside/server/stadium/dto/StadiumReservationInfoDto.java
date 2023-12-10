package offside.server.stadium.dto;

import java.util.List;

public class StadiumReservationInfoDto {
    public List<String> reservationList;
    public List<String> matchingQ;
    
    public StadiumReservationInfoDto() {}
    
    public StadiumReservationInfoDto(List<String> reservationList, List<String> matchingQ) {
        this.reservationList = reservationList;
        this.matchingQ = matchingQ;
    }
}
