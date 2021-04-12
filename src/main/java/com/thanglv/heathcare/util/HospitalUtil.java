package com.thanglv.heathcare.util;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */

import com.thanglv.heathcare.dao.Appointment;
import com.thanglv.heathcare.dao.HospitalDAO;
import com.thanglv.heathcare.model.AppointmentRequest;
import com.thanglv.heathcare.model.Doctor;

import java.util.Calendar;

public class HospitalUtil {
    public static int appointmentNumber = 1;

    public static Appointment makeNewAppointment(AppointmentRequest appointmentRequest, HospitalDAO hospitalDAO) {
        Appointment newAppointment = new Appointment();
        Doctor doctor = hospitalDAO.findDoctorByName(appointmentRequest.getDoctor());
        if (doctor == null || !doctor.getHospital().equalsIgnoreCase(appointmentRequest.getHospital()))
            return null;
        newAppointment.setAppointmentNumber(appointmentNumber++);
        newAppointment.setDoctor(doctor);
        newAppointment.setPatient(appointmentRequest.getPatient());
        newAppointment.setFee(doctor.getFee());
        newAppointment.setConfirmed(false);
        return newAppointment;
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

    public static boolean checDiscountEligibility(String dob) {
        int yob = Integer.parseInt(dob.split("-")[0]);
        int currentYear = Calendar.getInstance().get(1);
        int age = currentYear - yob;
        if (age < 12 || age > 55)
            return true;
        return false;
    }
}
