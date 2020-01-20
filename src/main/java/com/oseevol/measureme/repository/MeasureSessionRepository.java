package com.oseevol.measureme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oseevol.measureme.domain.MeasureSession;

public interface MeasureSessionRepository extends JpaRepository<MeasureSession, Long>{

}
