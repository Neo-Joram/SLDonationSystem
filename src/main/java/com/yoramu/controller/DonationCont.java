package com.yoramu.controller;

import com.yoramu.model.Donation;
import com.yoramu.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DonationCont {
    @Autowired
    DonationService donationService;

    @GetMapping("/donations")
    String donations(Model model){
        model.addAttribute("donationModel", new Donation());
        model.addAttribute("listDonation", donationService.listDonations());
        return "donations";
    }

    @PostMapping("/deleteDonation")
    String deleteDonation(@ModelAttribute Donation donation){
        donationService.deleteDonation(donation);
        return "redirect:donations";
    }
}
