package com.fdm.newcarparksystem;

import java.util.List;

public class CarParkOperator implements ICarParkOperator {
	
	private CarPark carPark;
	private List<Vehicle> validVehicles;
	private CommandGrammer commandGrammer;
	
	
	public CarParkOperator(CarPark carPark, List<Vehicle> validVehicles, CommandGrammer commandGrammer) {
		this.carPark = carPark;
		this.validVehicles = validVehicles;
		this.commandGrammer = commandGrammer;
	}

	@Override
	public void initializeCarPark(int numOfSpaces ) {
		carPark.setNumOfSpaces(numOfSpaces);
	}

	@Override
	public String executeCommand(String command) {
		command = command.trim();
		String response="";
		if(commandGrammer.getReportCommand().equals(command)) {
			response = getReport();
		}
		else if(command.contains(commandGrammer.getEnterCommand())) {
			for(Vehicle validVehicle: validVehicles) {
				
				if(command.contains(validVehicle.getClass().getSimpleName().toUpperCase())){
					if( carPark.isSpaceAvailable(validVehicle)) {
						carPark.addVehicle(validVehicle);
					}
					else response = "Space not available";
				}
	        }
		}
		else if (command.contains(commandGrammer.getExitCommand())) {
			for(Vehicle validVehicle: validVehicles) {
				if(command.contains(validVehicle.getClass().getSimpleName().toUpperCase())){
					if( carPark.getParkedVehicles().size() > 0 ) {
						validVehicle.setParkedHours(getHours(command));
						carPark.removeVehicle(validVehicle);
					}
					else response = "No " + validVehicle.getClass().getSimpleName() + " parked"; 
				}
	        }
		}
		
	return response;
	}
	
	private int getHours(String command) {
		String hours = command.substring(command.lastIndexOf(" ")+1);
		return Integer.parseInt(hours);
	}
	
	private String getReport() {
		String report = "";
		for(Vehicle validVehicle: validVehicles) {
            report += "\n" + validVehicle.getClass().getSimpleName() + "s Entered: " + carPark.getEnteredVehicle(validVehicle);
            report += "\n" + validVehicle.getClass().getSimpleName() + "s Exited: " + carPark.getExitedVehicle(validVehicle);
            report += "\nParking " + validVehicle.getClass().getSimpleName() + "s :" + carPark.getParkedVehicle(validVehicle);
        }
		report += "\nSpaces available: " + carPark.spaceAvailable();
		report += "\nFees paid: $" + carPark.getTotalFees();
		return report;
	}
}
