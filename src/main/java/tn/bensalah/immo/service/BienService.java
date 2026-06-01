package tn.bensalah.immo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.bensalah.immo.dto.BienRequest;
import tn.bensalah.immo.dto.BienResponse;
import tn.bensalah.immo.entity.Bien;
import tn.bensalah.immo.enums.StatutBien;
import tn.bensalah.immo.enums.TypeBien;
import tn.bensalah.immo.enums.TypeLocation;
import tn.bensalah.immo.repository.BienRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BienService {

    private final BienRepository bienRepository;
    private final CloudinaryService cloudinaryService;

    public List<BienResponse> getAllActifs() {
        return bienRepository.findByActifTrueOrderByCreatedAtDesc()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<BienResponse> getFeatured() {
        return bienRepository.findByFeaturedTrueAndActifTrue()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<BienResponse> search(TypeLocation typeLocation, TypeBien typeBien,
                                      String zone, Boolean piscine,
                                      Boolean vueMer, Double prixMax) {
        return bienRepository.search(typeLocation, typeBien, zone, piscine, vueMer, prixMax)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public BienResponse getById(Long id) {
        return toResponse(bienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bien non trouvé")));
    }

    public BienResponse create(BienRequest request, List<MultipartFile> images) throws IOException {
        Bien bien = toBien(request);
        bien.setImages(new ArrayList<>());

        if (images != null) {
            for (MultipartFile file : images) {
                String url = cloudinaryService.uploadImage(file, "biens");
                bien.getImages().add(url);
            }
        }

        return toResponse(bienRepository.save(bien));
    }

    public BienResponse update(Long id, BienRequest request, List<MultipartFile> newImages) throws IOException {
        Bien bien = bienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bien non trouvé"));

        bien.setTitre(request.getTitre());
        bien.setDescription(request.getDescription());
        bien.setTypeBien(request.getTypeBien());
        bien.setTypeLocation(request.getTypeLocation());
        bien.setZone(request.getZone());
        bien.setAdresse(request.getAdresse());
        bien.setPrix(request.getPrix());
        bien.setChambres(request.getChambres());
        bien.setSallesDeBain(request.getSallesDeBain());
        bien.setSurface(request.getSurface());
        bien.setPiscine(request.isPiscine());
        bien.setVueMer(request.isVueMer());
        bien.setWifi(request.isWifi());
        bien.setParking(request.isParking());
        bien.setClimatisation(request.isClimatisation());
        bien.setJardin(request.isJardin());
        bien.setAnimaux(request.isAnimaux());
        bien.setMeuble(request.isMeuble());
        bien.setFeatured(request.isFeatured());
        bien.setVideoUrls(request.getVideoUrls());

        if (newImages != null) {
            for (MultipartFile file : newImages) {
                String url = cloudinaryService.uploadImage(file, "biens");
                bien.getImages().add(url);
            }
        }

        return toResponse(bienRepository.save(bien));
    }

    public void delete(Long id) {
        Bien bien = bienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bien non trouvé"));
        bien.setActif(false);
        bienRepository.save(bien);
    }

    private Bien toBien(BienRequest r) {
        return Bien.builder()
                .titre(r.getTitre())
                .description(r.getDescription())
                .typeBien(r.getTypeBien())
                .typeLocation(r.getTypeLocation())
                .zone(r.getZone())
                .adresse(r.getAdresse())
                .prix(r.getPrix())
                .chambres(r.getChambres())
                .sallesDeBain(r.getSallesDeBain())
                .surface(r.getSurface())
                .piscine(r.isPiscine())
                .vueMer(r.isVueMer())
                .wifi(r.isWifi())
                .parking(r.isParking())
                .climatisation(r.isClimatisation())
                .jardin(r.isJardin())
                .animaux(r.isAnimaux())
                .meuble(r.isMeuble())
                .featured(r.isFeatured())
                .videoUrls(r.getVideoUrls())
                .actif(true)
                .statut(StatutBien.DISPONIBLE)
                .build();
    }

    public BienResponse toResponse(Bien b) {
        BienResponse r = new BienResponse();
        r.setId(b.getId());
        r.setTitre(b.getTitre());
        r.setDescription(b.getDescription());
        r.setTypeBien(b.getTypeBien());
        r.setTypeLocation(b.getTypeLocation());
        r.setStatut(b.getStatut());
        r.setZone(b.getZone());
        r.setAdresse(b.getAdresse());
        r.setPrix(b.getPrix());
        r.setDevise(b.getDevise());
        r.setChambres(b.getChambres());
        r.setSallesDeBain(b.getSallesDeBain());
        r.setSurface(b.getSurface());
        r.setPiscine(b.isPiscine());
        r.setVueMer(b.isVueMer());
        r.setWifi(b.isWifi());
        r.setParking(b.isParking());
        r.setClimatisation(b.isClimatisation());
        r.setJardin(b.isJardin());
        r.setAnimaux(b.isAnimaux());
        r.setMeuble(b.isMeuble());
        r.setFeatured(b.isFeatured());
        r.setImages(b.getImages());
        r.setVideoUrls(b.getVideoUrls());
        r.setRating(b.getRating());
        r.setNombreAvis(b.getNombreAvis());
        r.setCreatedAt(b.getCreatedAt());
        return r;
    }
    
    public BienResponse deleteImage(Long id, String imageUrl) throws IOException {
        Bien bien = bienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bien non trouvé"));
        bien.getImages().remove(imageUrl);
        return toResponse(bienRepository.save(bien));
    }

    public BienResponse updateStatut(Long id, StatutBien statut) {
        Bien bien = bienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bien non trouvé"));
        bien.setStatut(statut);
        return toResponse(bienRepository.save(bien));
    }
}