package tn.bensalah.immo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.bensalah.immo.service.CloudinaryService;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/images")
@RequiredArgsConstructor
public class ImageController {

    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "folder", defaultValue = "general") String folder)
            throws IOException {
        String url = cloudinaryService.uploadImage(file, folder);
        return ResponseEntity.ok(Map.of("url", url));
    }
}