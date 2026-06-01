package tn.bensalah.immo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.bensalah.immo.entity.Notification;
import tn.bensalah.immo.enums.TypeNotification;
import tn.bensalah.immo.repository.NotificationRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void create(TypeNotification type, String titre, String message,
                       Long refId, String refType) {
        Notification notif = Notification.builder()
                .type(type)
                .titre(titre)
                .message(message)
                .referenceId(refId)
                .referenceType(refType)
                .lu(false)
                .build();
        notificationRepository.save(notif);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Notification> getUnread() {
        return notificationRepository.findByLuFalseOrderByCreatedAtDesc();
    }

    public long countUnread() {
        return notificationRepository.countByLuFalse();
    }

    public void markAsRead(Long id) {
        notificationRepository.findById(id).ifPresent(n -> {
            n.setLu(true);
            notificationRepository.save(n);
        });
    }

    public void markAllAsRead() {
        List<Notification> notifs = notificationRepository.findByLuFalseOrderByCreatedAtDesc();
        notifs.forEach(n -> n.setLu(true));
        notificationRepository.saveAll(notifs);
    }
}