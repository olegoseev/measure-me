package com.oseevol.measureme.model;

import java.util.Objects;

public class WebsiteConfigDto {

	private String name;
	private String url;
	private int interval;
	private int threshold;

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

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "WebsiteConfigDto [name=" + name + ", url=" + url + ", interval=" + interval + ", threshold=" + threshold
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(interval, name, threshold, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof WebsiteConfigDto))
			return false;
		WebsiteConfigDto other = (WebsiteConfigDto) obj;
		return interval == other.interval && Objects.equals(name, other.name) && threshold == other.threshold
				&& Objects.equals(url, other.url);
	}
}
