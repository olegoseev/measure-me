package com.osevol.measureme.model.converter;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalLong;

import org.springframework.core.convert.converter.Converter;

import com.osevol.measureme.model.SummaryDto;
import com.osevol.measureme.model.projection.CalculatedDelay;
import com.osevol.measureme.util.MathUtil;

public class CalculatedDelayListToSummaryDto implements Converter<List<CalculatedDelay>, SummaryDto> {

	@Override
	public SummaryDto convert(List<CalculatedDelay> source) {
		
		SummaryDto summary = new SummaryDto();
		
		OptionalLong tempMax = MathUtil.max(source);
		OptionalLong tempMin = MathUtil.min(source);
		OptionalDouble tempAvg = MathUtil.avg(source);
		
		summary.setDelayMax(tempMax.orElse(0l));
		summary.setDelayMin(tempMin.orElse(0l));
		summary.setDelayAvg((long)tempAvg.orElse(0l));
		return summary;
	}

}
