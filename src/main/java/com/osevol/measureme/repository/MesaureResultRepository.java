package com.osevol.measureme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osevol.measureme.domain.MeasureResult;
import com.osevol.measureme.domain.MeasureSession;
import com.osevol.measureme.model.projection.CalculatedDelay;

public interface MesaureResultRepository extends JpaRepository<MeasureResult, Long>{
	List<CalculatedDelay> findAllByMeasureSession(MeasureSession session);
}
