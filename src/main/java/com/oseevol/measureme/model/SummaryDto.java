package com.oseevol.measureme.model;

public class SummaryDto {
	
	private long delayMin;
	private long delayMax;
	private long delayAvg;
	
	
	public SummaryDto() {
		super();
	}
	
	public SummaryDto(long delayMin, long delayMax, long delayAvg) {
		super();
		this.delayMin = delayMin;
		this.delayMax = delayMax;
		this.delayAvg = delayAvg;
	}
	public long getDelayMin() {
		return delayMin;
	}
	public void setDelayMin(long delayMin) {
		this.delayMin = delayMin;
	}
	public long getDelayMax() {
		return delayMax;
	}
	public void setDelayMax(long delayMax) {
		this.delayMax = delayMax;
	}
	public long getDelayAvg() {
		return delayAvg;
	}
	public void setDelayAvg(long delayAvg) {
		this.delayAvg = delayAvg;
	}
	
	@Override
	public String toString() {
		return "SummaryDto [delayMin=" + delayMin + ", delayMax=" + delayMax + ", delayAvg=" + delayAvg + "]";
	}

}
