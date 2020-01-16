package com.osevol.measureme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osevol.measureme.domain.MeasureSession;

public interface MeasureSessionRepository extends JpaRepository<MeasureSession, Long>{

}
