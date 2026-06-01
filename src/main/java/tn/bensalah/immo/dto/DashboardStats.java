package tn.bensalah.immo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardStats {
    private long totalBiens;
    private long biensActifs;
    private long totalReservations;
    private long reservationsEnAttente;
    private long messagesNonLus;
    private long notificationsNonLues;
}