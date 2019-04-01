package com.nab.assignment.customermanagement.controller;

/**
 * @author Monalisa Sethi
 *
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(value = { "classpath:application.properties" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

	private static final String CUSTOMER_MANAGEMENT_CUSTOMER = "/CustomerManagement/customer";
	private static final String HTTP_LOCALHOST = "http://localhost:";
	private static final String BAD_REQUEST_MSG = "400 BAD_REQUEST";

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveCustomerWhenCustomerFound() throws JSONException, IOException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1000"),
				HttpMethod.GET, entity, String.class);
		InputStream is = new FileInputStream("src/main/resources/create_customer_test7.json");
		String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
		JSONAssert.assertEquals(jsonTxt, response.getBody(), false);
		Assert.assertEquals("200 OK", response.getStatusCode().toString());
	}

	private String createURLWithPort(String uri) {
		return HTTP_LOCALHOST + port + uri;
	}

	@Test
	public void testRetrieveCustomerWhenCustomerNotFound() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1001"),
				HttpMethod.GET, entity, String.class);
		JSONAssert.assertEquals("{\"status\":404,\"message\":\"Customer not found\"}", response.getBody(), false);
		Assert.assertEquals("404 NOT_FOUND", response.getStatusCode().toString());
	}

	@Test
	public void testDeleteCustomerWhenCustomerNotFound() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1001"),
				HttpMethod.DELETE, entity, String.class);
		JSONAssert.assertEquals("{\"status\":404,\"message\":\"Customer not found\"}", response.getBody(), false);
		Assert.assertEquals("404 NOT_FOUND", response.getStatusCode().toString());
	}

	@Test
	public void testCreateCustomer() throws URISyntaxException, IOException {
		InputStream is = new FileInputStream("src/main/resources/create_customer_test1.json");
		String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URI(HTTP_LOCALHOST + port + CUSTOMER_MANAGEMENT_CUSTOMER))
				.contentType(MediaType.APPLICATION_JSON).body(jsonTxt);
		ResponseEntity<String> postResponse = restTemplate.exchange(requestEntity, String.class);
		Assert.assertEquals("201 CREATED", postResponse.getStatusCode().toString());
	}

	@Test
	public void testCreateCustomerWithoutFirstName() throws JSONException, URISyntaxException, IOException {
		InputStream is = new FileInputStream("src/main/resources/create_customer_test2.json");
		String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URL(HTTP_LOCALHOST + port + CUSTOMER_MANAGEMENT_CUSTOMER).toURI())
				.contentType(MediaType.APPLICATION_JSON).body(jsonTxt);
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		JSONAssert.assertEquals("{\"status\":400,\"message\":\"Mandatory parameter 'firstName' missing\"}",
				response.getBody(), false);
		Assert.assertEquals(BAD_REQUEST_MSG, response.getStatusCode().toString());
	}

	@Test
	public void testCreateCustomerWithoutSurName() throws JSONException, URISyntaxException, IOException {
		InputStream is = new FileInputStream("src/main/resources/create_customer_test3.json");
		String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URL(HTTP_LOCALHOST + port + CUSTOMER_MANAGEMENT_CUSTOMER).toURI())
				.contentType(MediaType.APPLICATION_JSON).body(jsonTxt);
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		JSONAssert.assertEquals("{\"status\":400,\"message\":\"Mandatory parameter 'surName' missing\"}",
				response.getBody(), false);
		Assert.assertEquals(BAD_REQUEST_MSG, response.getStatusCode().toString());
	}

	@Test
	public void testCreateCustomerWithoutSex() throws JSONException, URISyntaxException, IOException {
		InputStream is = new FileInputStream("src/main/resources/create_customer_test4.json");
		String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URL(HTTP_LOCALHOST + port + CUSTOMER_MANAGEMENT_CUSTOMER).toURI())
				.contentType(MediaType.APPLICATION_JSON).body(jsonTxt);
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		JSONAssert.assertEquals("{\"status\":400,\"message\":\"Mandatory parameter 'sex' missing\"}",
				response.getBody(), false);
		Assert.assertEquals(BAD_REQUEST_MSG, response.getStatusCode().toString());
	}

	@Test
	public void testCreateCustomerWithoutMailingAddress() throws JSONException, URISyntaxException, IOException {
		InputStream is = new FileInputStream("src/main/resources/create_customer_test5.json");
		String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URL(HTTP_LOCALHOST + port + CUSTOMER_MANAGEMENT_CUSTOMER).toURI())
				.contentType(MediaType.APPLICATION_JSON).body(jsonTxt);
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		JSONAssert.assertEquals("{\"status\":400,\"message\":\"Mandatory parameter 'mailingAddress' missing\"}",
				response.getBody(), false);
		Assert.assertEquals(BAD_REQUEST_MSG, response.getStatusCode().toString());
	}

	@Test
	public void testCreateCustomerWithCreditRatingOutOfRange() throws JSONException, URISyntaxException, IOException {
		InputStream is = new FileInputStream("src/main/resources/create_customer_test6.json");
		String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URL(HTTP_LOCALHOST + port + CUSTOMER_MANAGEMENT_CUSTOMER).toURI())
				.contentType(MediaType.APPLICATION_JSON).body(jsonTxt);
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		JSONAssert.assertEquals("{\"status\":400,\"message\":\"Credit Rating should be in the range of 0 to 100\"}",
				response.getBody(), false);
		Assert.assertEquals(BAD_REQUEST_MSG, response.getStatusCode().toString());
	}

	@Test
	public void testCreateAndDeleteCustomer() throws URISyntaxException, IOException {
		InputStream is = new FileInputStream("src/main/resources/create_customer_test1.json");
		String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URL(HTTP_LOCALHOST + port + CUSTOMER_MANAGEMENT_CUSTOMER).toURI())
				.contentType(MediaType.APPLICATION_JSON).body(jsonTxt);
		restTemplate.exchange(requestEntity, String.class);
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1"),
				HttpMethod.DELETE, entity, String.class);
		Assert.assertEquals("202 ACCEPTED", response.getStatusCode().toString());
	}

	@Test
	public void testDeleteCustomerWhenCustomerFound() {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1002"),
				HttpMethod.DELETE, entity, String.class);
		Assert.assertEquals("202 ACCEPTED", response.getStatusCode().toString());
	}

}
