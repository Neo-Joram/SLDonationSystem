package com.yoramu.service.implementation;

import com.yoramu.model.Patient;
import com.yoramu.repo.PatientRepo;
import com.yoramu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    @Override
    public List<Patient> searchPatients(String names, String treatment, String sickness) {
        if(names != null || treatment != null || sickness != null) {
            return patientRepo.findAllByNamesContainingIgnoreCaseOrTreatmentContainingIgnoreCaseOrSicknessContainingIgnoreCase(names, treatment, sickness);
        }else{
            return patientRepo.findAll();
        }
    }

    @Override
    public Page<Patient> findPaginated(Pageable pageable, String names, String treatment, String sickness) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Patient> patients;
        if(names != null || treatment != null || sickness != null) {
            patients = patientRepo.findAllByNamesContainingIgnoreCaseOrTreatmentContainingIgnoreCaseOrSicknessContainingIgnoreCase(names, treatment, sickness);
        }else{
            patients = patientRepo.findAll();
        }

        List<Patient> list;

        if (patients.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, patients.size());
            list = patients.subList(startItem, toIndex);
        }

        return new PageImpl<Patient>(list, PageRequest.of(currentPage, pageSize), patients.size());
    }
}
