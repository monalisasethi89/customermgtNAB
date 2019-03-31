package com.nab.assignment.customermanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.nab.assignment.customermanagement.dao.CustomerAddressRepository;
import com.nab.assignment.customermanagement.dao.CustomerManagementRepository;
import com.nab.assignment.customermanagement.dto.CustomerAddressDTO;
import com.nab.assignment.customermanagement.dto.CustomerDTO;
import com.nab.assignment.customermanagement.entity.Customer;
import com.nab.assignment.customermanagement.entity.CustomerAddress;
import com.nab.assignment.customermanagement.enums.Gender;
import com.nab.assignment.customermanagement.enums.MaritalStatus;
import com.nab.assignment.customermanagement.exception.CustomerNotFoundException;

@Service
public class CustomerManagementServiceImpl implements CustomerManagementServiceIntf {

	@Autowired
	CustomerManagementRepository customerDao;

	@Autowired
	CustomerAddressRepository customerAddressDao;

	@Override
	public Long updateCustomer(CustomerDTO customerDto) throws CustomerNotFoundException {
		Customer customer = new Customer();
		customer.setId(customerDto.getCustomerId());
		Customer updatedCustomer = prepareCustomerData(customerDto, customer);
		return updatedCustomer.getId();
	}

	@Override
	public Long createCustomer(CustomerDTO customerDto) {
		Customer customer = new Customer();
		Customer newCustomer = prepareCustomerData(customerDto, customer);
		return newCustomer.getId();
	}

	private Customer prepareCustomerData(CustomerDTO customerDto, Customer customer) {
		customer.setCreditRating(customerDto.getCreditRating());
		customer.setFirstName(customerDto.getFirstName());
		customer.setInitials(customerDto.getInitials());
		customer.setIsNabCustomer(customerDto.getIsNabCustomer());
		customer.setMaritalStatus(getMaritalStatusCode(customerDto.getMaritalStatus()));
		customer.setMiddleName(customerDto.getMiddleName());
		customer.setSex(populateSex(customerDto.getSex()));
		customer.setSurName(customerDto.getSurName());
		customer.setTitle(customerDto.getTitle());
		Customer newCustomer = customerDao.save(customer);
		CustomerAddress address = new CustomerAddress();
		address.setUnitNo(customerDto.getMailingAddress().getUnitNo());
		address.setCity(customerDto.getMailingAddress().getCity());
		address.setCountry(customerDto.getMailingAddress().getCountry());
		address.setState(customerDto.getMailingAddress().getState());
		address.setPinCode(customerDto.getMailingAddress().getPinCode());
		address.setStreetName(customerDto.getMailingAddress().getStreetName());
		address.setSuburb(customerDto.getMailingAddress().getSuburb());
		address.setCustomer(newCustomer);
		customerAddressDao.save(address);
		return newCustomer;
	}

	private String getMaritalStatusCode(String maritalStatus) {
		if (!Strings.isNullOrEmpty(maritalStatus)) {
			if (maritalStatus.equalsIgnoreCase(MaritalStatus.MARRIED.name()))
				return "M";
			else if (maritalStatus.equalsIgnoreCase(MaritalStatus.SINGLE.name()))
				return "S";
			else if (maritalStatus.equalsIgnoreCase(MaritalStatus.DIVORCED.name()))
				return "D";
		}
		return null;
	}

	private String populateSex(String sex) {
		if (!Strings.isNullOrEmpty(sex)) {
			if (sex.equalsIgnoreCase(Gender.MALE.name()))
				return "M";
			else if (sex.equalsIgnoreCase(Gender.FEMALE.name()))
				return "F";
		}
		return "NA";
	}

	@Override
	public void deleteCustomer(long id) {
		if (isCustomerAddressPresent(id))
			customerAddressDao.deleteById(id);
		customerDao.deleteById(id);
	}

	@Override
	public CustomerDTO retrieveCustomer(long id) {
		Optional<Customer> customer = customerDao.findById(id);
		CustomerDTO dto = new CustomerDTO();
		if (customer.isPresent()) {
			Customer cust = customer.get();
			dto.setCustomerId(cust.getId());
			dto.setCreditRating(cust.getCreditRating());
			dto.setFullName(populateFullName(cust));
			dto.setSex(populateGender(cust.getSex()));
			dto.setMaritalStatus(populateMaritalStatus(cust.getMaritalStatus()));
			dto.setFirstName(cust.getFirstName());
			dto.setInitials(cust.getInitials());
			dto.setIsNabCustomer(cust.getIsNabCustomer());
			dto.setTitle(cust.getTitle());
			dto.setSurName(cust.getSurName());
			Optional<CustomerAddress> customerAddress = customerAddressDao.findById(cust.getId());
			if (customerAddress.isPresent()) {
				CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
				CustomerAddress custAddress = customerAddress.get();
				customerAddressDTO.setUnitNo(custAddress.getCity());
				customerAddressDTO.setCity(custAddress.getCity());
				customerAddressDTO.setCountry(custAddress.getCountry());
				customerAddressDTO.setPinCode(custAddress.getPinCode());
				customerAddressDTO.setState(custAddress.getState());
				customerAddressDTO.setStreetName(custAddress.getStreetName());
				customerAddressDTO.setSuburb(custAddress.getSuburb());
				dto.setMailingAddress(customerAddressDTO);
			}
		}
		return dto;
	}

	private String populateMaritalStatus(String maritalStatus) {
		if (!Strings.isNullOrEmpty(maritalStatus)) {
			if (maritalStatus.equalsIgnoreCase("M"))
				return MaritalStatus.MARRIED.toString();
			else if (maritalStatus.equalsIgnoreCase("S"))
				return MaritalStatus.SINGLE.toString();
			else if (maritalStatus.equalsIgnoreCase("D"))
				return MaritalStatus.DIVORCED.toString();
		}
		return null;
	}

	private String populateGender(String sex) {
		if (!Strings.isNullOrEmpty(sex)) {
			if (sex.equalsIgnoreCase("M"))
				return Gender.MALE.toString();
			else if (sex.equalsIgnoreCase("F"))
				return Gender.FEMALE.toString();
		}
		return "Unspecified";
	}

	private String populateFullName(Customer cust) {
		StringBuilder fName = new StringBuilder();
		fName.append(cust.getFirstName());
		fName.append(" ");
		if (!Strings.isNullOrEmpty(cust.getMiddleName())) {
			fName.append(cust.getMiddleName());
			fName.append(" ");
		}
		fName.append(cust.getSurName());
		return fName.toString();
	}

	@Override
	public Boolean isCustomerPresent(long id) {
		Optional<Customer> customer = customerDao.findById(id);
		return customer.isPresent();
	}

	@Override
	public Boolean isCustomerAddressPresent(long id) {
		Optional<CustomerAddress> customerAddress = customerAddressDao.findById(id);
		return customerAddress.isPresent();
	}
}
