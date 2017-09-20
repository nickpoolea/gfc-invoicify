package com.theironyard.invoicify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theironyard.invoicify.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	int countByCompanyName(String companyName);

}
