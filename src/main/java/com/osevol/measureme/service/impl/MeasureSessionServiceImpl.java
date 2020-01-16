package com.osevol.measureme.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.osevol.measureme.domain.MeasureSession;
import com.osevol.measureme.repository.MeasureSessionRepository;
import com.osevol.measureme.service.MeasureSessionService;

@Service
public class MeasureSessionServiceImpl implements MeasureSessionService {

	private final MeasureSessionRepository sessionRepository;
	
	public MeasureSessionServiceImpl(MeasureSessionRepository sessionRepository) {
		super();
		this.sessionRepository = sessionRepository;
	}

	@Override
	public Optional<MeasureSession> findById(long id) {
		return sessionRepository.findById(id);
	}

	@Override
	public MeasureSession save(MeasureSession session) {
		return sessionRepository.save(session);
	}

	@Override
	public void delete(MeasureSession session) {
		sessionRepository.delete(session);
	}

}
