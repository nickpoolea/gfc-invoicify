package com.theironyard.invoicify.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class InvoiceLineItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@OneToOne
	private BillingRecord billingRecord;
	
	@ManyToOne
	private Invoice invoice;
	
	private Date createdOn;
	
	@ManyToOne
	private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BillingRecord getBillingRecord() {
		return billingRecord;
	}

	public void setBillingRecord(BillingRecord billingRecord) {
		this.billingRecord = billingRecord;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
