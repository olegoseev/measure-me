package com.osevol.measureme.model.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.osevol.measureme.domain.MeasureResult;
import com.osevol.measureme.domain.MeasureSession;
import com.osevol.measureme.domain.Website;
import com.osevol.measureme.model.MeasurementsDto;
import com.osevol.measureme.service.MeasureSessionService;
import com.osevol.measureme.service.WebsiteService;

@Component
public class MeasurementsDtoToMeasureSession implements Converter <MeasurementsDto, MeasureSession> {

	private static WebsiteService websiteService;
	private static MeasureSessionService sessionService;
	
	@Autowired
	public void setWebsiteService(WebsiteService websiteService) {
		MeasurementsDtoToMeasureSession.websiteService = websiteService;
	}
	
	@Autowired
	public void setMeasureSessionService(MeasureSessionService sessionService) {
		MeasurementsDtoToMeasureSession.sessionService = sessionService;
	}
	
	@Override
	public MeasureSession convert(MeasurementsDto source) {
		
		Optional<Website> data = websiteService.findByNameIgnoreCase(source.getWebsiteName());
		
		Website website = data.orElse(new Website());
		
		MeasureSession session = sessionService.findById(source.getSessionId()).orElse(new MeasureSession());
		session.setWebapp(source.getWebAppName());
		session.setWebappVersion(source.getWebAppVer());

		website.getMeasureSessions().add(session);
		session.setWebsite(website);
		
		MeasureResult result = new MeasureResult();
		result.setRequestTime(source.getRequestTime());
		result.setResponseTime(source.getResponseTime());
		
		session.getMeasurementResults().add(result);
		result.setMeasureSession(session);
		
		return session;
	}
}
