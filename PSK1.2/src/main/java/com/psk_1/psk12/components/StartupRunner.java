package com.psk_1.psk12.components;

import com.psk_1.psk12.service.LongCalculationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final LongCalculationService service;

    public StartupRunner(LongCalculationService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {

        System.out.println("Programa startavo.");

        // Paleidžiama asinchroniškai
        service.performLongCalculation();

        System.out.println("Pagrindinė gija tęsia darbą.");
    }
}