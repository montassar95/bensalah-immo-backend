package tn.bensalah.immo.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.bensalah.immo.enums.StatutReservation;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

    // Infos client (pas besoin de compte)
    @Column(nullable = false)
    private String clientNom;

    @Column(nullable = false)
    private String clientPrenom;

    @Column(nullable = false)
    private String clientEmail;

    @Column(nullable = false)
    private String clientTelephone;

    private String clientPays;
    private String clientMessage;

    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private Integer nombrePersonnes;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    private String notesAdmin;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.statut == null) this.statut = StatutReservation.EN_ATTENTE;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}