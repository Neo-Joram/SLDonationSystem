package com.yoramu.service.implementation;

import com.yoramu.model.Patient;
import com.yoramu.repo.PatientRepo;
import com.yoramu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepo patientRepo;

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepo.save(patient);
    }

    @Override
    public List<Patient> listPatients() {
        return patientRepo.findAll();
    }

    @Override
    public void deletePatient(Patient patient) {
        patientRepo.delete(patient);
    }
}
