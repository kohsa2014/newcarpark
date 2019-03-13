package com.fdm.newcarparksystem;

import java.util.List;

public class Controller {

	private final IView iView;
	private final IValidator iValidator;
	private final ICarParkOperator iCarParkOperator;
	private boolean exitFlag;
	private boolean initializeFlag;
	
	public Controller(IView iView, IValidator iValidator, ICarParkOperator iCarParkOperator) {
		this.iView = iView;
		this.iValidator = iValidator;
		this.iCarParkOperator = iCarParkOperator; 
		exitFlag = false;
		initializeFlag = false;
	}  
	
	public void initialize() {
		String numOfSpaces = iView.getNumOfSpaces();
	
			try {
				iValidator.validateInitialValues(numOfSpaces);
				iCarParkOperator.initializeCarPark(Integer.parseInt(numOfSpaces));
				initializeFlag = true;
			} catch (InvalidPropertiesException e) {
				iView.display(e.getMessage());
			}
	}		
		
	
	public void handleCommand() {
		String command = iView.getCommand();
		command = command.trim();
		if("QUIT".equals(command)) {
			exitFlag = true;
		}
		else { 
				try {
					iValidator.validateCommand(command);
					iView.display(iCarParkOperator.executeCommand(command));
					
				} catch (InvalidCommandException e) {
					iView.display(e.getMessage());
				}
		}
	}
	
	public boolean isExit() {
		return exitFlag;
	}
	public boolean isInitialized() {
		return initializeFlag;
	}
} 
