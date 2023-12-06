package com.yoramu.controller;

import com.yoramu.model.Feedback;
import com.yoramu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class FeedbackCont {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping("/feedbacks")
    String feedbacks(Model model){
        model.addAttribute("feedbackModel", new Feedback());
        model.addAttribute("listFeedbacks", feedbackService.listFeedbacks());
        return "feedbacks";
    }

    @PostMapping("/deleteFeedback")
    String deleteDonation(@ModelAttribute Feedback feedback){
        feedbackService.deleteFeedback(feedback);
        return "redirect:feedbacks";
    }
}
