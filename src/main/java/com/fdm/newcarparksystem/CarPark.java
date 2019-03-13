package com.fdm.newcarparksystem;

import java.util.ArrayList;
import java.util.List;

public class CarPark {

	private int numOfSpaces;
	private List<Vehicle> parkedVehicles;
	private List<Vehicle> exitedVehicles;

	public CarPark() {
		parkedVehicles = new ArrayList<>();
		exitedVehicles = new ArrayList<>();

	}

	public void addVehicle(Vehicle vehicle) {
		if (isSpaceAvailable(vehicle)) {
			parkedVehicles.add(vehicle);
		}
	}

	public void removeVehicle(Vehicle vehicle) {
		if (!parkedVehicles.isEmpty()) {
			parkedVehicles.remove(vehicle);
			exitedVehicles.add(vehicle);
		}
	}

	public boolean isSpaceAvailable(Vehicle vehicle) {
		return (spaceAvailable() >= vehicle.getSpaceCovered());
	}

	public int getNumOfSpaces() {
		return numOfSpaces;
	}

	public List<Vehicle> getParkedVehicles() {
		return parkedVehicles;
	}

	public List<Vehicle> getExitedVehicles() {
		return exitedVehicles;
	}

	public double getTotalFees() {
		double totalFees = 0;
		for (Vehicle vehicleExited : exitedVehicles) {
			totalFees += vehicleExited.getHourlyRate() * vehicleExited.getParkedHours();
		}
		return totalFees;
	}

	public int spaceAvailable() {
		int usedSpace = 0;
		for (Vehicle parked : parkedVehicles) {
			usedSpace += parked.getSpaceCovered();
		}
		return numOfSpaces - usedSpace;
	}

	public int getEnteredVehicle(Vehicle available) {
		return getParkedVehicle(available) + getExitedVehicle(available);
	}

	public int getExitedVehicle(Vehicle validVehicle) {
		int i = 0;
		for (Vehicle vehicleExited : exitedVehicles) {
			if (vehicleExited.getClass().isInstance(validVehicle)) {
				i++;
			}
		}
		return i;
	}

	public int getParkedVehicle(Vehicle validVehicle) {
		int i = 0;
		for (Vehicle vehicleExited : parkedVehicles) {
			if (vehicleExited.getClass().isInstance(validVehicle)) {
				i++;
			}
		}
		return i;
	}

	public void setNumOfSpaces(int numOfSpaces) {
		this.numOfSpaces = numOfSpaces;
	}

}
