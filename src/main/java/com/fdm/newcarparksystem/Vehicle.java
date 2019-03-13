package com.fdm.newcarparksystem;

public abstract class Vehicle {

	private final int spaceCovered;
	private final float hourlyRate;
	private int parkedHours;
	
	public Vehicle(int spaceCovered, float hourlyRate) {
		this.spaceCovered = spaceCovered;
		this.hourlyRate = hourlyRate;
	}

	public int getSpaceCovered() {
		return spaceCovered;
	}

	public float getHourlyRate() {
		return hourlyRate;
	}
	public void setParkedHours( int parkedHours) {
		this.parkedHours = parkedHours;
	}
	public int getParkedHours() {
		return parkedHours;
	}
}
