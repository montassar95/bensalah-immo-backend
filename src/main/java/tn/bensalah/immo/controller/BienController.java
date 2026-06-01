package tn.bensalah.immo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tn.bensalah.immo.dto.BienRequest;
import tn.bensalah.immo.dto.BienResponse;
import tn.bensalah.immo.enums.StatutBien;
import tn.bensalah.immo.enums.TypeBien;
import tn.bensalah.immo.enums.TypeLocation;
import tn.bensalah.immo.service.BienService;

@RestController
@RequestMapping("/api/biens")
@RequiredArgsConstructor
public class BienController {

    private final BienService bienService;

    // ── PUBLIC ──────────────────────────────────────

    @GetMapping
    public ResponseEntity<List<BienResponse>> getAll() {
        return ResponseEntity.ok(bienService.getAllActifs());
    }

    @GetMapping("/featured")
    public ResponseEntity<List<BienResponse>> getFeatured() {
        return ResponseEntity.ok(bienService.getFeatured());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BienResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bienService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BienResponse>> search(
            @RequestParam(required = false) TypeLocation typeLocation,
            @RequestParam(required = false) TypeBien typeBien,
            @RequestParam(required = false) String zone,
            @RequestParam(required = false) Boolean piscine,
            @RequestParam(required = false) Boolean vueMer,
            @RequestParam(required = false) Double prixMax) {
        return ResponseEntity.ok(
            bienService.search(typeLocation, typeBien, zone, piscine, vueMer, prixMax)
        );
    }

    // ── ADMIN ────────────────────────────────────────

    @PostMapping("/admin")
    public ResponseEntity<BienResponse> create(
            @Valid @RequestPart("bien") BienRequest request,
            @RequestPart(value = "images", required = false) List<MultipartFile> images)
            throws IOException {
        return ResponseEntity.ok(bienService.create(request, images));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<BienResponse> update(
            @PathVariable Long id,
            @Valid @RequestPart("bien") BienRequest request,
            @RequestPart(value = "images", required = false) List<MultipartFile> images)
            throws IOException {
        return ResponseEntity.ok(bienService.update(id, request, images));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bienService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @DeleteMapping("/admin/{id}/images")
    public ResponseEntity<BienResponse> deleteImage(
            @PathVariable Long id,
            @RequestParam String imageUrl) throws IOException {
        return ResponseEntity.ok(bienService.deleteImage(id, imageUrl));
    }

    @PutMapping("/admin/{id}/statut")
    public ResponseEntity<BienResponse> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutBien statut) {
        return ResponseEntity.ok(bienService.updateStatut(id, statut));
    }
}