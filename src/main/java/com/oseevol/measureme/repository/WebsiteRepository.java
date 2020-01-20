package com.oseevol.measureme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oseevol.measureme.domain.Website;

public interface WebsiteRepository extends JpaRepository<Website, Long>{
	Optional<Website> findByNameIgnoreCase(String name);
}
