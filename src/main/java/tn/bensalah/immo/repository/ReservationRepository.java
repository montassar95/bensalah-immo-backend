package tn.bensalah.immo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.bensalah.immo.entity.Reservation;
import tn.bensalah.immo.enums.StatutReservation;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByOrderByCreatedAtDesc();

    List<Reservation> findByStatutOrderByCreatedAtDesc(StatutReservation statut);

    List<Reservation> findByBienIdOrderByCreatedAtDesc(Long bienId);

    long countByStatut(StatutReservation statut);
}