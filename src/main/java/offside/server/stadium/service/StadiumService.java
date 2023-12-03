package offside.server.stadium.service;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import offside.server.stadium.domain.Reservation;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.ReservationDto;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.dto.StadiumInfoDto;
import offside.server.stadium.repository.ReservationRepository;
import offside.server.stadium.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StadiumService {
    private final StadiumRepository stadiumRepository;
    private final ReservationRepository reservationRepository;
    private final List<String> defaultAvailableTime = new ArrayList<>(Arrays.asList("1000","1100","1200","1300","1400","1500","1600","1700","1800","1900","2000","2100","2200"));

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository, ReservationRepository reservationRepository) {
        this.stadiumRepository = stadiumRepository;
        this.reservationRepository = reservationRepository;
    }
    
    public Stadium registerStadium(StadiumDto stadiumData){
        // stadium 등록
        Stadium stadium = new Stadium(stadiumData);
        return stadiumRepository.save(stadium);
    }
    
    public List<Stadium> requestStadium(String location, String contact_phone){
        // if 문으로 다 쪼개서 서로 다른 repo 메소드를 호출
        if (location.isEmpty() && !contact_phone.isEmpty()) {
            return stadiumRepository.findAllByContactPhone(contact_phone);
        } else if (!location.isEmpty() && contact_phone.isEmpty()) {
            return stadiumRepository.findAllByLocation(location);
        } else if (location.isEmpty()) { // 둘 다 비었을 때, (contact_phone.isEmpty() 는 이미 true)
            return stadiumRepository.findAll();
        }else{
            return stadiumRepository.findAllByLocationAndContactPhone(location, contact_phone);
        }
    }

    public StadiumInfoDto getStadiumInfo(Integer stadiumId, String date) throws IllegalArgumentException{
        // 1. 구장 id 로 구장 객체 가져오기
        final var stadium = stadiumRepository.findById(stadiumId);
        if(stadium.isEmpty()){
            throw new IllegalArgumentException("해당 구장이 없습니다.");
        }
        StadiumDto stadiumData = stadium.get().toStadiumDto();

        // 2. reservation 테이블에서 해당 구장 +예 약 date를 넣어서 1, 231205 ===> 13:00, 15:00 -> 12:00, 14:00, 16:00~~
        // 10:00 ~ 22:00 (1시간 단위)
        final var availableTime = this.getStadiumReservationList(stadiumId, date);

        return new StadiumInfoDto(stadiumData, availableTime);
    }
    
    public void validateLocation(String location) throws IllegalArgumentException {
        final var availableLocationList = Arrays.asList("마포구","서대문구","영등포구","강남구");
        if(!availableLocationList.contains(location)){
            throw new IllegalArgumentException("해당 위치 "+location+"은 등록될 수 없습니다.");
        }
    }

    public Reservation stadiumReservation(ReservationDto reservationData){

        // 1. 해당 시간에 예약이 있는가
        final var reservation = reservationRepository.findByStadiumIdAndDateAndTime(reservationData.stadiumId,reservationData.date,reservationData.time);

        // 2-0. 있으면 오류
        if(reservation.isPresent()){
            throw new IllegalStateException("해당 시간엔 이미 예약이 있습니다.");
        }
        else{ // 2-1. 없으면 추가
            final var newReservation = new Reservation(reservationData);
            return reservationRepository.save(newReservation);
        }
    }

    public List<String> getStadiumReservationList(Integer stadiumId, String date) {
        final var reservationList = reservationRepository.findAllByStadiumIdAndDate(stadiumId,date);
        final var availableTime = new ArrayList<>(defaultAvailableTime);
        reservationList.forEach(reservation -> {
            availableTime.remove(reservation.getTime());
        });
        return availableTime;
    }
}