package tn.bensalah.immo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tn.bensalah.immo.entity.Bien;
import tn.bensalah.immo.entity.User;
import tn.bensalah.immo.enums.*;
import tn.bensalah.immo.repository.BienRepository;
import tn.bensalah.immo.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final BienRepository bienRepository;
//
//    @Override
//    public void run(String... args) {
//
//        // Admin
//        if (!userRepository.existsByEmail("admin@bensalahimmo.tn")) {
//            User admin = User.builder()
//                    .email("admin@bensalahimmo.tn")
//                    .password(passwordEncoder.encode("admin123"))
//                    .nom("Ben Salah")
//                    .prenom("Admin")
//                    .telephone("+21600000000")
//                    .role(Role.ADMIN)
//                    .actif(true)
//                    .build();
//            userRepository.save(admin);
//            System.out.println("✅ Admin créé : admin@bensalahimmo.tn / admin123");
//        }
//
//        // Biens de test
//        if (bienRepository.count() == 0) {
//
//            bienRepository.saveAll(List.of(
//
//                Bien.builder()
//                    .titre("Villa Oasis — Houmt Souk")
//                    .description("Magnifique villa avec piscine privée, vue mer et jardin tropical. Idéale pour familles.")
//                    .typeBien(TypeBien.VILLA)
//                    .typeLocation(TypeLocation.VACANCES)
//                    .zone("Houmt Souk")
//                    .adresse("Route de la plage, Houmt Souk")
//                    .prix(new BigDecimal("250"))
//                    .chambres(4)
//                    .sallesDeBain(2)
//                    .surface(200.0)
//                    .piscine(true)
//                    .vueMer(true)
//                    .wifi(true)
//                    .parking(true)
//                    .climatisation(true)
//                    .jardin(true)
//                    .animaux(false)
//                    .meuble(true)
//                    .featured(true)
//                    .rating(4.9)
//                    .nombreAvis(38)
//                    .actif(true)
//                    .statut(StatutBien.DISPONIBLE)
//                    .images(List.of(
//                        "https://images.unsplash.com/photo-1564013799919-ab600027ffc6?w=700&auto=format&q=80",
//                        "https://images.unsplash.com/photo-1582268611958-ebfd161ef9cf?w=700&auto=format&q=80"
//                    ))
//                    .videoUrls(List.of())
//                    .build(),
//
//                Bien.builder()
//                    .titre("Appartement Vue Mer — Sidi Mahres")
//                    .description("Bel appartement en front de mer avec terrasse panoramique. Accès direct à la plage.")
//                    .typeBien(TypeBien.APPARTEMENT)
//                    .typeLocation(TypeLocation.VACANCES)
//                    .zone("Sidi Mahres")
//                    .adresse("Bord de mer, Sidi Mahres")
//                    .prix(new BigDecimal("120"))
//                    .chambres(2)
//                    .sallesDeBain(1)
//                    .surface(75.0)
//                    .piscine(false)
//                    .vueMer(true)
//                    .wifi(true)
//                    .parking(true)
//                    .climatisation(true)
//                    .jardin(false)
//                    .animaux(false)
//                    .meuble(true)
//                    .featured(true)
//                    .rating(4.7)
//                    .nombreAvis(12)
//                    .actif(true)
//                    .statut(StatutBien.DISPONIBLE)
//                    .images(List.of(
//                        "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?w=700&auto=format&q=80"
//                    ))
//                    .videoUrls(List.of())
//                    .build(),
//
//                Bien.builder()
//                    .titre("Dar Traditionnel — Midoun")
//                    .description("Dar typiquement djerbien rénové avec goût. Patio fleuri, piscine et architecture authentique.")
//                    .typeBien(TypeBien.DAR)
//                    .typeLocation(TypeLocation.VACANCES)
//                    .zone("Midoun")
//                    .adresse("Centre Midoun")
//                    .prix(new BigDecimal("180"))
//                    .chambres(3)
//                    .sallesDeBain(2)
//                    .surface(150.0)
//                    .piscine(true)
//                    .vueMer(false)
//                    .wifi(true)
//                    .parking(false)
//                    .climatisation(true)
//                    .jardin(true)
//                    .animaux(false)
//                    .meuble(true)
//                    .featured(true)
//                    .rating(4.8)
//                    .nombreAvis(54)
//                    .actif(true)
//                    .statut(StatutBien.DISPONIBLE)
//                    .images(List.of(
//                        "https://images.unsplash.com/photo-1600596542815-ffad4c1539a9?w=700&auto=format&q=80"
//                    ))
//                    .videoUrls(List.of())
//                    .build(),
//
//                Bien.builder()
//                    .titre("Villa Palatiale — Dar Luxe")
//                    .description("Villa de standing exceptionnel avec spa, piscine olimpique et vue panoramique sur la mer.")
//                    .typeBien(TypeBien.VILLA)
//                    .typeLocation(TypeLocation.VACANCES)
//                    .zone("Midoun")
//                    .adresse("Zone résidentielle, Midoun")
//                    .prix(new BigDecimal("450"))
//                    .chambres(5)
//                    .sallesDeBain(4)
//                    .surface(400.0)
//                    .piscine(true)
//                    .vueMer(true)
//                    .wifi(true)
//                    .parking(true)
//                    .climatisation(true)
//                    .jardin(true)
//                    .animaux(false)
//                    .meuble(true)
//                    .featured(true)
//                    .rating(4.9)
//                    .nombreAvis(21)
//                    .actif(true)
//                    .statut(StatutBien.DISPONIBLE)
//                    .images(List.of(
//                        "https://images.unsplash.com/photo-1613977257363-707ba9348227?w=700&auto=format&q=80"
//                    ))
//                    .videoUrls(List.of())
//                    .build(),
//
//                Bien.builder()
//                    .titre("Appartement Meublé — Houmt Souk")
//                    .description("Appartement moderne entièrement meublé et équipé. Idéal pour séjour longue durée.")
//                    .typeBien(TypeBien.APPARTEMENT)
//                    .typeLocation(TypeLocation.LONG_SEJOUR)
//                    .zone("Houmt Souk")
//                    .adresse("Centre ville, Houmt Souk")
//                    .prix(new BigDecimal("800"))
//                    .chambres(2)
//                    .sallesDeBain(1)
//                    .surface(65.0)
//                    .piscine(false)
//                    .vueMer(false)
//                    .wifi(true)
//                    .parking(true)
//                    .climatisation(true)
//                    .jardin(false)
//                    .animaux(false)
//                    .meuble(true)
//                    .featured(false)
//                    .rating(4.6)
//                    .nombreAvis(8)
//                    .actif(true)
//                    .statut(StatutBien.DISPONIBLE)
//                    .images(List.of(
//                        "https://images.unsplash.com/photo-1560448204-e02f11c3d0e2?w=700&auto=format&q=80"
//                    ))
//                    .videoUrls(List.of())
//                    .build(),
//
//                Bien.builder()
//                    .titre("Local Commercial — Rue Principale")
//                    .description("Local en rez-de-chaussée avec grande vitrine sur rue principale. Fort passage.")
//                    .typeBien(TypeBien.LOCAL_COMMERCIAL)
//                    .typeLocation(TypeLocation.COMMERCIAL)
//                    .zone("Houmt Souk")
//                    .adresse("Rue Principale, Houmt Souk")
//                    .prix(new BigDecimal("1500"))
//                    .chambres(null)
//                    .sallesDeBain(1)
//                    .surface(85.0)
//                    .piscine(false)
//                    .vueMer(false)
//                    .wifi(false)
//                    .parking(true)
//                    .climatisation(false)
//                    .jardin(false)
//                    .animaux(false)
//                    .meuble(false)
//                    .featured(false)
//                    .actif(true)
//                    .statut(StatutBien.DISPONIBLE)
//                    .images(List.of(
//                        "https://images.unsplash.com/photo-1486325212027-8081e485255e?w=700&auto=format&q=80"
//                    ))
//                    .videoUrls(List.of())
//                    .build()
//            ));
//
//            System.out.println("✅ 6 biens de test créés !");
//        }
//    }
//}

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BienRepository bienRepository;

    @Override
    public void run(String... args) {

        // Admin
        if (!userRepository.existsByEmail("admin@bensalahimmo.tn")) {
            User admin = User.builder()
                    .email("zouhair")
                    .password(passwordEncoder.encode("admin123"))
                    .nom("Ben Salah")
                    .prenom("Admin")
                    .telephone("+21600000000")
                    .role(Role.ADMIN)
                    .actif(true)
                    .build();
            userRepository.save(admin);
            System.out.println("✅ Admin créé : admin@bensalahimmo.tn / admin123");
        }

        // Biens de test
        if (bienRepository.count() == 0) {

            bienRepository.saveAll(List.of(

                Bien.builder()
                    .titre("Villa Oasis — Houmt Souk")
                    .description("Magnifique villa avec piscine privée, vue mer et jardin tropical. Idéale pour familles.")
                    .typeBien(TypeBien.VILLA)
                    .typeLocation(TypeLocation.VACANCES)
                    .zone("Houmt Souk")
                    .adresse("Route de la plage, Houmt Souk")
                    .prix(new BigDecimal("250"))
                    .chambres(4)
                    .sallesDeBain(2)
                    .surface(200.0)
                    .piscine(true)
                    .vueMer(true)
                    .wifi(true)
                    .parking(true)
                    .climatisation(true)
                    .jardin(true)
                    .animaux(false)
                    .meuble(true)
                    .featured(true)
                    .rating(4.9)
                    .nombreAvis(38)
                    .actif(true)
                    .statut(StatutBien.DISPONIBLE)
                    .images(List.of(
                        "https://images.unsplash.com/photo-1564013799919-ab600027ffc6?w=700&auto=format&q=80",
                        "https://images.unsplash.com/photo-1582268611958-ebfd161ef9cf?w=700&auto=format&q=80"
                    ))
                    .videoUrls(List.of())
                    .build(),

                Bien.builder()
                    .titre("Appartement Vue Mer — Sidi Mahres")
                    .description("Bel appartement en front de mer avec terrasse panoramique. Accès direct à la plage.")
                    .typeBien(TypeBien.APPARTEMENT)
                    .typeLocation(TypeLocation.VACANCES)
                    .zone("Sidi Mahres")
                    .adresse("Bord de mer, Sidi Mahres")
                    .prix(new BigDecimal("120"))
                    .chambres(2)
                    .sallesDeBain(1)
                    .surface(75.0)
                    .piscine(false)
                    .vueMer(true)
                    .wifi(true)
                    .parking(true)
                    .climatisation(true)
                    .jardin(false)
                    .animaux(false)
                    .meuble(true)
                    .featured(true)
                    .rating(4.7)
                    .nombreAvis(12)
                    .actif(true)
                    .statut(StatutBien.DISPONIBLE)
                    .images(List.of(
                        "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?w=700&auto=format&q=80"
                    ))
                    .videoUrls(List.of())
                    .build(),

                Bien.builder()
                    .titre("Dar Traditionnel — Midoun")
                    .description("Dar typiquement djerbien rénové avec goût. Patio fleuri, piscine et architecture authentique.")
                    .typeBien(TypeBien.DAR)
                    .typeLocation(TypeLocation.VACANCES)
                    .zone("Midoun")
                    .adresse("Centre Midoun")
                    .prix(new BigDecimal("180"))
                    .chambres(3)
                    .sallesDeBain(2)
                    .surface(150.0)
                    .piscine(true)
                    .vueMer(false)
                    .wifi(true)
                    .parking(false)
                    .climatisation(true)
                    .jardin(true)
                    .animaux(false)
                    .meuble(true)
                    .featured(true)
                    .rating(4.8)
                    .nombreAvis(54)
                    .actif(true)
                    .statut(StatutBien.DISPONIBLE)
                    .images(List.of(
                        "https://images.unsplash.com/photo-1600596542815-ffad4c1539a9?w=700&auto=format&q=80"
                    ))
                    .videoUrls(List.of())
                    .build(),

                Bien.builder()
                    .titre("Villa Palatiale — Dar Luxe")
                    .description("Villa de standing exceptionnel avec spa, piscine olimpique et vue panoramique sur la mer.")
                    .typeBien(TypeBien.VILLA)
                    .typeLocation(TypeLocation.VACANCES)
                    .zone("Midoun")
                    .adresse("Zone résidentielle, Midoun")
                    .prix(new BigDecimal("450"))
                    .chambres(5)
                    .sallesDeBain(4)
                    .surface(400.0)
                    .piscine(true)
                    .vueMer(true)
                    .wifi(true)
                    .parking(true)
                    .climatisation(true)
                    .jardin(true)
                    .animaux(false)
                    .meuble(true)
                    .featured(true)
                    .rating(4.9)
                    .nombreAvis(21)
                    .actif(true)
                    .statut(StatutBien.DISPONIBLE)
                    .images(List.of(
                        "https://images.unsplash.com/photo-1613977257363-707ba9348227?w=700&auto=format&q=80"
                    ))
                    .videoUrls(List.of())
                    .build(),

                Bien.builder()
                    .titre("Appartement Meublé — Houmt Souk")
                    .description("Appartement moderne entièrement meublé et équipé. Idéal pour séjour longue durée.")
                    .typeBien(TypeBien.APPARTEMENT)
                    .typeLocation(TypeLocation.LONG_SEJOUR)
                    .zone("Houmt Souk")
                    .adresse("Centre ville, Houmt Souk")
                    .prix(new BigDecimal("800"))
                    .chambres(2)
                    .sallesDeBain(1)
                    .surface(65.0)
                    .piscine(false)
                    .vueMer(false)
                    .wifi(true)
                    .parking(true)
                    .climatisation(true)
                    .jardin(false)
                    .animaux(false)
                    .meuble(true)
                    .featured(false)
                    .rating(4.6)
                    .nombreAvis(8)
                    .actif(true)
                    .statut(StatutBien.DISPONIBLE)
                    .images(List.of(
                        "https://images.unsplash.com/photo-1560448204-e02f11c3d0e2?w=700&auto=format&q=80"
                    ))
                    .videoUrls(List.of())
                    .build(),

                Bien.builder()
                    .titre("Local Commercial — Rue Principale")
                    .description("Local en rez-de-chaussée avec grande vitrine sur rue principale. Fort passage.")
                    .typeBien(TypeBien.LOCAL_COMMERCIAL)
                    .typeLocation(TypeLocation.COMMERCIAL)
                    .zone("Houmt Souk")
                    .adresse("Rue Principale, Houmt Souk")
                    .prix(new BigDecimal("1500"))
                    .chambres(null)
                    .sallesDeBain(1)
                    .surface(85.0)
                    .piscine(false)
                    .vueMer(false)
                    .wifi(false)
                    .parking(true)
                    .climatisation(false)
                    .jardin(false)
                    .animaux(false)
                    .meuble(false)
                    .featured(false)
                    .actif(true)
                    .statut(StatutBien.DISPONIBLE)
                    .images(List.of(
                        "https://images.unsplash.com/photo-1486325212027-8081e485255e?w=700&auto=format&q=80"
                    ))
                    .videoUrls(List.of())
                    .build()
            ));

            System.out.println("✅ 6 biens de test créés !");
        }
    }
}