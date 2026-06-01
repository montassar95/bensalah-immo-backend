package tn.bensalah.immo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.bensalah.immo.dto.ContactRequest;
import tn.bensalah.immo.entity.ContactMessage;
import tn.bensalah.immo.service.ContactService;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    // ── PUBLIC ──────────────────────────────────────

    @PostMapping("/contact")
    public ResponseEntity<ContactMessage> create(
            @Valid @RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactService.create(request));
    }

    // ── ADMIN ────────────────────────────────────────

    @GetMapping("/admin/contact")
    public ResponseEntity<List<ContactMessage>> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }

    @PutMapping("/admin/contact/{id}/lu")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        contactService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}