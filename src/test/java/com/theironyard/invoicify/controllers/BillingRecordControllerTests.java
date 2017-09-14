package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.BillingRecord;
import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

public class BillingRecordControllerTests {
	
	private BillingRecordController controller;
	private BillingRecordRepository billingRepo;
	private CompanyRepository companyRepo;
	
	@Before
	public void setup() {
		billingRepo = mock(BillingRecordRepository.class);
		companyRepo = mock(CompanyRepository.class);
		controller = new BillingRecordController(billingRepo, companyRepo);
	}

	@Test
	public void test_list() {
		List<BillingRecord> records = new ArrayList<BillingRecord>();
		List<Company> companies = new ArrayList<Company>();
		Company company = new Company();
		when(billingRepo.findAll()).thenReturn(records);
		when(companyRepo.findAll()).thenReturn(companies);
		
		ModelAndView actual = controller.list();
		
		verify(billingRepo).findAll();
		verify(companyRepo).findAll();
		assertThat(actual.getViewName()).isEqualTo("billing-records/list");
		assertThat(actual.getModel().get("records")).isSameAs(records);
		assertThat(actual.getModel().get("companies")).isSameAs(companies);
	}
	
}
