package com.osevol.measureme.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "measure_config")
public class MeasureConfig implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "measure_interval")
    private int measureInterval;

    @Column(name = "response_threshold")
    private int responseThreshold;

    @Column(name = "modified_date")
    @CreationTimestamp
    private LocalDateTime modifiedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Website website;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMeasureInterval() {
		return measureInterval;
	}

	public void setMeasureInterval(int measureInterval) {
		this.measureInterval = measureInterval;
	}

	public int getResponseThreshold() {
		return responseThreshold;
	}

	public void setResponseThreshold(int responseThreshold) {
		this.responseThreshold = responseThreshold;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, measureInterval, modifiedDate, responseThreshold, website);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MeasureConfig))
			return false;
		MeasureConfig other = (MeasureConfig) obj;
		return id == other.id && measureInterval == other.measureInterval
				&& Objects.equals(modifiedDate, other.modifiedDate) && responseThreshold == other.responseThreshold
				&& Objects.equals(website, other.website);
	}

	@Override
	public String toString() {
		return "MeasureConfig [id=" + id + ", measureInterval=" + measureInterval + ", responseThreshold="
				+ responseThreshold + ", modifiedDate=" + modifiedDate + "]";
	}
}
