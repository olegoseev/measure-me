package com.osevol.measureme.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "measure_session")
public class MeasureSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "created_datetime")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "webapp")
	private String webapp;

	@Column(name = "webapp_version")
	private String webappVersion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "measureSession")
	private List<MeasureResult> measurementResults = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "website_id")
	private Website website;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getWebapp() {
		return webapp;
	}

	public void setWebapp(String webapp) {
		this.webapp = webapp;
	}

	public String getWebappVersion() {
		return webappVersion;
	}

	public void setWebappVersion(String webappVersion) {
		this.webappVersion = webappVersion;
	}

	public List<MeasureResult> getMeasurementResults() {
		return measurementResults;
	}

	public void setMeasurementResults(List<MeasureResult> measurementResults) {
		this.measurementResults = measurementResults;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdDate, id, measurementResults, webapp, webappVersion, website);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MeasureSession))
			return false;
		MeasureSession other = (MeasureSession) obj;
		return Objects.equals(createdDate, other.createdDate) && id == other.id
				&& Objects.equals(measurementResults, other.measurementResults) && Objects.equals(webapp, other.webapp)
				&& Objects.equals(webappVersion, other.webappVersion) && Objects.equals(website, other.website);
	}
}
