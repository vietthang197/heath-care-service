package com.thanglv.heathcare.dao;

/**
 * @author thanglv on 4/12/2021
 * @project NBD
 */
import com.thanglv.heathcare.model.Doctor;
import com.thanglv.heathcare.model.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HealthcareDao {
    public static List<Doctor> doctorsList = new ArrayList<>();

    public static List<String> catergories = new ArrayList<>();

    public static HashMap<String, Payment> payments = new HashMap<>();

    public static List<Doctor> findDoctorByCategory(String category) {
        List<Doctor> list = new ArrayList<>();
        for (Doctor doctor : doctorsList) {
            if (category.equals(doctor.getCategory()))
                list.add(doctor);
        }
        return list;
    }

    public static Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctorsList) {
            if (doctor.getName().equals(name))
                return doctor;
        }
        return null;
    }
}
