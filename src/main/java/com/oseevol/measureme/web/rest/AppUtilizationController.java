package com.oseevol.measureme.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appinfo")
public class AppUtilizationController {
	
	@GetMapping
	public String appInfo() {
		return "";
	}

}
