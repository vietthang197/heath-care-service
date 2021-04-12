package com.thanglv.heathcare.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanglv.heathcare.dao.HealthcareDao;
import com.thanglv.heathcare.model.Payment;
import com.thanglv.heathcare.model.PaymentSettlement;
import com.thanglv.heathcare.model.Status;
import com.thanglv.heathcare.util.HealthCareUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */

@RestController
@RequestMapping("/healthcare")
@Slf4j
public class HealthCareController {

    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/payments")
    public ResponseEntity settlePayment(@RequestBody PaymentSettlement paymentSettlement) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString(paymentSettlement));
        if (paymentSettlement.getAppointmentNumber() >= 0) {
            Payment payment = HealthCareUtil.createNewPaymentEntry(paymentSettlement);
            payment.setStatus("Settled");
            payment.setAppointmentNo(paymentSettlement.getAppointmentNumber());
            payment.setDoctorName(paymentSettlement.getDoctor().getName());
            HealthcareDao.payments.put(payment.getPaymentID(), payment);
            return ResponseEntity.status(HttpStatus.OK).body(payment);
        }
        Status status = new Status("Error.Could not Find the Requested appointment ID");
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
