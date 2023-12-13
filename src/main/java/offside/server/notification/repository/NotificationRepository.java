package offside.server.notification.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import offside.server.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByContactPhoneOrderByCreatedTimeDesc(String contactPhone);
}
