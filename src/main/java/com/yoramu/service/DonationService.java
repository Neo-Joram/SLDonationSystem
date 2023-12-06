package com.yoramu.service;

import com.yoramu.model.Donation;

import java.util.List;

public interface DonationService {
    Donation addDonation(Donation donation);
    List<Donation> listDonations();
    void deleteDonation(Donation donation);
}
