package offside.server.stadium.dto;

import offside.server.stadium.domain.Matching;
import offside.server.stadium.domain.Reservation;

public class MatchingReservationDto {
    public boolean isMatching;
    public Matching matchingData;
    public Reservation reservationData;
    
    public MatchingReservationDto() {}
    
    public MatchingReservationDto(Matching matchingData) {
        isMatching = true;
        this.matchingData = matchingData;
    }
    
    public MatchingReservationDto(Reservation reservationData) {
        isMatching = false;
        this.reservationData = reservationData;
    }
}
