package com.thanglv.heathcare.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanglv.heathcare.dao.Appointment;
import com.thanglv.heathcare.dao.ChannelingFeeDao;
import com.thanglv.heathcare.dao.HospitalDAO;
import com.thanglv.heathcare.dao.PatientRecord;
import com.thanglv.heathcare.model.AppointmentRequest;
import com.thanglv.heathcare.model.Doctor;
import com.thanglv.heathcare.model.Patient;
import com.thanglv.heathcare.model.Status;
import com.thanglv.heathcare.util.HospitalUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */
public class HospitalController {

    private ObjectMapper mapper = new ObjectMapper();

    private static Map<Integer, Appointment> appointments = new HashMap<>();

    private HospitalDAO hospitalDAO = new HospitalDAO();

    List<String> catergories = this.hospitalDAO.getCatergories();

    List<Doctor> doctorsList = this.hospitalDAO.getDoctorsList();

    public HospitalController() {
        this.catergories.add("surgery");
        this.catergories.add("cardiology");
        this.catergories.add("gynaecology");
        this.catergories.add("ent");
        this.catergories.add("paediatric");
    }

    public String reserveAppointment(AppointmentRequest appointmentRequest, String category) throws JsonProcessingException {
        if (this.hospitalDAO.getCatergories().contains(category)) {
            Appointment appointment = HospitalUtil.makeNewAppointment(appointmentRequest, this.hospitalDAO);
            if (appointment == null) {
                Status status1 = new Status("Doctor " + appointmentRequest.getDoctor() + " is not available in " + appointmentRequest.getHospital());
                return mapper.writeValueAsString(status1);
            }

            appointments.put(Integer.valueOf(appointment.getAppointmentNumber()), appointment);
            this.hospitalDAO.getPatientMap().put(appointmentRequest.getPatient().getSsn(), appointmentRequest.getPatient());
            if (!this.hospitalDAO.getPatientRecordMap().containsKey(appointmentRequest.getPatient().getSsn())) {
                PatientRecord patientRecord = new PatientRecord(appointmentRequest.getPatient());
                this.hospitalDAO.getPatientRecordMap().put(appointmentRequest.getPatient().getSsn(), patientRecord);
            }
            return mapper.writeValueAsString(appointment);
        }
        Status status = new Status("Invalid Category");
        return mapper.writeValueAsString(status);
    }

    public ResponseEntity checkChannellingFee(int id) {
        ChannelingFeeDao channelingFee = new ChannelingFeeDao();
        if (appointments.containsKey(Integer.valueOf(id))) {
            Patient patient = ((Appointment)appointments.get(Integer.valueOf(id))).getPatient();
            Doctor doctor = ((Appointment)appointments.get(Integer.valueOf(id))).getDoctor();
            channelingFee.setActualFee(Double.toString(doctor.getFee()));
            channelingFee.setDoctorName(doctor.getName().toLowerCase());
            channelingFee.setPatientName(patient.getName().toLowerCase());
            return ResponseEntity.status(HttpStatus.OK).body(channelingFee);
        }
        Status status = new Status("Error.Could not Find the Requested appointment ID");
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

//    public Response updatePatientRecord(HashMap<String, Object> patientDetails) {
//        String SSN = (String)((Map)patientDetails.get("patient")).get("ssn");
//        List symptoms = (List)patientDetails.get("symptoms");
//        List treatments = (List)patientDetails.get("treatments");
//        if (this.hospitalDAO.getPatientMap().get(SSN) != null) {
//            Patient patient = (Patient)this.hospitalDAO.getPatientMap().get(SSN);
//            PatientRecord patientRecord = (PatientRecord)this.hospitalDAO.getPatientRecordMap().get(SSN);
//            if (patient != null) {
//                patientRecord.updateSymptoms(symptoms);
//                patientRecord.updateTreatments(treatments);
//                Status status2 = new Status("Record Update Success");
//                return Response.status(Response.Status.OK).entity(status2).type("application/json").build();
//            }
//            Status status1 = new Status("Could not find valid Patient Record");
//            return Response.status(Response.Status.OK).entity(status1).type("application/json").build();
//        }
//        Status status = new Status("Could not find valid Patient Entry");
//        return Response.status(Response.Status.OK).entity(status).type("application/json").build();
//    }
//
//    public Response getPatientRecord(@PathParam("SSN") String SSN) {
//        PatientRecord patientRecord = (PatientRecord)this.hospitalDAO.getPatientRecordMap().get(SSN);
//        if (patientRecord == null) {
//            Status status = new Status("Could not find valid Patient Entry");
//            return Response.status(Response.Status.OK).entity(status).type("application/json").build();
//        }
//        return Response.status(Response.Status.OK).entity(patientRecord).type("application/json").build();
//    }
//
//    public Response isEligibleForDiscount(@PathParam("appointment_id") int id) {
//        Appointment appointment = appointments.get(Integer.valueOf(id));
//        if (appointment == null) {
//            Status status1 = new Status("Invalid appointment ID");
//            return Response.status(Response.Status.OK).entity(status1).type("application/json").build();
//        }
//        boolean eligible = HospitalUtil.checDiscountEligibility(appointment.getPatient().getDob());
//        Status status = new Status(String.valueOf(eligible));
//        return Response.status(Response.Status.OK).entity(status).type("application/json").build();
//    }
//
//    public Response addNewDoctor(Doctor doctor) {
//        String category = doctor.getCategory();
//        if (!this.catergories.contains(category))
//            this.catergories.add(category);
//        if (this.hospitalDAO.findDoctorByName(doctor.getName()) == null) {
//            this.doctorsList.add(doctor);
//            Status status1 = new Status("New Doctor Added Successfully");
//            return Response.status(Response.Status.OK).entity(status1).type("application/json").build();
//        }
//        Status status = new Status("Doctor Already Exist in the system");
//        return Response.status(Response.Status.OK).entity(status).type("application/json").build();
//    }

    public static Map<Integer, Appointment> getAppointments() {
        return appointments;
    }

    public static void setAppointments(Map<Integer, Appointment> appointments) {
        HospitalController.appointments = appointments;
    }
}
