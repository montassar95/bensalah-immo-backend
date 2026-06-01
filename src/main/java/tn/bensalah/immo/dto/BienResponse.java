package tn.bensalah.immo.dto;

import lombok.Data;
import tn.bensalah.immo.enums.StatutBien;
import tn.bensalah.immo.enums.TypeBien;
import tn.bensalah.immo.enums.TypeLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BienResponse {
    private Long id;
    private String titre;
    private String description;
    private TypeBien typeBien;
    private TypeLocation typeLocation;
    private StatutBien statut;
    private String zone;
    private String adresse;
    private BigDecimal prix;
    private String devise;
    private Integer chambres;
    private Integer sallesDeBain;
    private Double surface;
    private boolean piscine;
    private boolean vueMer;
    private boolean wifi;
    private boolean parking;
    private boolean climatisation;
    private boolean jardin;
    private boolean animaux;
    private boolean meuble;
    private boolean featured;
    private List<String> images;
    private List<String> videoUrls;
    private Double rating;
    private Integer nombreAvis;
    private LocalDateTime createdAt;
}