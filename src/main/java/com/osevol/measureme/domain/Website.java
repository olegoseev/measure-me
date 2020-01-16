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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "website")
public class Website implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "url")
	@NotNull
	private String url;

	@Column(name = "modified_date")
	@CreationTimestamp
	private LocalDateTime modifiedDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "website")
	private List<MeasureSession> measureSessions = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "website")
	private MeasureConfig measureConfig;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<MeasureSession> getMeasureSessions() {
		return measureSessions;
	}

	public void setMeasureSessions(List<MeasureSession> measureSessions) {
		this.measureSessions = measureSessions;
	}

	public MeasureConfig getMeasureConfig() {
		return measureConfig;
	}

	public void setMeasureConfig(MeasureConfig measureConfig) {
		this.measureConfig = measureConfig;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, measureConfig, measureSessions, modifiedDate, name, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Website))
			return false;
		Website other = (Website) obj;
		return id == other.id && Objects.equals(measureConfig, other.measureConfig)
				&& Objects.equals(measureSessions, other.measureSessions)
				&& Objects.equals(modifiedDate, other.modifiedDate) && Objects.equals(name, other.name)
				&& Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Website [id=" + id + ", name=" + name + ", url=" + url + ", modifiedDate=" + modifiedDate
				+ ", measureConfig=" + measureConfig + "]";
	}
}
