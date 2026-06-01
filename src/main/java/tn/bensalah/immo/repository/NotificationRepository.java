package tn.bensalah.immo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.bensalah.immo.entity.Notification;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByOrderByCreatedAtDesc();

    List<Notification> findByLuFalseOrderByCreatedAtDesc();

    long countByLuFalse();
}