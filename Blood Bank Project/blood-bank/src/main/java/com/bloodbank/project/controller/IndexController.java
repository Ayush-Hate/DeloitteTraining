package com.bloodbank.project.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.project.model.BloodStock;
import com.bloodbank.project.model.Donor;
import com.bloodbank.project.model.Recipient;
import com.bloodbank.project.repository.BloodStockRepository;
import com.bloodbank.project.repository.DonorRepository;
import com.bloodbank.project.repository.RecipientRepository;

@Controller
public class IndexController {

	@Autowired
	private DonorRepository donorRepository;
	
	@Autowired
	private RecipientRepository recipientRepository;

	@Autowired
	private BloodStockRepository bloodStockRepository;

	
	
	@GetMapping({"/","/index"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/donorRegistration")
	public String donorRegistration() {
		System.err.println("inside  get...");
		return "dashboardDonor";
	}
	
	@PostMapping({"/donorRegistration"})
	public String donorRegistrationPost() {
		return "donorRegistration";
	}
	
	@PostMapping({"/register"})
	public String createDonor(@ModelAttribute("donor") Donor donor, Model model)  {
		model.addAttribute("donor",donor);
		donorRepository.save(donor);
		return "donorRegistration";
	}
	
	@PostMapping("/loginDonor")
	public String loginDonor(@ModelAttribute("email") String email, Model model, HttpServletRequest request) {
		Donor donorObj = donorRepository.findByEmail(email);
		model.addAttribute("donorObj", donorObj);
		if(donorObj != null)
		{
			request.getSession().setAttribute("email", email);
			model.addAttribute("email",email);
			return "donorDashboard";
		}
		else
		{
			model.addAttribute("email",email);
			return "dashboardDonor";
		}
	}
	
	//LOGOUT
	@PostMapping("/logoutDonor")
	public String logoutDonor(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/index";
	}

	
	//READ
	@GetMapping("/viewDonor") //Add Listing Page Link
	public String viewDonors(Model model, HttpServletRequest request) {
		String email = request.getSession().getAttribute("email").toString().trim();
		model.addAttribute("donorList", donorRepository.findByEmail(email));
		return "/donorDashboard";
	}//use the donorList object in the donorDashboard html page to populate the table

	
	//DONATE BLOOD
	@PostMapping("/donate") //add form link <- REFACTOR HTML
	public String createBloodStock(@ModelAttribute("bloodStock") BloodStock bloodStock, Model model, HttpServletRequest request)  {
		String email = (String) request.getSession().getAttribute("email");
		Donor currentDonor = donorRepository.findByEmail(email);
		bloodStock.setDonor(currentDonor);
		bloodStock.setDonationDate(new Date());
		bloodStock.setBloodgroup(currentDonor.getBloodgroup());
		bloodStockRepository.save(bloodStock);
		return "donorDashboard";
	}
	
	@PostMapping({"/recipientRegistration"})
	public String recipientRegistrationPost() {
		return "recipientRegistration";
	}

	@PostMapping({"/registerRecipient"})
	public String createRecipient(@ModelAttribute("recipient") Recipient recipient, Model model)  {
		model.addAttribute("recipient",recipient);
		recipientRepository.save(recipient);
		return "recipientRegistration";
	}

	@PostMapping("/loginRecipient")
	public String loginRecipient(@ModelAttribute("email") String email, Model model, HttpServletRequest request) {
		Recipient recipientObj = recipientRepository.findByEmail(email);
		model.addAttribute("recipientObj", recipientObj);
		if(recipientObj != null)
		{
			request.getSession().setAttribute("email", email);
			model.addAttribute("email",email);
			return "recipientDashboard";
		}
		else
		{
			model.addAttribute("email",email);
			return "recipientDashboard";
		}
	}
	
	//READ
	@GetMapping("/viewRecipient") //Add Listing Page Link
	public String viewRecipients(Model model, HttpServletRequest request) {
		String email = request.getSession().getAttribute("email").toString().trim();
		model.addAttribute("recipientList", recipientRepository.findByEmail(email));
		return "/recipientDashboard";
	}//use the donorList object in the donorDashboard html page to populate the table

	
	@GetMapping("/requestBloodView") //Add Listing Page Link
	public String viewBloodStock(Model model, HttpServletRequest request) { //make sure to send an ITERABLE of Longs (IDs)
		String email = request.getSession().getAttribute("email").toString().trim();
		Recipient recipientObj = recipientRepository.findByEmail(email);
		String bloodgroup = recipientObj.getBloodgroup();
		//ADD BLOOD GROUP LOGIC
		List<BloodStock> bloodStockList = bloodStockRepository.findByBloodgroupOrderByDonationDateDesc(bloodgroup);
		model.addAttribute("bloodStockList", bloodStockList);
		return "recipientDonateDashboard";
	}
	
	//REQUEST BLOOD
	@PostMapping("/requestBlood") //Add Listing Page Link
	public String requestBlood(@ModelAttribute("bloodStockIdList") List<BloodStock> bloodStockIdList, Model model, HttpServletRequest request) { //make sure to filter the attribute when displaying
		return "/recipientDashboard";
	}
	
	//LOGOUT
	@PostMapping("/logoutRecipient")
	public String logoutRecipient(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/index";
	}	
	
}
