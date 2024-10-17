package com.amit.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	
	@GetMapping("/contactSupport")
	public Mono<String> contactSupport(){
		return Mono.just("Error occurred. Please try after some time or contact the support team..");
	}

}
