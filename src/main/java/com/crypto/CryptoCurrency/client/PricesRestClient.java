package com.crypto.CryptoCurrency.client;

import com.crypto.CryptoCurrency.v1.output.PricesCryptoOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PricesRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<PricesCryptoOutput[]> getCryptoInformation(String id) {
        System.out.println("API CoinMarketCap");
        return restTemplate.getForEntity("https://api.coinmarketcap.com/v1/ticker/"+id, PricesCryptoOutput[].class);
    }
}