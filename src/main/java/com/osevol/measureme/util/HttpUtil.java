package com.osevol.measureme.util;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class HttpUtil {

	private HttpUtil() {}
	
	/**
	 * Create header alert
	 * @param message - alert message
	 * @param param - parameters
	 * @return
	 */
	public static HttpHeaders createAlert(String message, String param) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-WebMonitor-alert", message);
		headers.add("X-WebMonitor-param", param);
		return headers;
	}
	
	/**
	 * Create error header alert
	 * @param message - alert message
	 * @param param - parameters
	 * @return
	 */
	public static HttpHeaders createErrorAlert(String message, String param) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-WebMonitor-error", message);
		headers.add("X-WebMonitor-param", param);
		return headers;
	}
	
	
	public static <T> ResponseEntity<T> entityOkOrNotFound(Optional<T> entity){
		return entity
				.map(response -> new ResponseEntity<T>(response, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));		
	}
	
	
	public static <T> ResponseEntity<T> entityOkOrNotFound(Optional<T> entity, HttpHeaders headers){
		return entity
				.map(response -> new ResponseEntity<T>(response, headers, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
