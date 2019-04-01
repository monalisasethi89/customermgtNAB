package com.nab.assignment.customermanagement.service;

/**
 * @author Monalisa Sethi
 *
 */
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.nab.assignment.customermanagement.dao.CustomerAddressRepository;
import com.nab.assignment.customermanagement.dao.CustomerManagementRepository;
import com.nab.assignment.customermanagement.dto.CustomerAddressDTO;
import com.nab.assignment.customermanagement.dto.CustomerDTO;
import com.nab.assignment.customermanagement.entity.Customer;
import com.nab.assignment.customermanagement.entity.CustomerAddress;
import com.nab.assignment.customermanagement.exception.CreditRatingOutOfRangeException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerManagementServiceImplTest {

	private static final String AUSTRALIA = "Australia";

	@InjectMocks
	private CustomerManagementServiceImpl customerManagementService;

	@Mock
	private CustomerManagementRepository customerManagementRepo;

	@Mock
	private CustomerAddressRepository customerAddressRepo;

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public CustomerManagementServiceIntf employeeService() {
			return new CustomerManagementServiceImpl();
		}
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Customer customer = new Customer();
		customer.setId(1000L);
		customer.setFirstName("Jon");
		customer.setSurName("Locker");
		customer.setSex("M");
		customer.setCreditRating(3);
		customer.setIsNabCustomer("Y");
		customer.setMaritalStatus("S");
		customer.setTitle("Mr");
		customer.setInitials("JS");
		Mockito.when(customerManagementRepo.findById(1000L)).thenReturn(Optional.of(customer));

		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setCity("Melbourne");
		customerAddress.setCountry(AUSTRALIA);
		customerAddress.setCustomer(customer);
		customerAddress.setPinCode("3000");
		customerAddress.setState("Victoria");
		customerAddress.setStreetName("Spencer street");
		customerAddress.setUnitNo("655");
		customerAddress.setId(1000L);
		Mockito.when(customerAddressRepo.findById(1000L)).thenReturn(Optional.of(customerAddress));
	}

	@Test
	public void testCreateCustomer() throws CreditRatingOutOfRangeException {
		Customer customerInput = new Customer();
		customerInput.setCreditRating(15);
		customerInput.setFirstName("Sara");
		customerInput.setInitials("SMJ");
		customerInput.setTitle("Miss");
		customerInput.setSurName("Jones");
		customerInput.setMiddleName("Mary");
		customerInput.setIsNabCustomer("Y");
		customerInput.setMaritalStatus("M");
		customerInput.setSex("F");
		customerInput.setId(null);

		Customer customerOutput = new Customer();
		customerOutput.setId(1000L);

		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setId(1000L);
		customerAddress.setCity("Sydney");
		customerAddress.setCountry(AUSTRALIA);
		customerAddress.setPinCode("2056");
		customerAddress.setState("NSW");
		customerAddress.setStreetName("King st");
		customerAddress.setSuburb("Toongabbie");
		customerAddress.setUnitNo("U42");
		customerAddress.setStreetName("Winston Avenue");
		customerAddress.setCustomer(customerInput);

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCreditRating(15);
		customerDTO.setFirstName("Sara");
		customerDTO.setInitials("SMJ");
		customerDTO.setTitle("Miss");
		customerDTO.setSurName("Jones");
		customerDTO.setMiddleName("Mary");
		customerDTO.setIsNabCustomer(true);
		customerDTO.setMaritalStatus("Married");
		customerDTO.setSex("Female");

		CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
		customerAddressDTO.setCity("Sydney");
		customerAddressDTO.setCountry(AUSTRALIA);
		customerAddressDTO.setPinCode("2056");
		customerAddressDTO.setState("NSW");
		customerAddressDTO.setStreetName("King st");
		customerAddressDTO.setSuburb("Toongabbie");
		customerAddressDTO.setUnitNo("U42");
		customerAddressDTO.setStreetName("Winston Avenue");
		customerDTO.setMailingAddress(customerAddressDTO);

		when(customerManagementRepo.save(any(Customer.class))).thenReturn(customerOutput);
		Long customerId = customerManagementService.createCustomer(customerDTO);
		Assert.assertEquals("1000", String.valueOf(customerId));
	}

	@Test
	public void testRetrieveCustomer() {
		CustomerDTO customerDTO = customerManagementService.retrieveCustomer(1000L);
		Assert.assertEquals("Male", customerDTO.getSex());
		Assert.assertEquals("Jon", customerDTO.getFirstName());
		Assert.assertEquals("Locker", customerDTO.getSurName());
		Assert.assertEquals("1000", String.valueOf(customerDTO.getCustomerId()));
		Assert.assertEquals("Single", customerDTO.getMaritalStatus());
		Assert.assertEquals("Male", customerDTO.getSex());
		Assert.assertEquals(null, customerDTO.getMiddleName());
		Assert.assertEquals("JS", customerDTO.getInitials());
		Assert.assertEquals("Mr Jon Locker", customerDTO.getFullName());
		Assert.assertEquals(true, customerDTO.getIsNabCustomer());
		Assert.assertEquals("Mr", customerDTO.getTitle());
		Assert.assertEquals("Melbourne", customerDTO.getMailingAddress().getCity());
		Assert.assertEquals(AUSTRALIA, customerDTO.getMailingAddress().getCountry());
		Assert.assertEquals("3000", customerDTO.getMailingAddress().getPinCode());
		Assert.assertEquals("Spencer street", customerDTO.getMailingAddress().getStreetName());
		Assert.assertEquals("Victoria", customerDTO.getMailingAddress().getState());
		Assert.assertEquals("655", customerDTO.getMailingAddress().getUnitNo());
	}

	@Test
	public void testIsCustomerPresent() {
		Assert.assertEquals(true, customerManagementService.isCustomerPresent(1000L));
	}

	@Test
	public void testIsCustomerAddressPresent() {
		Assert.assertEquals(true, customerManagementService.isCustomerAddressPresent(1000L));
	}

}