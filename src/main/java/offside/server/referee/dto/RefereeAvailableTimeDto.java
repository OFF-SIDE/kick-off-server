package offside.server.referee.dto;

import java.util.List;

public class RefereeAvailableTimeDto {
    public List<String> availableTime;
    
    public RefereeAvailableTimeDto() {
    }
    
    public RefereeAvailableTimeDto(List<String> availableTime) {
        this.availableTime = availableTime;
    }
    
    public List<String> getAvailableTime() {
        return availableTime;
    }
    
    public void setAvailableTime(List<String> availableTime) {
        this.availableTime = availableTime;
    }
}
