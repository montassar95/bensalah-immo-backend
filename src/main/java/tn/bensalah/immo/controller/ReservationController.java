package tn.bensalah.immo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tn.bensalah.immo.dto.ReservationRequest;
import tn.bensalah.immo.dto.ReservationResponse;
import tn.bensalah.immo.enums.StatutReservation;
import tn.bensalah.immo.repository.ReservationRepository;
import tn.bensalah.immo.service.ReservationService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;
    // ── PUBLIC ──────────────────────────────────────

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> create(
            @Valid @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(reservationService.create(request));
    }

    // ── ADMIN ────────────────────────────────────────

    @GetMapping("/admin/reservations")
    public ResponseEntity<List<ReservationResponse>> getAll() {
        return ResponseEntity.ok(reservationService.getAll());
    }

    @PutMapping("/admin/reservations/{id}/statut")
    public ResponseEntity<ReservationResponse> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutReservation statut) {
        return ResponseEntity.ok(reservationService.updateStatut(id, statut));
    }
    
    @GetMapping("/biens/{bienId}/reservations/dates")
    public ResponseEntity<List<Map<String, String>>> getReservedDates(
            @PathVariable Long bienId) {
        List<Map<String, String>> dates = reservationRepository
                .findByBienIdOrderByCreatedAtDesc(bienId)
                .stream()
                .filter(r -> r.getStatut() == StatutReservation.CONFIRMEE ||
                             r.getStatut() == StatutReservation.EN_ATTENTE)
                .filter(r -> r.getDateArrivee() != null && r.getDateDepart() != null)
                .map(r -> Map.of(
                    "dateArrivee", r.getDateArrivee().toString(),
                    "dateDepart", r.getDateDepart().toString()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dates);
    }
}