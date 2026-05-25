package com.psk_1.psk12.service;

import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LongCalculationService {

    @Async
    public void performLongCalculation() {

        System.out.println("Ilgas skaičiavimas pradėtas...");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Skaičiavimas baigtas.");
    }
}