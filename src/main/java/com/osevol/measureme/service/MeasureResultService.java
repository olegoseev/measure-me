package com.osevol.measureme.service;

import java.util.List;

import com.osevol.measureme.domain.MeasureSession;
import com.osevol.measureme.model.projection.CalculatedDelay;

public interface MeasureResultService {
	List<CalculatedDelay> findAllByMeasureSession(MeasureSession session);
}
