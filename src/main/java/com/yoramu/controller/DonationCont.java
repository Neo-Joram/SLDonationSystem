package com.yoramu.controller;

import com.yoramu.model.Donation;
import com.yoramu.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class DonationCont {
    @Autowired
    DonationService donationService;

    @GetMapping("/donations")
    String donations(Model model, @RequestParam(value = "search", required = false) String search){
        model.addAttribute("donationModel", new Donation());
        model.addAttribute("listDonation", donationService.searchDonations(search));
        return "donations";
    }

    @PostMapping("/deleteDonation")
    String deleteDonation(@ModelAttribute Donation donation){
        donationService.deleteDonation(donation);
        return "redirect:donations";
    }
}
