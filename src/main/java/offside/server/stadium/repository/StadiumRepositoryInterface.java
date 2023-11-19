package offside.server.stadium.repository;

import java.util.List;
import java.util.Optional;
import offside.server.stadium.domain.Stadium;

public interface StadiumRepositoryInterface {
    Stadium save(Stadium stadium);
    Optional<Stadium> findById(Integer stadium_id);
    List<Stadium> findByLocation(String location);
    List<Stadium> findByContactPhone(String location);
    List<Stadium> findByBoth(String location, String contact_phone);
    void deleteStadiumById(Integer stadium_id);
}

