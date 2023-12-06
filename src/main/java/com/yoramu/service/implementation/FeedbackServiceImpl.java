package com.yoramu.service.implementation;

import com.yoramu.model.Feedback;
import com.yoramu.repo.FeedbackRepo;
import com.yoramu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepo feedbackRepo;

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    @Override
    public List<Feedback> listFeedbacks() {
        return feedbackRepo.findAll();
    }

    @Override
    public void deleteFeedback(Feedback feedback) {
        feedbackRepo.delete(feedback);
    }
}
