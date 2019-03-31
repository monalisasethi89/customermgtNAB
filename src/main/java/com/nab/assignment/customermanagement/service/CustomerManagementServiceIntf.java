package com.nab.assignment.customermanagement.service;

import com.nab.assignment.customermanagement.dto.CustomerDTO;
import com.nab.assignment.customermanagement.exception.CustomerNotFoundException;

public interface CustomerManagementServiceIntf {

	Long updateCustomer(CustomerDTO customer) throws CustomerNotFoundException;

	Long createCustomer(CustomerDTO student);

	void deleteCustomer(long id);

	CustomerDTO retrieveCustomer(long id);

	Boolean isCustomerPresent(long id);

	Boolean isCustomerAddressPresent(long id);

}
