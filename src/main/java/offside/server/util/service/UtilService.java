package offside.server.util.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class UtilService {
    //오늘 날짜를 string으로 가져오가
    public String getDateFromToday(){
        var now = LocalDate.now().toString(); // 2023-12-03
        var newNow = now.replace("-","");
        return newNow.substring(2);
    }

    public LocalDate stringDateToLocalDate(String date){
        return LocalDate.of(Integer.parseInt("20" + date.substring(0,2)),Integer.parseInt( date.substring(2,4)),Integer.parseInt(date.substring(4,6)));
    }

    /* 한 달 내의 예약 까지만 가능하게 */
    public void validateDate(String date){
        if(LocalDate.now().plusMonths(1).isBefore(stringDateToLocalDate(date)))
            throw new IllegalArgumentException("한 달 내의 날짜만 입력하실 수 있습니다");
    }
}
