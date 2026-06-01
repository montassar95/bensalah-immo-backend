package tn.bensalah.immo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.bensalah.immo.dto.DashboardStats;
import tn.bensalah.immo.enums.StatutReservation;
import tn.bensalah.immo.repository.*;
import tn.bensalah.immo.service.ContactService;
import tn.bensalah.immo.service.NotificationService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BienRepository bienRepository;
    private final ReservationRepository reservationRepository;
    private final ContactService contactService;
    private final NotificationService notificationService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStats> getDashboard() {
        DashboardStats stats = DashboardStats.builder()
                .totalBiens(bienRepository.count())
                .biensActifs(bienRepository.findByActifTrueOrderByCreatedAtDesc().size())
                .totalReservations(reservationRepository.count())
                .reservationsEnAttente(
                    reservationRepository.countByStatut(StatutReservation.EN_ATTENTE)
                )
                .messagesNonLus(contactService.countUnread())
                .notificationsNonLues(notificationService.countUnread())
                .build();

        return ResponseEntity.ok(stats);
    }
}