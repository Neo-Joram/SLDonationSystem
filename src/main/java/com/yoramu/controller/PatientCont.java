package com.yoramu.controller;

import com.yoramu.model.Patient;
import com.yoramu.service.PatientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class PatientCont {
    @Autowired
    PatientService patientService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";
//    @Value("${upload.directory}")
//    private String UPLOAD_DIRECTORY;

    @GetMapping
    String patientGetterSlash(Model model, HttpSession session){
        model.addAttribute("patientModel", new Patient());
        model.addAttribute("listPatients", patientService.listPatients());
        return "patients";
    }
    @GetMapping("/patients")
    String patientGetter(Model model, @RequestParam(value = "search", required = false) String search){
        model.addAttribute("patientModel", new Patient());
        model.addAttribute("listPatients", patientService.searchPatients(search, search, search));
        return "patients";
    }

    @PostMapping("/addPatient")
    String addPatient(@ModelAttribute Patient patient, @RequestParam("image") MultipartFile file, @RequestParam("action") String action, @RequestParam("pic") String img) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        if(action.equals("Update")){
            patient.setId(patient.getId());
            if(file.getSize() != 0){
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename());
                Files.write(fileNameAndPath, file.getBytes());

                patient.setPicture(fileNames.toString());
            }else {
                patient.setPicture(img);
            }
            patientService.addPatient(patient);
        }else if(action.equals("Delete")){
            patient.setId(patient.getId());
            patientService.deletePatient(patient);
        }else{
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());

            patient.setId(UUID.randomUUID());
            patient.setPicture(fileNames.toString());
            patientService.addPatient(patient);
        }
        return "redirect:patients";
    }
}
