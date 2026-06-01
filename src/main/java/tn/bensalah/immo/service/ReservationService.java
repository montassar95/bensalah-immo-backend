package tn.bensalah.immo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.bensalah.immo.dto.ReservationRequest;
import tn.bensalah.immo.dto.ReservationResponse;
import tn.bensalah.immo.entity.Bien;
import tn.bensalah.immo.entity.Reservation;
import tn.bensalah.immo.enums.StatutReservation;
import tn.bensalah.immo.enums.TypeNotification;
import tn.bensalah.immo.repository.BienRepository;
import tn.bensalah.immo.repository.ReservationRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BienRepository bienRepository;
    private final EmailService emailService;
    private final NotificationService notificationService;

    public ReservationResponse create(ReservationRequest request) {
        Bien bien = bienRepository.findById(request.getBienId())
                .orElseThrow(() -> new RuntimeException("Bien non trouvé"));

        Reservation reservation = Reservation.builder()
                .bien(bien)
                .clientNom(request.getClientNom())
                .clientPrenom(request.getClientPrenom())
                .clientEmail(request.getClientEmail())
                .clientTelephone(request.getClientTelephone())
                .clientPays(request.getClientPays())
                .clientMessage(request.getClientMessage())
                .dateArrivee(request.getDateArrivee())
                .dateDepart(request.getDateDepart())
                .nombrePersonnes(request.getNombrePersonnes())
                .statut(StatutReservation.EN_ATTENTE)
                .build();

        reservation = reservationRepository.save(reservation);

        // Email à l'admin
        emailService.sendEmail(
            "admin@bensalahimmo.tn",
            "🏠 Nouvelle réservation — " + bien.getTitre(),
            "Nouvelle demande de réservation :\n\n" +
            "Bien : " + bien.getTitre() + "\n" +
            "Client : " + request.getClientNom() + " " + request.getClientPrenom() + "\n" +
            "Email : " + request.getClientEmail() + "\n" +
            "Téléphone : " + request.getClientTelephone() + "\n" +
            "Pays : " + request.getClientPays() + "\n" +
            "Arrivée : " + request.getDateArrivee() + "\n" +
            "Départ : " + request.getDateDepart() + "\n" +
            "Personnes : " + request.getNombrePersonnes() + "\n" +
            "Message : " + request.getClientMessage()
        );

        // Email de confirmation au client
        emailService.sendEmail(
            request.getClientEmail(),
            "✅ Demande reçue — Ben Salah Immo",
            "Bonjour " + request.getClientPrenom() + ",\n\n" +
            "Votre demande de réservation pour \"" + bien.getTitre() + "\" a bien été reçue.\n" +
            "Notre équipe vous contactera dans les plus brefs délais pour confirmer.\n\n" +
            "WhatsApp : +216 00 000 000\n\n" +
            "Ben Salah Immo — Djerba"
        );

        // Notification in-app
        notificationService.create(
            TypeNotification.NOUVELLE_RESERVATION,
            "Nouvelle réservation",
            request.getClientNom() + " " + request.getClientPrenom() +
            " — " + bien.getTitre(),
            reservation.getId(),
            "RESERVATION"
        );

        return toResponse(reservation);
    }

    public List<ReservationResponse> getAll() {
        return reservationRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ReservationResponse updateStatut(Long id, StatutReservation statut) {
        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        r.setStatut(statut);
        return toResponse(reservationRepository.save(r));
    }

    private ReservationResponse toResponse(Reservation r) {
        ReservationResponse res = new ReservationResponse();
        res.setId(r.getId());
        res.setBienId(r.getBien().getId());
        res.setBienTitre(r.getBien().getTitre());
        res.setClientNom(r.getClientNom());
        res.setClientPrenom(r.getClientPrenom());
        res.setClientEmail(r.getClientEmail());
        res.setClientTelephone(r.getClientTelephone());
        res.setClientPays(r.getClientPays());
        res.setClientMessage(r.getClientMessage());
        res.setDateArrivee(r.getDateArrivee());
        res.setDateDepart(r.getDateDepart());
        res.setNombrePersonnes(r.getNombrePersonnes());
        res.setStatut(r.getStatut());
        res.setNotesAdmin(r.getNotesAdmin());
        res.setCreatedAt(r.getCreatedAt());
        return res;
    }
}