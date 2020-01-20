package com.oseevol.measureme.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oseevol.measureme.domain.MeasureSession;
import com.oseevol.measureme.model.projection.CalculatedDelay;
import com.oseevol.measureme.repository.MesaureResultRepository;
import com.oseevol.measureme.service.MeasureResultService;

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
