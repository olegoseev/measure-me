package com.osevol.measureme.service;

import java.util.Optional;

import com.osevol.measureme.domain.MeasureSession;

public interface MeasureSessionService {
	Optional<MeasureSession> findById(long id);
	MeasureSession save(MeasureSession session);
	void delete(MeasureSession session);
}
