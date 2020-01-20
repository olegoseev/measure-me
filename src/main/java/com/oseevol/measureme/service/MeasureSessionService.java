package com.oseevol.measureme.service;

import java.util.Optional;

import com.oseevol.measureme.domain.MeasureSession;

public interface MeasureSessionService {
	Optional<MeasureSession> findById(long id);
	MeasureSession save(MeasureSession session);
	void delete(MeasureSession session);
}
