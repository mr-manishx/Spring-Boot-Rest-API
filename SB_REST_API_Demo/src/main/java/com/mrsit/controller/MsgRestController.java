package com.mrsit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {

	@GetMapping("/welcome")
	public String getWelcome() {
		
		String msg = "Welcome to REST API...";
		
		return msg;
	}
	
	@GetMapping("/greet")
	public String greet(@RequestParam("name") String name) {
		
		String msg = "Good Morning, "+name;
		
		return msg;
	}
	
	@GetMapping("/profile/{name}")
	public String profilePage(@PathVariable String name) {
		
		return "Welcome to your Profile, "+name;
	}
}
