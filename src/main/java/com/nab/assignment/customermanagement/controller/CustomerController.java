package com.nab.assignment.customermanagement.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.base.Strings;
import com.nab.assignment.customermanagement.dto.CustomerDTO;
import com.nab.assignment.customermanagement.exception.CustomerNotFoundException;
import com.nab.assignment.customermanagement.exception.MandatoryParamMissingException;
import com.nab.assignment.customermanagement.service.CustomerManagementServiceIntf;

@RestController
public class CustomerController {

	private static final String CUSTOMER_NOT_FOUND = "Customer not found";
	@Autowired
	CustomerManagementServiceIntf customerManagementServiceIntf;

	@GetMapping(value = "/customer/{id}")
	public ResponseEntity<CustomerDTO> retrieveCustomer(@PathVariable long id) throws CustomerNotFoundException {
		CustomerDTO customer = customerManagementServiceIntf.retrieveCustomer(id);
		if (customer.getCustomerId() == null)
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@DeleteMapping(value = "/customer/{id}")
	@ResponseBody
	public ResponseEntity<Void> deleteCustomer(@PathVariable long id) throws CustomerNotFoundException {
		if (customerManagementServiceIntf.isCustomerPresent(id))
			customerManagementServiceIntf.deleteCustomer(id);
		else
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/customer", consumes = { "application/json" })
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customer)
			throws MandatoryParamMissingException {
		if (Strings.isNullOrEmpty(customer.getFirstName()))
			throw new MandatoryParamMissingException("Mandatory parameter 'firstName' missing");
		else if (Strings.isNullOrEmpty(customer.getSurName()))
			throw new MandatoryParamMissingException("Mandatory parameter 'surName' missing");
		else if (Strings.isNullOrEmpty(customer.getSex()))
			throw new MandatoryParamMissingException("Mandatory parameter 'sex' missing");
		else if (customer.getMailingAddress() == null)
			throw new MandatoryParamMissingException("Mandatory parameter 'mailingAddress' missing");
		Long custId = customerManagementServiceIntf.createCustomer(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(custId).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/customer", consumes = { "application/json" })
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customer)
			throws CustomerNotFoundException, MandatoryParamMissingException {
		if (customer.getCustomerId() == null)
			throw new MandatoryParamMissingException("Required Parameter 'customerId' missing");
		if (customerManagementServiceIntf.isCustomerPresent(customer.getCustomerId()))
			customerManagementServiceIntf.updateCustomer(customer);
		else
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
}
