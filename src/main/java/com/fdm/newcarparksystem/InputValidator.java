package com.fdm.newcarparksystem;

import java.util.ArrayList;
import java.util.List;

public class InputValidator implements IValidator{
	private final CommandGrammer commandGrammer;
	private final List<Vehicle> validVehicles;
	private List<String> validCommands;
	
	public InputValidator(CommandGrammer commandGrammer, List<Vehicle> validVehicles) {
		this.commandGrammer = commandGrammer;
		this.validVehicles = validVehicles;
		validCommands = generateValidCommands();
	}

	@Override
	public void validateInitialValues(String numOfSpaces) throws InvalidPropertiesException {
		if( !isValidNumOfSpaces(numOfSpaces)) {	
			throw new InvalidPropertiesException("Invalid carpark properties");
		}
	}   

	@Override
	public void validateCommand(String command) throws InvalidCommandException {
		boolean flag = false;
		for( String regx : validCommands ) {
			if( command.matches(regx)) {
				flag = true;
				break;
			}
			else {
				flag = false;
			}
		}
		if(flag == false) {
			throw new InvalidCommandException("Invalid Command");
		}
	}
	
	private boolean isValidNumOfSpaces(String string) {
		return string.matches("[0-9]+") ;
	}
	
	private List<String> generateValidCommands() {
		List<String> possibleRegx = new ArrayList<>();
		possibleRegx.add("(" + commandGrammer.getReportCommand() + ")");
		for( Vehicle validVehicle : validVehicles ) {
			possibleRegx.add("(" + commandGrammer.getEnterCommand()+ " " + validVehicle.getClass().getSimpleName().toUpperCase() + ")");
			possibleRegx.add("(" + commandGrammer.getExitCommand()+ " " + validVehicle.getClass().getSimpleName().toUpperCase() + " )\\d+");
		}
		return possibleRegx;
	}

}
