package com.bloodbank.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodbank.project.model.BloodStock;

import java.util.*;

public interface BloodStockRepository extends JpaRepository<BloodStock, Long> {
	
	List<BloodStock> findByBloodgroupOrderByDonationDateDesc(String bloodgroup);
	
}
