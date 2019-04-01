package com.nab.assignment.customermanagement.entity;

/**
 * @author Monalisa Sethi
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custid_generator")
	@SequenceGenerator(name = "custid_generator", sequenceName = "custid_seq", allocationSize = 50)
	private Long id;

	@Column(name = "FIRST_NAME", length = 20, nullable = false)
	private String firstName;

	@Column(name = "MIDDLE_NAME", length = 20)
	private String middleName;

	@Column(name = "SUR_NAME", length = 20, nullable = false)
	private String surName;

	@Column(name = "INITIALS", length = 4)
	private String initials;

	@Column(name = "TITLE", length = 4)
	private String title;

	@Column(name = "SEX", length = 1, nullable = false)
	private String sex;

	@Column(name = "MARITAL_STATUS", length = 1)
	private String maritalStatus;

	@Column(name = "CREDIT_RATING", length = 3)
	private Integer creditRating;

	@Column(name = "NAB_CUSTOMER_YN", length = 1)
	private String isNabCustomer;

	public Customer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getIsNabCustomer() {
		return isNabCustomer;
	}

	public void setIsNabCustomer(String isNabCustomer) {
		this.isNabCustomer = isNabCustomer;
	}
}
