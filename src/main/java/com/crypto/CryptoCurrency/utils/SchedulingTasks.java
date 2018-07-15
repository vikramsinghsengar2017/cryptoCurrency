package com.crypto.CryptoCurrency.utils;

import com.crypto.CryptoCurrency.repository.PricesRepository;
import com.crypto.CryptoCurrency.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SchedulingTasks {

    @Autowired
    private PriceService priceService;

    @Autowired
    private PricesRepository pricesRepository;

    private final long SECOND = 1000;
    private final long MINUTE = SECOND * 60;

    @Scheduled(fixedDelay = MINUTE)
    public void reportPrice() {
        if(pricesRepository.getValueAlert().isStatus()) {
            priceService.initMonitoringOfPrice(pricesRepository.getValueAlert().getPrice(), pricesRepository.getValueAlert().getId());
        }
    }

}