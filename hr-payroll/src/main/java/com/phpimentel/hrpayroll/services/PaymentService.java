package com.phpimentel.hrpayroll.services;

import com.phpimentel.hrpayroll.entities.Payment;
import com.phpimentel.hrpayroll.entities.dto.Worker;
import com.phpimentel.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(Long workerId, int days) {

        Worker worker = this.workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
