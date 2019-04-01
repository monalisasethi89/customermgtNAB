package com.nab.assignment.customermanagement.service;

/**
 * @author Monalisa Sethi
 *
 */
import com.nab.assignment.customermanagement.dto.CustomerDTO;
import com.nab.assignment.customermanagement.exception.CreditRatingOutOfRangeException;
import com.nab.assignment.customermanagement.exception.CustomerNotFoundException;

public interface CustomerManagementServiceIntf {

	Long updateCustomer(CustomerDTO customer) throws CustomerNotFoundException, CreditRatingOutOfRangeException;

	Long createCustomer(CustomerDTO student) throws CreditRatingOutOfRangeException;

	void deleteCustomer(long id);

	CustomerDTO retrieveCustomer(long id);

	Boolean isCustomerPresent(long id);

	Boolean isCustomerAddressPresent(long id);

}
