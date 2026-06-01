package tn.bensalah.immo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.bensalah.immo.dto.ContactRequest;
import tn.bensalah.immo.entity.ContactMessage;
import tn.bensalah.immo.enums.TypeNotification;
import tn.bensalah.immo.repository.ContactMessageRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMessageRepository contactMessageRepository;
    private final EmailService emailService;
    private final NotificationService notificationService;

    public ContactMessage create(ContactRequest request) {
        ContactMessage msg = ContactMessage.builder()
                .nom(request.getNom())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .pays(request.getPays())
                .sujet(request.getSujet())
                .message(request.getMessage())
                .lu(false)
                .build();

        msg = contactMessageRepository.save(msg);

        // Email admin
        emailService.sendEmail(
            "admin@bensalahimmo.tn",
            "📩 Nouveau message — " + request.getSujet(),
            "De : " + request.getNom() + " <" + request.getEmail() + ">\n" +
            "Téléphone : " + request.getTelephone() + "\n" +
            "Pays : " + request.getPays() + "\n\n" +
            request.getMessage()
        );

        // Notif in-app
        notificationService.create(
            TypeNotification.NOUVEAU_MESSAGE,
            "Nouveau message",
            request.getNom() + " — " + request.getSujet(),
            msg.getId(),
            "CONTACT"
        );

        return msg;
    }

    public List<ContactMessage> getAll() {
        return contactMessageRepository.findAllByOrderByCreatedAtDesc();
    }

    public void markAsRead(Long id) {
        contactMessageRepository.findById(id).ifPresent(m -> {
            m.setLu(true);
            contactMessageRepository.save(m);
        });
    }

    public long countUnread() {
        return contactMessageRepository.countByLuFalse();
    }
}