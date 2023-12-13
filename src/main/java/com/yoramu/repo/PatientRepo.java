package com.yoramu.repo;

import com.yoramu.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientRepo extends JpaRepository<Patient, UUID> {
    List<Patient> findAllByNamesContainingIgnoreCaseOrTreatmentContainingIgnoreCaseOrSicknessContainingIgnoreCase(
            String names, String treatment, String sickness
    );
}
