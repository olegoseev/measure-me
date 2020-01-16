package com.osevol.measureme.model.converter;

import org.springframework.core.convert.converter.Converter;

import com.osevol.measureme.domain.MeasureConfig;
import com.osevol.measureme.domain.Website;
import com.osevol.measureme.model.WebsiteConfigDto;

public class WebsiteToWebsiteConfigDtoConverter implements Converter<Website, WebsiteConfigDto> {

	@Override
	public WebsiteConfigDto convert(Website source) {
		WebsiteConfigDto dto = new WebsiteConfigDto();
		dto.setName(source.getName());
		dto.setUrl(source.getUrl());

		MeasureConfig sourceCfg = source.getMeasureConfig();

		if (sourceCfg != null) {
			dto.setThreshold(sourceCfg.getResponseThreshold());
			dto.setInterval(sourceCfg.getMeasureInterval());
		}
		return dto;
	}

}
