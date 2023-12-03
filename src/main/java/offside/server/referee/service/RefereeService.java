package offside.server.referee.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import offside.server.referee.domain.Referee;
import offside.server.referee.domain.RefereeAvailableTime;
import offside.server.referee.dto.RegisterRefereeDto;
import offside.server.referee.repository.RefereeAvailableTimeRepository;
import offside.server.referee.repository.RefereeRepository;
import offside.server.referee.repository.RefereeReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class RefereeService {
    private final RefereeAvailableTimeRepository refereeAvailableTimeRepository;
    private final RefereeRepository refereeRepository;
    private final RefereeReservationRepository refereeReservationRepository;

    @Autowired
    public RefereeService(RefereeRepository refereeRepository, RefereeAvailableTimeRepository refereeAvailableTimeRepository, RefereeReservationRepository refereeReservationRepository) {
        this.refereeRepository = refereeRepository;
        this.refereeAvailableTimeRepository = refereeAvailableTimeRepository;
        this.refereeReservationRepository = refereeReservationRepository;
    }

    public Referee registerReferee(RegisterRefereeDto refereeData){
        // 심판 등록
        final var referee = new Referee(refereeData);
        try{
            final var newReferee = refereeRepository.save(referee);
            this.setRefereeAvailableTime(newReferee.getId(), refereeData.availableTime);
            return newReferee;
        }catch (Exception e){
            throw new IllegalArgumentException("같은 전화번호의 유저를 등록할 수 없습니다.");
        }
    }

    public boolean setRefereeAvailableTime(Integer refereeId, List<String> availableTime){
        if(availableTime == null){
           return false;
        }else{
            availableTime.forEach(avt -> {
                final var refereeAvailableTime = new RefereeAvailableTime(refereeId,avt);
                refereeAvailableTimeRepository.save(refereeAvailableTime);
            });
            return true;
        }
    }

    public List<Referee> findAllRefereeByLocationAndDate(String location, String date) {
        // 0. availableTime 에서 해당 date, location에 가능한 목록 가져오기
        final var refereeList = refereeRepository.findAllByLocation(location);
        final var availableReferee = new ArrayList<Referee>();
        refereeList.forEach(referee ->
        {
            var availableTime = refereeAvailableTimeRepository.findAllByRefereeId(referee.getId());
            var reservedTime = refereeReservationRepository.findAllByRefereeIdAndDate(referee.getId(),date);
            if (availableTime.size() - reservedTime.size() > 0){
                availableReferee.add(referee);
            }
        });
        return availableReferee;
    }
}



