package vn.smokecoffee.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSmokeCoffee {
	@GetMapping("/hello")
	public String helloPage() {
		return "Welcome from Viet Nam";
	}
}
