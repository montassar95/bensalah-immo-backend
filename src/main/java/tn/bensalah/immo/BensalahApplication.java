package tn.bensalah.immo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BensalahApplication {

	public static void main(String[] args) {
		SpringApplication.run(BensalahApplication.class, args);
	}

}
