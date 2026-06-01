package tn.bensalah.immo.dto;

import lombok.Data;
import tn.bensalah.immo.enums.StatutReservation;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservationResponse {
    private Long id;
    private Long bienId;
    private String bienTitre;
    private String clientNom;
    private String clientPrenom;
    private String clientEmail;
    private String clientTelephone;
    private String clientPays;
    private String clientMessage;
    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private Integer nombrePersonnes;
    private StatutReservation statut;
    private String notesAdmin;
    private LocalDateTime createdAt;
}