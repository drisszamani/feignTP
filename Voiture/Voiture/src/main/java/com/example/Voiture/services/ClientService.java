package com.example.Voiture.services;

import com.example.Voiture.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-CLIENT", url = "http://localhost:8088")
public interface ClientService {
    @GetMapping("/clients/{id}")
    Client getClientById(@PathVariable("id") Long id);
}