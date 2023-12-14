package offside.server.stadium.service;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import offside.server.notification.service.NotificationService;
import offside.server.stadium.domain.Matching;
import offside.server.stadium.domain.Reservation;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.MatchingDto;
import offside.server.stadium.dto.MatchingReservationDto;
import offside.server.stadium.dto.ReservationAndStadiumDto;
import offside.server.stadium.dto.ReservationDto;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.dto.StadiumInfoDto;
import offside.server.stadium.dto.StadiumReservationInfoDto;
import offside.server.stadium.repository.MatchingRepository;
import offside.server.stadium.repository.ReservationRepository;
import offside.server.stadium.repository.StadiumRepository;
import offside.server.util.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StadiumService {
    private final StadiumRepository stadiumRepository;
    private final ReservationRepository reservationRepository;
    private final UtilService utilService;
    private final MatchingRepository matchingRepository;
    private final NotificationService notificationService;
    private final List<String> defaultAvailableTime = new ArrayList<>(Arrays.asList("1000","1100","1200","1300","1400","1500","1600","1700","1800","1900","2000","2100","2200"));

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository, ReservationRepository reservationRepository,UtilService utilService, MatchingRepository matchingRepository, NotificationService notificationService) {
        this.stadiumRepository = stadiumRepository;
        this.reservationRepository = reservationRepository;
        this.utilService = utilService;
        this.matchingRepository = matchingRepository;
        this.notificationService = notificationService;
    }
    
    public Stadium registerStadium(StadiumDto stadiumData){
        // stadium 등록
        Stadium stadium = new Stadium(stadiumData);
        return stadiumRepository.save(stadium);
    }
    
    public List<Stadium> requestStadium(String location, String contactPhone){
        // if 문으로 다 쪼개서 서로 다른 repo 메소드를 호출
        if (location.isEmpty() && !contactPhone.isEmpty()) {
            return stadiumRepository.findAllByContactPhone(contactPhone);
        } else if (!location.isEmpty() && contactPhone.isEmpty()) {
            return stadiumRepository.findAllByLocation(location);
        } else if (location.isEmpty()) { // 둘 다 비었을 때, (contact_phone.isEmpty() 는 이미 true)
            return stadiumRepository.findAll();
        }else{
            return stadiumRepository.findAllByLocationAndContactPhone(location, contactPhone);
        }
    }

    public StadiumInfoDto getStadiumInfo(Integer stadiumId) throws IllegalArgumentException{
        // 1. 구장 id 로 구장 객체 가져오기
        final var stadium = stadiumRepository.findById(stadiumId);
        if(stadium.isEmpty()){
            throw new IllegalArgumentException("해당 구장이 없습니다.");
        }
        StadiumDto stadiumData = stadium.get().toStadiumDto();

        // 2. reservation 테이블에서 해당 구장 +예 약 date를 넣어서 1, 231205 ===> 13:00, 15:00 -> 12:00, 14:00, 16:00~~
        // 10:00 ~ 22:00 (1시간 단위)
        final var date = utilService.getDateFromToday();
        final var reservationList = getStadiumReservationList(stadiumId, date).reservationList;
        final var matchingQ = matchingRepository.findAllByStadiumIdAndDate(stadiumId,date).stream().map(Matching::getTime).toList();

        return new StadiumInfoDto(stadiumData, reservationList, matchingQ);
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

    /* date 날짜 기준으로 해당 stadium의 예약 가능한 날짜를 구함 ( 전체 날짜 - 예약된 날짜) */
    public StadiumReservationInfoDto getStadiumReservationList(Integer stadiumId, String date) {
        final var reservationList = reservationRepository.findAllByStadiumIdAndDate(stadiumId,date).stream().map(Reservation::getTime).toList();
        final var matchingQ = matchingRepository.findAllByStadiumIdAndDate(stadiumId,date).stream().map(Matching::getTime).toList();
        
        return new StadiumReservationInfoDto(reservationList,matchingQ);
    }
    
    public MatchingReservationDto matchingReservation(MatchingDto matchingData){
        //1. 해당 시간에 예약이 있는가
        final var reservation = reservationRepository.findByStadiumIdAndDateAndTime(matchingData.stadiumId, matchingData.date, matchingData.time);
        
        // 에약이 있으면 오류
        if(reservation.isPresent())
            throw new IllegalStateException("해당 시간에 이미 예약이 있어 매칭 대기를 할 수 없습니다");
        
    
        //2. 예약이 없다면 해당 시간에 매칭대기가 있는가
        final var matching = matchingRepository.findByStadiumIdAndDateAndTime(matchingData.stadiumId, matchingData.date, matchingData.time);
        if(matching.isPresent()){
            //3. 매칭 대기가 있으면 stadiumReservation 으로 실제 예약
            final var presentMatching = matching.get();
            Reservation reservation1 = new Reservation(presentMatching.getStadiumId(), presentMatching.getDate(), presentMatching.getTime(), presentMatching.getUserName(), presentMatching.getContactPhone());
            Reservation reservation2 = new Reservation(matchingData.stadiumId, matchingData.date,matchingData.time,matchingData.userName,matchingData.contactPhone);
            
            reservationRepository.save(reservation1);
            reservationRepository.save(reservation2);
            
            final var stadium = this.getStadiumInfo(reservation1.getStadiumId());
            notificationService.createNotification(reservation1.getUserPhone(),"매칭/예약 완료","요청하신 '" + stadium.getName()+"' 구장에서의 매칭이 완료되어 예약 되었습니다. 예약 날짜 :"+reservation1.getDate() + " 예약 시간 :" + reservation1.getTime());
            notificationService.createNotification(reservation2.getUserPhone(),"매칭/예약 완료","요청하신 '" + stadium.getName()+"' 구장에서의 매칭이 완료되어 예약 되었습니다. 예약 날짜 :"+reservation2.getDate() + " 예약 시간 :" + reservation2.getTime());
    
            matchingRepository.delete(matching.get());
            
            return new MatchingReservationDto(reservation2);
        }else{
            //4. 매칭 대기가 없으면 매칭대기를 올려놓음(matchingQ 에 등록)
            final var newMatching = new Matching(matchingData);
            return new MatchingReservationDto(matchingRepository.save(newMatching));
        }
    }
    
    public Reservation requestListOfReservationInfo(Integer stadiumId, String date, String time) {
        final var reservation =  reservationRepository.findAllByStadiumIdAndDateAndTime(stadiumId, date,time);
        if(reservation.isEmpty()){
            throw new IllegalStateException("해당하는 예약자가 없습니다.");
        }
        return reservation.get(0);
    }
    
    public List<ReservationAndStadiumDto> requestMyReservationInfo(String userPhone) {
        // Reservation 정보를 가져오기
        final var reservationList = reservationRepository.findAllByUserPhoneOrderByDateDescTimeDesc(userPhone);
        
        return reservationList.stream().map(reservation -> {
            final var stadium = stadiumRepository.findById(reservation.getStadiumId());
            return new ReservationAndStadiumDto(reservation, stadium.get());
        }).toList();
    }
    
    public Matching getMatchingInfo(Integer stadiumId, String date, String time) {
        // matching 정보 가져오기
        final var matchingInfo = matchingRepository.findByStadiumIdAndDateAndTime(stadiumId, date, time);
        if(matchingInfo.isEmpty()){
            throw new IllegalStateException("현재 해당하는 조건에 매칭 대기중인 사람이 없습니다.");
        }
        return matchingInfo.get();
    }
}