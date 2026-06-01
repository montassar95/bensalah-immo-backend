package tn.bensalah.immo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.bensalah.immo.entity.ContactMessage;
import java.util.List;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    List<ContactMessage> findAllByOrderByCreatedAtDesc();

    List<ContactMessage> findByLuFalseOrderByCreatedAtDesc();

    long countByLuFalse();
}