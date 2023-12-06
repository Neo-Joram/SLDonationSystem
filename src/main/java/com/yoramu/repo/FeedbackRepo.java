package com.yoramu.repo;

import com.yoramu.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepo extends JpaRepository<Feedback, UUID> {
}
