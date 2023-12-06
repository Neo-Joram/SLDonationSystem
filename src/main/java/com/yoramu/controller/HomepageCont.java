package com.yoramu.controller;

import com.yoramu.model.Donation;
import com.yoramu.model.Feedback;
import com.yoramu.service.DonationService;
import com.yoramu.service.FeedbackService;
import com.yoramu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class HomepageCont {
    @Autowired
    PatientService patientService;
    @Autowired
    DonationService donationService;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    FeedbackService feedbackService;

    @Value("${spring.mail.username}")
    String sender;

    @GetMapping("/")
    String homepage(Model model){
        model.addAttribute("welcomeMsg", "Welcome to SaveLife Donation System");
        model.addAttribute("listPatients", patientService.listPatients());
        model.addAttribute("donationModel", new Donation());
        model.addAttribute("feedbackModel", new Feedback());
        return "homepage";
    }



    @PostMapping("/addDonation")
    String addDonation(@ModelAttribute Donation donation) throws Exception{
        donation.setId(UUID.randomUUID());
        donationService.addDonation(donation);

        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(donation.getEmail());
        mailMessage.setText("Thank you for donating " + donation.getAmount() + "RWF ");
        mailMessage.setSubject("Thank you (Donation) *.*");

        javaMailSender.send(mailMessage);
        return "redirect:/";
    }

    @PostMapping("/addFeedback")
    String addFeedback(@ModelAttribute Feedback feedback){
        feedback.setId(UUID.randomUUID());
        feedbackService.addFeedback(feedback);

        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(feedback.getEmail());
        mailMessage.setText("Thank you for your feedback \n\n " + feedback.getMessage());
        mailMessage.setSubject("Thank you (Feedback) *.*");

        javaMailSender.send(mailMessage);
        return "redirect:/";
    }
}
