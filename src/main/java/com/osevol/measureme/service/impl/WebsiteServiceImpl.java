package com.osevol.measureme.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.osevol.measureme.domain.Website;
import com.osevol.measureme.repository.WebsiteRepository;
import com.osevol.measureme.service.WebsiteService;

@Service
public class WebsiteServiceImpl implements WebsiteService {

	private final WebsiteRepository websiteRepository;

	public WebsiteServiceImpl(WebsiteRepository websiteRepository) {
		super();
		this.websiteRepository = websiteRepository;
	}

	@Override
	public Website save(Website site) {
		return websiteRepository.save(site);
	}

	@Override
	public List<Website> findAll() {
		return websiteRepository.findAll();
	}

	@Override
	public Optional<Website> findByNameIgnoreCase(String name) {
		return websiteRepository.findByNameIgnoreCase(name);
	}

}
