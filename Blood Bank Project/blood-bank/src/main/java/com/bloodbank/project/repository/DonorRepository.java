package com.bloodbank.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodbank.project.model.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {
	
	Donor findByEmail(String email);//returns the Donor record for that email. from this match the password
}
