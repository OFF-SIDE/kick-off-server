package offside.server.stadium.dto;

import java.util.List;

public class StadiumInfoDto extends StadiumDto{
    private List<String> reservationList;
    private List<String> matchingQ;

    public List<String> getReservationList() {
        
        return reservationList;
    }

    public StadiumInfoDto(StadiumDto stadiumData, List<String> reservationList, List<String> matchingQ) {
        super(stadiumData);
        this.reservationList = reservationList;
        this.matchingQ = matchingQ;
    }
    
    
}

