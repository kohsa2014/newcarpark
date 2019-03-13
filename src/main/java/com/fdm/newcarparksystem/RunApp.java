package com.fdm.newcarparksystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunApp {

	public static void main(String[] args) {
		Console console = new Console();
    	Scanner keyboard = new Scanner(System.in);
    	CommandGrammer grammer = new  CommandGrammer("REPORT", "ENTER", "EXIT");
    	Car car = new Car(1, 2);
    	Truck truck = new Truck(2, 3);
    	
    	List<Vehicle> validVehicles = new ArrayList<>();
    	validVehicles.add(car);
    	validVehicles.add(truck);
    	
    	InputValidator validator = new InputValidator(grammer, validVehicles);
    	
    	CarPark carPark = new CarPark();
		CarParkOperator operator = new CarParkOperator(carPark, validVehicles, grammer);	
    	
		CarParkView view = new CarParkView(keyboard, console);
		Controller controller = new Controller(view, validator, operator);

		while(!controller.isInitialized()) {
				controller.initialize();
		}
		while(!controller.isExit()) {
				controller.handleCommand();
		}
				
    	keyboard.close();
	}

}
