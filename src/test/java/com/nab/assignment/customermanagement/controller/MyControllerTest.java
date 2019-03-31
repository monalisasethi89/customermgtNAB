package com.nab.assignment.customermanagement.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/*
	 * @Test public void getProductsList() throws Exception { String uri =
	 * "/CustomerManagement/customer/1000"; MvcResult mvcResult =
	 * mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.
	 * APPLICATION_JSON_VALUE)) .andReturn();
	 * 
	 * int status = mvcResult.getResponse().getStatus(); assertEquals(200, status);
	 * String content = mvcResult.getResponse().getContentAsString(); CustomerDTO
	 * customerDTO = super.mapFromJson(content, CustomerDTO.class);
	 * assertTrue(customerDTO.getCustomerId() > 0); }
	 */

	@Test
	public void testCreate() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/CustomerManagement/customer/1000")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();
		Assert.assertEquals("http response status is wrong", 200, status);

	}
}
