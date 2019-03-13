package com.fdm.newcarparksystem;


public interface IValidator {


	void validateCommand(String command) throws InvalidCommandException;

	void validateInitialValues(String numOfSpaces) throws InvalidPropertiesException;

}
