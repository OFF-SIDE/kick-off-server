package offside.offside.server.repository;

import offside.offside.server.domain.Stadium;

public interface StadiumRepositoryInterface {
    Stadium save(Stadium stadium);
    Stadium findById(Integer stadium_id);
    void deleteStadiumById(Integer stadium_id);
}
