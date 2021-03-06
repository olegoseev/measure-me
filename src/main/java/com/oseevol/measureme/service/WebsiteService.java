package com.oseevol.measureme.service;

import java.util.List;
import java.util.Optional;

import com.oseevol.measureme.domain.Website;

public interface WebsiteService {
	Website save(Website site);
	Optional<Website> findByNameIgnoreCase(String name);
	List<Website> findAll();
}
