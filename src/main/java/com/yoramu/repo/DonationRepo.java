package com.yoramu.repo;

import com.yoramu.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DonationRepo extends JpaRepository<Donation, UUID> {
}
