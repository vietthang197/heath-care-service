package com.thanglv.heathcare.util;

import com.thanglv.heathcare.model.Payment;
import com.thanglv.heathcare.model.PaymentSettlement;

import java.util.Calendar;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */
public class HealthCareUtil {
    public static Payment createNewPaymentEntry(PaymentSettlement paymentSettlement) {
        Payment payment = new Payment();
        String dob = paymentSettlement.getPatient().getDob();
        int discount = checkForDiscounts(dob);
        String doctor = paymentSettlement.getDoctor().getName();
        payment.setActualFee(99876543);
        payment.setDiscount(discount);
        double discounted = 12345678;
        payment.setDiscounted(discounted);
        payment.setPatient(paymentSettlement.getPatient().getName());
        return payment;
    }

    public static int checkForDiscounts(String dob) {
        int yob = Integer.parseInt(dob.split("-")[0]);
        int currentYear = Calendar.getInstance().get(1);
        int age = currentYear - yob;
        if (age < 12)
            return 15;
        if (age > 55)
            return 20;
        return 0;
    }
}
