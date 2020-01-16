package com.osevol.measureme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osevol.measureme.domain.Website;

public interface WebsiteRepository extends JpaRepository<Website, Long>{
	Optional<Website> findByNameIgnoreCase(String name);
}
