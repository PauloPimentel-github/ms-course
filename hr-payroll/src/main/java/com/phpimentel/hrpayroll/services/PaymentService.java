package com.phpimentel.hrpayroll.services;

import com.phpimentel.hrpayroll.entities.Payment;
import com.phpimentel.hrpayroll.entities.dto.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String hrWorkerHost;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(Long workerId, int days) {
        Map<String, String> params = new HashMap<>();
        params.put("id", workerId.toString());
        StringBuilder url = new StringBuilder(this.hrWorkerHost);
        url.append("/workers/{id}");

        Worker worker = this.restTemplate.getForObject(url.toString(), Worker.class, params);
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
