package com.thanglv.heathcare.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thanglv.heathcare.model.AppointmentRequest;
import com.thanglv.heathcare.model.Doctor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */

@RestController
@RequestMapping("/grandoaks/categories")
public class GrandOakHospitalController extends HospitalController {

    public GrandOakHospitalController() {
        this.doctorsList.add(new Doctor("thomas collins", "grand oak community hospital", "surgery", "9.00 a.m - 11.00 a.m", 7000.0D)); // bác sĩ phẫu thuật
        this.doctorsList.add(new Doctor("henry parker", "grand oak community hospital", "ent", "9.00 a.m - 11.00 a.m", 4500.0D)); // bác sĩ tai mũi họng
        this.doctorsList.add(new Doctor("abner jones", "grand oak community hospital", "gynaecology", "8.00 a.m - 10.00 a.m", 11000.0D)); // bác sĩ phụ khoa
        this.doctorsList.add(new Doctor("abner jones", "grand oak community hospital", "ent", "8.00 a.m - 10.00 a.m", 6750.0D));
    }

    @PostMapping(value = "/{category}/reserve", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String reserveAppointment(@RequestBody AppointmentRequest appointmentRequest, @PathVariable("category") String category) throws JsonProcessingException {
        String dataResponse = super.reserveAppointment(appointmentRequest, category);
        System.out.println("http://localhost:9090/grandoaks/categories/{uri.var.category}/reserve");
        System.out.println("RESPONSE: " + dataResponse);
        return dataResponse;
    }

    @GetMapping("/appointments/{appointment_id}/fee")
    public ResponseEntity checkChannellingFee(@PathVariable("appointment_id") int id) {
        System.out.println("appointment_id : " + id);
        return super.checkChannellingFee(id);
    }
}
