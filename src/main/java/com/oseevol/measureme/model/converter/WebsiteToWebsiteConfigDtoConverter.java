package com.oseevol.measureme.model.converter;

import org.springframework.core.convert.converter.Converter;

import com.oseevol.measureme.domain.MeasureConfig;
import com.oseevol.measureme.domain.Website;
import com.oseevol.measureme.model.WebsiteConfigDto;

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
