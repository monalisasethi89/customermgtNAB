package com.nab.assignment.customermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress {

	@Id
	private Long id;

	@Column(name = "UNIT_NUMBER", length = 8, nullable = false)
	private String unitNo;

	@Column(name = "STREET_NAME", length = 16)
	private String streetName;

	@Column(name = "SUBERB", length = 16)
	private String suburb;

	@Column(name = "CITY", length = 16, nullable = false)
	private String city;

	@Column(name = "STATE", length = 16, nullable = false)
	private String state;

	@Column(name = "COUNTRY", length = 16, nullable = false)
	private String country;

	@Column(name = "PIN_CODE", length = 8, nullable = false)
	private String pinCode;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Customer customer;

	public CustomerAddress() {
		super();
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
