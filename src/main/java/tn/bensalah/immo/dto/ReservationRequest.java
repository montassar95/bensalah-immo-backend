package tn.bensalah.immo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservationRequest {

    @NotNull
    private Long bienId;

    @NotBlank
    private String clientNom;

    @NotBlank
    private String clientPrenom;

    @Email
    @NotBlank
    private String clientEmail;

    @NotBlank
    private String clientTelephone;

    private String clientPays;
    private String clientMessage;

    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private Integer nombrePersonnes;
}