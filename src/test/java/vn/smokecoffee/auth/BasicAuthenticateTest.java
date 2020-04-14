package vn.smokecoffee.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicAuthenticateTest {

	TestRestTemplate restTemplate;
	URL base;
	@LocalServerPort
	int port;

	String api = "/auth/hello";

	@Before
	public void setUp() throws MalformedURLException {
		restTemplate = new TestRestTemplate("Khan", "password2");
		base = new URL("http://localhost:" + port + api);
	}

	@Test
	public void whenLoggedUserRequestsHomePage_ThenSuccess() throws IllegalStateException, IOException {
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("Viet Nam"));
	}

	@Test
	public void whenUserWithWrongCredentials_thenUnauthorizedPage() throws Exception {

		restTemplate = new TestRestTemplate("Khan", "wrongpassword");
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		//assertTrue(response.getBody().contains("Unauthorized"));
	}
}
