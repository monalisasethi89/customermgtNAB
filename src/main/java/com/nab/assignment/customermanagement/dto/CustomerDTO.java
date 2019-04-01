package com.nab.assignment.customermanagement.dto;

/**
 * @author Monalisa Sethi
 *
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustomerDTO {
	private Long customerId;
	private String fullName;
	private String firstName;
	private String middleName;
	private String surName;
	private String initials;
	private String title;
	private CustomerAddressDTO mailingAddress;
	private String sex;
	private String maritalStatus;
	private Integer creditRating;
	private Boolean isNabCustomer;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CustomerAddressDTO getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(CustomerAddressDTO mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(Integer creditRating) {
		this.creditRating = creditRating;
	}

	public CustomerDTO() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Boolean getIsNabCustomer() {
		return isNabCustomer;
	}

	public void setIsNabCustomer(Boolean isNabCustomer) {
		this.isNabCustomer = isNabCustomer;
	}
}
