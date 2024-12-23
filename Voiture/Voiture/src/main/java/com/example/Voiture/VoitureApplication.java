package com.example.Voiture;

import com.example.Voiture.entities.Client;
import com.example.Voiture.entities.Voiture;
import com.example.Voiture.repositories.VoitureRepository;
import com.example.Voiture.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.Voiture.services")
public class VoitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoitureApplication.class, args);
	}

	@Bean
	CommandLineRunner initialiserBaseH2(VoitureRepository voitureRepository, ClientService clientService) {
		return args -> {
			try {
				// Fetch client details using Feign Client
				Client c1 = clientService.getClientById(1L); // Example client with ID 1
				Client c2 = clientService.getClientById(2L); // Example client with ID 2

				// Save sample voitures in the repository
				voitureRepository.save(new Voiture(null, "bmw", "C 292292", "520d", 1L, c1));
				voitureRepository.save(new Voiture(null, "audi", "B 44832", "a5", 1L, c1));
				voitureRepository.save(new Voiture(null, "mercedes", "H 29222", "c220d", 2L, c2));
			} catch (Exception e) {
				System.err.println("Erreur lors de la récupération des clients : " + e.getMessage());
			}
		};
	}
}
