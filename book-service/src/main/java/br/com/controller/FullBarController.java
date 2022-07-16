package br.com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Foo bar")
@RestController
@RequestMapping("book-service")
public class FullBarController {

	private Logger logger = LoggerFactory.getLogger(FullBarController.class);

	//@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
	//@CircuitBreaker(name = "default")
	//@RateLimiter(name = "default")
	@GetMapping("/foo-bar")
	@Operation(summary = "Foo bar")
	@Bulkhead(name = "default")
	public String foobar() {
		logger.info("Request to foobar received");
		new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		return "Foo-bar";
	}
	
	public String fallbackMethod(Exception ex) {
		return "fallbackMethod foo-bar";
	}
}
