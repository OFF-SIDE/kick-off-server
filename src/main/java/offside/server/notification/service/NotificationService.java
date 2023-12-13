package offside.server.notification.service;

import java.util.List;
import offside.server.notification.domain.Notification;
import offside.server.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;
    
    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    
    public List<Notification> requestNotificationByContactPhone(String contactPhone){
        return notificationRepository.findAllByContactPhoneOrderByCreatedTimeDesc(contactPhone);
    }
    
    public void createNotification(String contactPhone, String type, String message){
        final var notification = new Notification(contactPhone,type,message);
        notificationRepository.save(notification);
    }
}
