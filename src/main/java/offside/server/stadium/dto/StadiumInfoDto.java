package offside.server.stadium.dto;

import java.util.List;

public class StadiumInfoDto extends StadiumDto{
    private List<String> availableTime;

    public List<String> getAvailableTime() {
        return availableTime;
    }

    public StadiumInfoDto(StadiumDto stadiumData, List<String> _availableTime) {
        super(stadiumData);
        availableTime = _availableTime;
    }
}

