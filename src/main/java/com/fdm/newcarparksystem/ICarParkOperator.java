package com.fdm.newcarparksystem;


public interface ICarParkOperator {

	String executeCommand(String command);
	void initializeCarPark(int numOfSpaces);

}
