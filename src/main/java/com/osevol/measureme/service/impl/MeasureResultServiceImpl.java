package com.osevol.measureme.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.osevol.measureme.domain.MeasureSession;
import com.osevol.measureme.model.projection.CalculatedDelay;
import com.osevol.measureme.repository.MesaureResultRepository;
import com.osevol.measureme.service.MeasureResultService;

@Service
public class MeasureResultServiceImpl implements MeasureResultService {

	private final MesaureResultRepository resultRepository;
	
	public MeasureResultServiceImpl(MesaureResultRepository resultRepository) {
		super();
		this.resultRepository = resultRepository;
	}

	@Override
	public List<CalculatedDelay> findAllByMeasureSession(MeasureSession session) {
		return resultRepository.findAllByMeasureSession(session);
	}

}
