package tn.bensalah.immo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank
    private String nom;

    @Email
    @NotBlank
    private String email;

    private String telephone;
    private String pays;
    private String sujet;

    @NotBlank
    private String message;
}