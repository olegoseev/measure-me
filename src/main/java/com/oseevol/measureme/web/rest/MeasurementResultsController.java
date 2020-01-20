package com.oseevol.measureme.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oseevol.measureme.domain.MeasureSession;
import com.oseevol.measureme.domain.Website;
import com.oseevol.measureme.model.MeasurementsDto;
import com.oseevol.measureme.model.SummaryDto;
import com.oseevol.measureme.model.projection.CalculatedDelay;
import com.oseevol.measureme.service.MeasureResultService;
import com.oseevol.measureme.service.MeasureSessionService;
import com.oseevol.measureme.service.WebsiteService;

@RestController
@RequestMapping("/api")
public class MeasurementResultsController {

	private final WebsiteService websiteService;
	private final ConversionService conversionService;
	private final MeasureSessionService sessionService;
	private final MeasureResultService resultService;
	
	
	public MeasurementResultsController(WebsiteService websiteService, ConversionService conversionService,
			MeasureSessionService sessionService, MeasureResultService resultService) {
		super();
		this.websiteService = websiteService;
		this.conversionService = conversionService;
		this.sessionService = sessionService;
		this.resultService = resultService;
	}

	@PostMapping("/results")
	public ResponseEntity<MeasurementsDto> results(@RequestBody MeasurementsDto results) {

		if (results == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Optional<Website> website = websiteService.findByNameIgnoreCase(results.getWebsiteName());
		
		if(website.isEmpty()) {
			return new ResponseEntity<>(results, HttpStatus.OK);
		}

		if (conversionService.canConvert(MeasurementsDto.class, MeasureSession.class)) {
			MeasureSession session = conversionService.convert(results, MeasureSession.class);
			MeasureSession saved = sessionService.save(session);
			results.setSessionId(saved.getId());
			return new ResponseEntity<>(results, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/results/{id}")
	public ResponseEntity<Long> results(@PathVariable(name = "id") Long id) {
		
		Optional<MeasureSession> result = sessionService.findById(id);
		
		if(result.isEmpty()) {
			return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
		}
		
		MeasureSession session = result.get();
		sessionService.delete(session);
		
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@GetMapping("/summary/{id}")
	public ResponseEntity<SummaryDto> summary(@PathVariable(name = "id") Long sessionId){
		
		Optional<MeasureSession> record = sessionService.findById(sessionId);
		
		if(record.isEmpty()) {
			return new ResponseEntity<>(new SummaryDto(0l, 0l, 0l), HttpStatus.OK);
		}
		
		MeasureSession session = record.get();

		List<CalculatedDelay> delayList = resultService.findAllByMeasureSession(session);
		
		SummaryDto summary = conversionService.convert(delayList, SummaryDto.class);
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
}
