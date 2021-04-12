package com.thanglv.heathcare.model;

import java.util.UUID;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */
public class Payment {
    private String patient;

    private double actualFee;

    private int discount;

    private double discounted;

    private String paymentID = UUID.randomUUID().toString();

    private String status;

    private int appointmentNo;

    private String doctorName;

    public String getPatient() {
        return this.patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public double getActualFee() {
        return this.actualFee;
    }

    public void setActualFee(double actualFee) {
        this.actualFee = actualFee;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getDiscounted() {
        return this.discounted;
    }

    public void setDiscounted(double discounted) {
        this.discounted = discounted;
    }

    public String getPaymentID() {
        return this.paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(int appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
