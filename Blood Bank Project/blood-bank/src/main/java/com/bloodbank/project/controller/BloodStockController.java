package com.bloodbank.project.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.project.model.BloodStock;
import com.bloodbank.project.repository.BloodStockRepository;

@RestController
@RequestMapping("") //'/api' add own link
public class BloodStockController {
	
	@Autowired
	private BloodStockRepository bloodStockRepository;
	
	//DONATE BLOOD
	@PostMapping("/donorDashboardDonate") //add form link
	public BloodStock createBloodStock(@Valid @RequestBody BloodStock bloodStock)  {
		bloodStock.setDonationDate(new Date());
		bloodStockRepository.save(bloodStock);
		return bloodStock;
	}

	//TAKE BLOOD
	@GetMapping("/") //Add Listing Page Link
	public List<BloodStock> findAllById(Iterable<Long> ids) { //make sure to send an ITERABLE of Longs (IDs)
		return bloodStockRepository.findAllById(ids);
	}
	//UPDATE NOT APPLICABLE FOR BLOOD STOCK
	
	//DELETE NOT APPLICABLE FOR BLOOD STOCK
	
	
}
