package tn.bensalah.immo.config;
 

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @Scheduled(fixedRate = 60000) // toutes les 1 minutes
    public void keepAlive() {
        try {
            new RestTemplate().getForObject(
                "https://bensalah-immo-api.onrender.com/health",
                String.class
            );
        } catch (Exception ignored) {}
    }
}