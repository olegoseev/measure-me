package com.oseevol.measureme.web.rest;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oseevol.measureme.domain.Website;
import com.oseevol.measureme.model.WebsiteConfigDto;
import com.oseevol.measureme.service.WebsiteService;

@RestController
@RequestMapping("/api")
public class WebsiteConfigController {

	private final WebsiteService websiteService;
	private final ConversionService conversionService;

	public WebsiteConfigController(WebsiteService websiteService, ConversionService conversionService) {
		super();
		this.websiteService = websiteService;
		this.conversionService = conversionService;
	}

	@GetMapping("/cfg/{siteName}")
	public ResponseEntity<WebsiteConfigDto> configuration(@PathVariable(name = "siteName") String siteName) {
		
		Website website = websiteService.findByNameIgnoreCase(siteName).orElse(null);

		if (website == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		WebsiteConfigDto websiteConfig = null;
		if (conversionService.canConvert(website.getClass(), WebsiteConfigDto.class)) {
			websiteConfig = conversionService.convert(website, WebsiteConfigDto.class);
		}
		return new ResponseEntity<>(websiteConfig, HttpStatus.OK);
	}
}
