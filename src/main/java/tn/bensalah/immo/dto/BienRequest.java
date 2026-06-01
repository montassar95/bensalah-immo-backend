package tn.bensalah.immo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tn.bensalah.immo.enums.TypeBien;
import tn.bensalah.immo.enums.TypeLocation;
import java.math.BigDecimal;
import java.util.List;

@Data
public class BienRequest {

    @NotBlank
    private String titre;

    private String description;

    @NotNull
    private TypeBien typeBien;

    @NotNull
    private TypeLocation typeLocation;

    private String zone;
    private String adresse;

    @NotNull
    private BigDecimal prix;

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

    private List<String> videoUrls;
}
