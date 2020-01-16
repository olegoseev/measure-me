package com.osevol.measureme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osevol.measureme.domain.MeasureConfig;

public interface MeasureConfigRepository extends JpaRepository<MeasureConfig, Long>{

}
