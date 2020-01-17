package com.bloodbank.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodbank.project.model.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
	Recipient findByEmail(String email);//returns the Recp record for that email. from this match the password

}
