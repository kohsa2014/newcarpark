package com.fdm.newcarparksystem;

import java.util.Scanner;

public class CarParkView implements IView {
	private Scanner scanner;
	private Console console;

	public CarParkView( Scanner scanner, Console console) {
		this.scanner = scanner;
		this.console = console;
	}
 
	@Override 
	public String getNumOfSpaces() {
		console.print("How many spaces does the car park have? ");
		return scanner.nextLine();
	}

	@Override
	public void display(String message) {
		console.print(message);
	}

	@Override
	public String getCommand() {
		console.printSameLine("USER: ");
		return scanner.nextLine();
	}

}
