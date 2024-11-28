package store.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import store.notification.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
