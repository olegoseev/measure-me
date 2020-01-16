package com.osevol.measureme.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "measurement_result")
public class MeasureResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "request_time")
	private long requestTime;

	@Column(name = "response_time")
	private long responseTime;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "measure_session_id")
	private MeasureSession measureSession;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public MeasureSession getMeasureSession() {
		return measureSession;
	}

	public void setMeasureSession(MeasureSession measureSession) {
		this.measureSession = measureSession;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, measureSession, requestTime, responseTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MeasureResult))
			return false;
		MeasureResult other = (MeasureResult) obj;
		return id == other.id && Objects.equals(measureSession, other.measureSession)
				&& requestTime == other.requestTime && responseTime == other.responseTime;
	}
}
