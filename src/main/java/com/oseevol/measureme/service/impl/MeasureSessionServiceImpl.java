package com.oseevol.measureme.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oseevol.measureme.domain.MeasureSession;
import com.oseevol.measureme.repository.MeasureSessionRepository;
import com.oseevol.measureme.service.MeasureSessionService;

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
