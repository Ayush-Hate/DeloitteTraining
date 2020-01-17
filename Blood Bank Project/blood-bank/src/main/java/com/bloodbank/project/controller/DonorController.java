package com.bloodbank.project.controller;

import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.project.model.BloodStock;
import com.bloodbank.project.model.Donor;
import com.bloodbank.project.repository.BloodStockRepository;
import com.bloodbank.project.repository.DonorRepository;


@Controller
@RequestMapping("/dashboardDonor")//DONOR PAGE LINK HERE
public class DonorController {

	@Autowired
	private DonorRepository donorRepository;
	
	@Autowired
	private BloodStockRepository bloodStockRepository;
	
	//DONATE BLOOD
	@PostMapping("/donate") //add form link <- REFACTOR HTML
	public String createBloodStock(@Valid @RequestBody BloodStock bloodStock, HttpServletRequest request)  {
		String email = (String) request.getSession().getAttribute("email");
		Donor currentDonor = donorRepository.findByEmail(email);
		bloodStock.setDonor(currentDonor);
		bloodStock.setDonationDate(new Date());
		bloodStock.setBloodgroup(currentDonor.getBloodgroup());
		bloodStockRepository.save(bloodStock);
		return "redirect:/dashboardDonor";
	}

	
	//READ
	@GetMapping("/") //Add Listing Page Link
	public String findByEmail(String email, Model model) {
		model.addAttribute("donorList", donorRepository.findByEmail(email));
		return "redirect:/dashboardDonor";
	}//use the donorList object in the donorDashboard html page to populate the table
	
	
	//UPDATE TO DO LATER
	@PutMapping("/update") //Add update page link
	public String updateDonor(@PathVariable(value="donorId") Long donorId, @Valid @RequestBody Donor d) {
		Optional<Donor> donor = donorRepository.findById(donorId);
		if(donor.isPresent())
		{
			donor.get().setFirstName(d.getFirstName());
			donor.get().setLastName(d.getLastName());
			donor.get().setPhone(d.getPhone());
			donor.get().setEmail(d.getEmail());
			donor.get().setDateOfBirth(d.getDateOfBirth());
			donor.get().setBloodgroup(d.getBloodgroup());
			donor.get().setWeight(d.getWeight());
			d = donorRepository.save(donor.get());
		}
		return "redirect:/dashboardDonor";
	}
//	//DELETE
//	@DeleteMapping("")//link for deleting a donor
//	public ResponseEntity<?> deleteDonor(@PathVariable(value="donorId") Long donorId) {
//		Optional<Donor> donor = donorRepository.findById(donorId);
//		if(donor.isPresent())
//		{
//			donorRepository.delete(donor.get());
//		}
//			return ResponseEntity.ok().build();
//	}
}
