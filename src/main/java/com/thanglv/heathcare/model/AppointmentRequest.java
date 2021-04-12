package com.thanglv.heathcare.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */

@XmlRootElement
public class AppointmentRequest {

    private Patient patient;

    private String doctor;

    private String hospital;

    public AppointmentRequest(Patient patient, String doctor, String hospital) {
        this.patient = patient;
        this.doctor = doctor;
        this.hospital = hospital;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return this.doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospital() {
        return this.hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
