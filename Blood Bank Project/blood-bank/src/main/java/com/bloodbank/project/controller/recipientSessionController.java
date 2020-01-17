package com.bloodbank.project.controller;
import java.util.*;
import org.springframework.stereotype.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.bloodbank.project.model.Recipient;
import com.bloodbank.project.repository.RecipientRepository;

@Controller
@RequestMapping("/home")
public class recipientSessionController {
	@Autowired
	private RecipientRepository recipientRepository;
	
	//LOGIN
	@PostMapping("/home")
	public String loginRecipient(@RequestParam("email") String email, HttpServletRequest request) {
		Recipient recipientObj = recipientRepository.findByEmail(email);
		if(recipientObj != null)
		{
			request.getSession().setAttribute("email", email);
			return "redirect:/recipientDashboard";
		}
		else
			return "redirect:/home";
	}
	
	//LOGOUT
	@PostMapping("/recipientDashboard")
	public String logoutRecipient(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/home";
	}

}
