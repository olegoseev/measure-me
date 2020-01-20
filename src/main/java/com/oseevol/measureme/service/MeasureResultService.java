package com.oseevol.measureme.service;

import java.util.List;

import com.oseevol.measureme.domain.MeasureSession;
import com.oseevol.measureme.model.projection.CalculatedDelay;

public interface MeasureResultService {
	List<CalculatedDelay> findAllByMeasureSession(MeasureSession session);
}
