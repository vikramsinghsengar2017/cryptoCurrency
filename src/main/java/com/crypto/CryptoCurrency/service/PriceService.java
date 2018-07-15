package com.crypto.CryptoCurrency.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.crypto.CryptoCurrency.client.PricesRestClient;
import com.crypto.CryptoCurrency.repository.PricesRepository;
import com.crypto.CryptoCurrency.utils.SendMail;
import com.crypto.CryptoCurrency.v1.output.EnableDisableAlertOutput;
import com.crypto.CryptoCurrency.v1.output.PricesCryptoOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PriceService {

    @Autowired
    private PricesRestClient pricesRestClient;

    @Autowired
    private PricesRepository pricesRepository;

    public ResponseEntity<PricesCryptoOutput[]> getInformation(String id) {
        return pricesRestClient.getCryptoInformation(id);
    }

    public String enableDisableAlert(String id, double alertPrice, boolean enableDisable) {
        pricesRepository.enableDisableAlert(enableDisable);
        if(enableDisable)
            initMonitoringOfPrice(alertPrice, id);
        return new EnableDisableAlertOutput(enableDisable, new Date()).toString();
    }

    public String initMonitoringOfPrice(double alertPrice, String id) {
        System.out.println("===> Monitoring price <===");
        List<PricesCryptoOutput> info = Arrays.asList(pricesRestClient.getCryptoInformation(id).getBody());
        info.forEach(item->{
            if(Double.parseDouble(item.getPrice_usd()) > alertPrice) {
                alertUser(Double.parseDouble(item.getPrice_usd()), id);
            }
        });
        return info.toString();
    }

    private void alertUser(double alertPrice, String id) {
        System.out.println("New price of "+id+" is "+alertPrice);
        SendMail.sendMail(alertPrice);
    }
}