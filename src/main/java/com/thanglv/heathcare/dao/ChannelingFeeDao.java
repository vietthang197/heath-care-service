package com.thanglv.heathcare.dao;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */

public class ChannelingFeeDao {
    String patientName;

    String doctorName;

    String actualFee;

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getActualFee() {
        return this.actualFee;
    }

    public void setActualFee(String actualFee) {
        this.actualFee = actualFee;
    }
}
