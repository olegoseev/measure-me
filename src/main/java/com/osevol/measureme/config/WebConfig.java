package com.osevol.measureme.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.osevol.measureme.model.converter.CalculatedDelayListToSummaryDto;
import com.osevol.measureme.model.converter.MeasurementsDtoToMeasureSession;
import com.osevol.measureme.model.converter.WebsiteToWebsiteConfigDtoConverter;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("resources/**")
			.addResourceLocations("/public/")
			.setCachePeriod(3600);
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new WebsiteToWebsiteConfigDtoConverter());
		registry.addConverter(new MeasurementsDtoToMeasureSession());
		registry.addConverter(new CalculatedDelayListToSummaryDto());
		WebMvcConfigurer.super.addFormatters(registry);
	}
	
	
	
}
