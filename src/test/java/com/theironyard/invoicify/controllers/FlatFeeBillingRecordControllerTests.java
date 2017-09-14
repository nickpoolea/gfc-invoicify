package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.FlatFeeBillingRecord;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

public class FlatFeeBillingRecordControllerTests {
	
	private FlatFeeBillingRecordController controller;
	private BillingRecordRepository billingRepo;
	private CompanyRepository companyRepo;
	private Authentication auth;
	private User user;
	private Company company;
	
	@Before
	public void setup() {
		billingRepo = mock(BillingRecordRepository.class);
		companyRepo = mock(CompanyRepository.class);
		auth = mock(Authentication.class);
		controller = new FlatFeeBillingRecordController(billingRepo, companyRepo);
		user = new User();
		company = new Company();
	}


	@Test
	public void test_creat() {
		FlatFeeBillingRecord record = new FlatFeeBillingRecord();
		when(auth.getPrincipal()).thenReturn(user);
		when(companyRepo.findOne(15l)).thenReturn(company); 
		
		ModelAndView actual = controller.create(record, 15l, auth);
		
		assertThat(actual.getViewName()).isEqualTo("redirect:/billing-records");
		verify(companyRepo).findOne(15l);
		assertThat(record.getClient()).isSameAs(company);
		assertThat(record.getCreatedBy()).isSameAs(user);
		verify(billingRepo).save(record);
		 
		
	}

}
