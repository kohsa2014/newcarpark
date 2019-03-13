package com.fdm.newcarparksystem;

public class CommandGrammer {
	
	private final String reportCommand;
	private final String enterCommand;
	private final String  exitCommand;
	
	public CommandGrammer(String reportCommand, String enterCommand, String exitCommand) {
		this.reportCommand = reportCommand;
		this.enterCommand = enterCommand;
		this.exitCommand = exitCommand;
	}

	public String getReportCommand() {
		return reportCommand;
	}

	public String getEnterCommand() {
		return enterCommand;
	}

	public String getExitCommand() {
		return exitCommand;
	}
}	
