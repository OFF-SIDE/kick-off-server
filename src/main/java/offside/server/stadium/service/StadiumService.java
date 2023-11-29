package offside.server.stadium.service;

import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import offside.server.file.service.FileService;
import offside.server.stadium.domain.Reservation;
import offside.server.stadium.domain.Stadium;
import offside.server.stadium.dto.ReservationDto;
import offside.server.stadium.dto.StadiumDto;
import offside.server.stadium.repository.ReservationRepository;
import offside.server.stadium.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class StadiumService {
    private final FileService fileService;
    private final StadiumRepository stadiumRepository;
    private final ReservationRepository reservationRepository;
    
    @Autowired
    public StadiumService(StadiumRepository stadiumRepository, ReservationRepository reservationRepository, FileService fileService) {
        this.stadiumRepository = stadiumRepository;
        this.reservationRepository = reservationRepository;
        this.fileService = fileService;
    }
    
    public Stadium registerStadium(StadiumDto stadiumData, MultipartFile file){
        // 이미지 저장
        String imgUrl = fileService.store(file);

        // stadium 등록
        Stadium stadium = new Stadium(stadiumData,imgUrl);
        return stadiumRepository.save(stadium);
    }
    
    public List<Stadium> requestStadium(String location, String contact_phone){
        // if 문으로 다 쪼개서 서로 다른 repo 메소드를 호출
        if (location.isEmpty() && !contact_phone.isEmpty()) {
            return stadiumRepository.findByContactPhone(contact_phone);
        } else if (!location.isEmpty() && contact_phone.isEmpty()) {
            return stadiumRepository.findByLocation(location);
        } else if (location.isEmpty()) { // 둘 다 비었을 때, (contact_phone.isEmpty() 는 이미 true)
            return stadiumRepository.findAll();
        }else{
                return stadiumRepository.findByBoth(location, contact_phone);
        }
    }

    public Stadium getStadiumInfo(Integer stadiumId) throws IllegalArgumentException{
        Optional<Stadium> stadium = stadiumRepository.findById(stadiumId);
        if(stadium.isEmpty()){
            throw new IllegalArgumentException("해당 구장이 없습니다.");
        }
        return stadium.get();
    }
    
    public void validateLocation(String location) throws IllegalArgumentException {
        List<String> availableLocationList = Arrays.asList("마포구","서대문구","영등포구","강남구");
        if(!availableLocationList.contains(location)){
            throw new IllegalArgumentException("해당 위치 "+location+"은 등록될 수 없습니다.");
        }
    }

    public Reservation stadiumReservation(ReservationDto reservationData){
        // 1. 해당 시간에 예약이 있는가
        Optional<Reservation> reservation = reservationRepository.findByDateAndTime(reservationData.stadium_id,reservationData.date,reservationData.time);

        // 2. 없으면 -> 추가
        if(reservation.isPresent()){
            throw new IllegalStateException("해당 시간엔 이미 예약이 있습니다.");
        }
        else{
            Reservation newReservation = new Reservation(reservationData);
            return reservationRepository.save(newReservation);
        }
    }
    
}