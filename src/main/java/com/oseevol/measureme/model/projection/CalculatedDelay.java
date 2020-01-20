package com.oseevol.measureme.model.projection;

import org.springframework.beans.factory.annotation.Value;

import com.oseevol.measureme.util.BaseNumber;

public interface CalculatedDelay extends BaseNumber {

	@Value("#{target.responseTime - target.requestTime}")
	long getLong();
}
