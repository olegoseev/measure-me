package com.oseevol.measureme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oseevol.measureme.domain.MeasureResult;
import com.oseevol.measureme.domain.MeasureSession;
import com.oseevol.measureme.model.projection.CalculatedDelay;

public interface MesaureResultRepository extends JpaRepository<MeasureResult, Long>{
	List<CalculatedDelay> findAllByMeasureSession(MeasureSession session);
}
