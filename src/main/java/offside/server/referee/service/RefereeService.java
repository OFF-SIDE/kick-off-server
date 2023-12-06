package offside.server.referee.service;

import jakarta.transaction.Transactional;
import offside.server.referee.domain.Referee;
import offside.server.referee.domain.RefereeAvailableTime;
import offside.server.referee.domain.RefereeReservation;
import offside.server.referee.dto.RegisterRefereeDto;
import offside.server.referee.dto.ReservationRefereeDto;
import offside.server.referee.repository.RefereeAvailableTimeRepository;
import offside.server.referee.repository.RefereeRepository;
import offside.server.referee.repository.RefereeReservationRepository;
import offside.server.util.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RefereeService {
    private final RefereeAvailableTimeRepository refereeAvailableTimeRepository;
    private final RefereeRepository refereeRepository;
    private final RefereeReservationRepository refereeReservationRepository;
    private final UtilService utilService;

    @Autowired
    public RefereeService(RefereeRepository refereeRepository, RefereeAvailableTimeRepository refereeAvailableTimeRepository, RefereeReservationRepository refereeReservationRepository,UtilService utilService) {
        this.refereeRepository = refereeRepository;
        this.refereeAvailableTimeRepository = refereeAvailableTimeRepository;
        this.refereeReservationRepository = refereeReservationRepository;
        this.utilService = utilService;
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
                final var result = availableReferee.add(referee);
            }
        });
        return availableReferee;
    }

    public List<String> findAvailableTimes(Integer refereeId, String date) {
        // 1. 해당 referee가 본인이 예약 가능하다고 말한 시간대 불러오기
        var availableTime = refereeAvailableTimeRepository.findAllByRefereeId(refereeId);
        var availableTimeList = availableTime.stream().map(RefereeAvailableTime::getTime).toList();
        // 2. date날에 예약된 시간 대 불러오기
        var reservedTime = refereeReservationRepository.findAllByRefereeIdAndDate(refereeId, date);
        var reservedTimeList = reservedTime.stream().map(RefereeReservation::getTime).toList();
        // 3. return (1 - 2)
        return availableTimeList.stream().filter(avt -> !reservedTimeList.contains(avt)).toList();
    }

    // referee 예약
    public RefereeReservation reservationReferee(ReservationRefereeDto reservationRefereeData) {
        // 해당 시간에 예약이 있는 지
        final var reservation = refereeReservationRepository.findByRefereeIdAndDateAndTime(reservationRefereeData.refereeId, reservationRefereeData.date, reservationRefereeData.time);
        if(reservation.isPresent()){
            throw new IllegalStateException("해당 시간에 이미 심판 예약이 있습니다. 다른 시간에 예약을 해주세요");
        } else{
            final var newReservation = new RefereeReservation(reservationRefereeData);
            return refereeReservationRepository.save(newReservation);
        }
    }
}



