package com.yoramu.service;

import com.yoramu.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback addFeedback(Feedback feedback);
    List<Feedback> listFeedbacks();
    void deleteFeedback(Feedback feedback);
}
