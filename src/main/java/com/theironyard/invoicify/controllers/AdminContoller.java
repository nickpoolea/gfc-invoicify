package com.theironyard.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.repositories.CompanyRepository;

@Controller
@RequestMapping("/admin")
public class AdminContoller {
	
	private CompanyRepository companyRepo;
	
	public AdminContoller(CompanyRepository companyRepo) {
		this.companyRepo = companyRepo;
	}
	
	@GetMapping("/companies")
	public ModelAndView showAddCompany() {
		ModelAndView mv = new ModelAndView("/admin/companies");
		mv.addObject("companies", companyRepo.findAllByOrderByNameAsc());
		return mv;
	}
	
	@PostMapping("/companies")
	public String addNewcompany(Company company) {
		companyRepo.save(company);
		return "redirect:/admin/companies";
	}

}
