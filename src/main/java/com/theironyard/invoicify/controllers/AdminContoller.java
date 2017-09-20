package com.theironyard.invoicify.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.repositories.CompanyRepository;
import com.theironyard.invoicify.repositories.InvoiceRepository;

@Controller
@RequestMapping("/admin")
public class AdminContoller {
	
	private CompanyRepository companyRepo;
	private InvoiceRepository invoiceRepo;
	
	public AdminContoller(CompanyRepository companyRepo, InvoiceRepository invoiceRepo) {
		this.companyRepo = companyRepo;
		this.invoiceRepo = invoiceRepo;
	}
	
	public int getCounts(String companyName) {
		return invoiceRepo.countByCompanyName(companyName);
	}
	
	@GetMapping("/companies")
	public ModelAndView showAddCompany() {
		ModelAndView mv = new ModelAndView("/admin/companies");
		List<Company> companies = companyRepo.findAllByOrderByNameAsc();
		
		for (Company co: companies) {
			co.setInvoiceCounts(invoiceRepo.countByCompanyName(co.getName()));
		}
		
		mv.addObject("companies", companies );
		return mv;
	}
	
	@PostMapping("/companies")
	public String addNewcompany(Company company) {
		companyRepo.save(company);
		return "redirect:/admin/companies";
	}

}
