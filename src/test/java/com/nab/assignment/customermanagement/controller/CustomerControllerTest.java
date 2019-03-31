package com.nab.assignment.customermanagement.controller;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

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
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
// @WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	/*
	 * @Autowired private MockMvc mvc;
	 */
	@LocalServerPort
	private int port;

	/*
	 * @MockBean CustomerManagementServiceIntf customerManagementServiceIntf;
	 */

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveCustomerWhenCustomerFound() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1000"),
				HttpMethod.GET, entity, String.class);
		String expected = "{\r\n" + "    \"customerId\": 1000,\r\n" + "    \"fullName\": \"Nick Hopkins\",\r\n"
				+ "    \"firstName\": \"Nick\",\r\n" + "    \"surName\": \"Hopkins\",\r\n"
				+ "    \"mailingAddress\": {\r\n" + "        \"unitNo\": \"Melbourne\",\r\n"
				+ "        \"streetName\": \"Spencer Street\",\r\n" + "        \"suburb\": \"Craigeburn\",\r\n"
				+ "        \"city\": \"Melbourne\",\r\n" + "        \"state\": \"Victoria\",\r\n"
				+ "        \"country\": \"Australia\",\r\n" + "        \"pinCode\": \"3000\"\r\n" + "    },\r\n"
				+ "    \"sex\": \"Male\"\r\n" + "}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
		Assert.assertEquals("200 OK", response.getStatusCode().toString());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	/*
	 * @Test public void getEmployeeByIdAPI() throws Exception { mvc.perform(
	 * MockMvcRequestBuilders.get("/CustomerManagement/customer/1000",
	 * 1).accept(MediaType.APPLICATION_JSON))
	 * .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Nick")); }
	 */

	@Test
	public void testRetrieveCustomerWhenCustomerNotFound() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1001"),
				HttpMethod.GET, entity, String.class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		JSONAssert.assertEquals("{\"status\":404,\"message\":\"Customer not found\"}", response.getBody(), false);
		Assert.assertEquals("404 NOT_FOUND", response.getStatusCode().toString());
	}

	@Test
	public void testDeleteCustomerWhenCustomerNotFound() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1001"),
				HttpMethod.DELETE, entity, String.class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		JSONAssert.assertEquals("{\"status\":404,\"message\":\"Customer not found\"}", response.getBody(), false);
		Assert.assertEquals("404 NOT_FOUND", response.getStatusCode().toString());
	}

	@Test
	public void testPostAndDeleteCustomer() throws JSONException, MalformedURLException, URISyntaxException {

		String postBodyJson = "{\r\n" + "    \"firstName\": \"Rajni\",\r\n" + "    \"surName\": \"Minz\",\r\n"
				+ "    \"sex\": \"Female\",\r\n" + "    \"maritalStatus\":\"single\",\r\n"
				+ "    \"mailingAddress\":{\"unitNo\":\"U31\",\"city\":\"Sydney\",\"state\":\"NSW\",\"country\":\"Australia\",\"pinCode\":\"3064\"}\r\n"
				+ "}";
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URL("http://localhost:8080/CustomerManagement/customer").toURI())
				.contentType(MediaType.APPLICATION_JSON).body(postBodyJson);
		ResponseEntity<String> postResponse = restTemplate.exchange(requestEntity, String.class);
		Assert.assertEquals("201 CREATED", postResponse.getStatusCode().toString());
		// Assert.assertEquals("http://localhost:8080/CustomerManagement/customer/1",
		// postResponse.getHeaders().get("Location"));
		/*
		 * HttpEntity<String> entity = new HttpEntity<>(null, headers);
		 * ResponseEntity<String> response =
		 * restTemplate.exchange(createURLWithPort("/CustomerManagement/customer/1"),
		 * HttpMethod.DELETE, entity, String.class);
		 * System.out.println(response.getBody());
		 * System.out.println(response.getStatusCode());
		 * JSONAssert.assertEquals("{\"status\":202,\"message\":\"Customer not found\"}"
		 * , response.getBody(), false); Assert.assertEquals("202 Accepted",
		 * response.getStatusCode().toString());
		 */
	}

	@Test
	public void testCreateCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCustomer() {
		fail("Not yet implemented");
	}

}
