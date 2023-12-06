package com.yoramu.service;

import com.yoramu.model.Patient;

import java.util.List;

public interface PatientService {
    Patient addPatient(Patient patient);
    List<Patient> listPatients();
    void deletePatient(Patient patient);
}
