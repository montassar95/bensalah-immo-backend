package tn.bensalah.immo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.bensalah.immo.entity.Bien;
import tn.bensalah.immo.enums.StatutBien;
import tn.bensalah.immo.enums.TypeBien;
import tn.bensalah.immo.enums.TypeLocation;
import java.util.List;

public interface BienRepository extends JpaRepository<Bien, Long> {

    List<Bien> findByActifTrueOrderByCreatedAtDesc();

    List<Bien> findByTypeLocationAndActifTrue(TypeLocation typeLocation);

    List<Bien> findByFeaturedTrueAndActifTrue();

    List<Bien> findByStatutAndActifTrue(StatutBien statut);

    @Query("""
        SELECT b FROM Bien b
        WHERE b.actif = true
        AND (:typeLocation IS NULL OR b.typeLocation = :typeLocation)
        AND (:typeBien IS NULL OR b.typeBien = :typeBien)
        AND (:zone IS NULL OR LOWER(b.zone) LIKE LOWER(CONCAT('%', :zone, '%')))
        AND (:piscine IS NULL OR b.piscine = :piscine)
        AND (:vueMer IS NULL OR b.vueMer = :vueMer)
        AND (:prixMax IS NULL OR b.prix <= :prixMax)
        ORDER BY b.createdAt DESC
    """)
    List<Bien> search(
        @Param("typeLocation") TypeLocation typeLocation,
        @Param("typeBien") TypeBien typeBien,
        @Param("zone") String zone,
        @Param("piscine") Boolean piscine,
        @Param("vueMer") Boolean vueMer,
        @Param("prixMax") Double prixMax
    );
}