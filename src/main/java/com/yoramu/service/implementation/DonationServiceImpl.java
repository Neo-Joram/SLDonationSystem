package com.yoramu.service.implementation;

import com.yoramu.model.Donation;
import com.yoramu.repo.DonationRepo;
import com.yoramu.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
    @Autowired
    DonationRepo donationRepo;

    @Override
    public Donation addDonation(Donation donation) {
        return donationRepo.save(donation);
    }

    @Override
    public List<Donation> listDonations() {
        return donationRepo.findAll();
    }

    @Override
    public void deleteDonation(Donation donation) {
        donationRepo.delete(donation);
    }
}
