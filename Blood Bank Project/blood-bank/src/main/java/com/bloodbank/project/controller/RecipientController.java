package com.bloodbank.project.controller;

import java.util.List;
import java.util.Optional;

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
import com.bloodbank.project.model.Recipient;
import com.bloodbank.project.repository.BloodStockRepository;
import com.bloodbank.project.repository.RecipientRepository;

@Controller
@RequestMapping("/recipientDashboard")//RECIPIENT PAGE CONTROLLER LINK HERE
public class RecipientController {

	@Autowired
	private RecipientRepository recipientRepository;
	
	@Autowired
	private BloodStockRepository bloodStockRepository;
	
	//REQUEST BLOOD
	@GetMapping("/request/{bloodgroup}") //Add Listing Page Link
	public String requestBlood(@PathVariable(value="bloodgroup") String bloodgroup, Model model) { //make sure to filter the attribute when displaying
		model.addAttribute("donations", bloodStockRepository.findByBloodgroupOrderByDonationDateDesc(bloodgroup));
		return "redirect:/recipientDashboardRequest";
	}

		
	//CREATE
	@PostMapping("/") //MOVE THIS TO SESSION CONTROLLER
	public Recipient createRecipient(@Valid @RequestBody Recipient recipient)  {
		recipientRepository.save(recipient);
		return recipient;
	}
	
	
	//READ
	@GetMapping("/") //Add Listing Page Link
	public String findByEmail(@PathVariable(value="email") String email, Model model) {
		model.addAttribute("recipientList", recipientRepository.findByEmail(email));
		return "redirect:/recipientDashboard";
	}//use the recipientList object in the recipientDashboard html page to populate the table
	//UPDATE
	@PutMapping("/") //Add update page link
	public Recipient updateRecipient(@PathVariable(value="recipientId") Long recipientId, @Valid @RequestBody Recipient r) {
		Optional<Recipient> recipient = recipientRepository.findById(recipientId);
		if(recipient.isPresent())
		{
			recipient.get().setFirstName(r.getFirstName());
			recipient.get().setLastName(r.getLastName());
			recipient.get().setPhone(r.getPhone());
			recipient.get().setEmail(r.getEmail());
			recipient.get().setDateOfBirth(r.getDateOfBirth());
			recipient.get().setBloodgroup(r.getBloodgroup());
			recipient.get().setWeight(r.getWeight());
			r = recipientRepository.save(recipient.get());
		}
		return r;
	}
		
}
