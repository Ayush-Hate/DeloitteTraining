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

import com.bloodbank.project.model.Donor;
import com.bloodbank.project.repository.DonorRepository;

@Controller
@RequestMapping("/donorRegistration")
public class donorSessionController {

	@Autowired
	private DonorRepository donorRepository;
	
	//LOGIN
	@PostMapping("/home")
	public String loginDonor(@RequestParam("email") String email, HttpServletRequest request) {
		Donor donorObj = donorRepository.findByEmail(email);
		if(donorObj != null)
		{
			request.getSession().setAttribute("email", email);
			return "redirect:/donorDashboard";
		}
		else
			return "redirect:/home";
	}
	
	//LOGOUT
	@PostMapping("/donorDashboard")
	public String logoutDonor(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/home";
	}

}
