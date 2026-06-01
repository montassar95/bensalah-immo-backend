package tn.bensalah.immo.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.bensalah.immo.enums.TypeBien;
import tn.bensalah.immo.enums.StatutBien;
import tn.bensalah.immo.enums.TypeLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "biens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private TypeBien typeBien;

    @Enumerated(EnumType.STRING)
    private TypeLocation typeLocation;

    @Enumerated(EnumType.STRING)
    private StatutBien statut;

    private String zone;
    private String adresse;

    private BigDecimal prix;
    private String devise = "TND";

    private Integer chambres;
    private Integer sallesDeBain;
    private Double surface;

    private boolean piscine = false;
    private boolean vueMer = false;
    private boolean wifi = false;
    private boolean parking = false;
    private boolean climatisation = false;
    private boolean jardin = false;
    private boolean animaux = false;
    private boolean meuble = false;

    // Images Cloudinary
    @ElementCollection
    @CollectionTable(name = "bien_images", joinColumns = @JoinColumn(name = "bien_id"))
    @Column(name = "image_url")
    private List<String> images;

    // Videos YouTube
    @ElementCollection
    @CollectionTable(name = "bien_videos", joinColumns = @JoinColumn(name = "bien_id"))
    @Column(name = "video_url")
    private List<String> videoUrls;

    private Double rating;
    private Integer nombreAvis;

    private boolean featured = false;
    private boolean actif = true;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.statut == null) this.statut = StatutBien.DISPONIBLE;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}