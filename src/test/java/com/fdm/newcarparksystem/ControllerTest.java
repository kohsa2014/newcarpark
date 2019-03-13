package com.fdm.newcarparksystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ControllerTest {
	private final IView iView = mock(IView.class);
	private final IValidator iValidator = mock(IValidator.class);
	private final ICarParkOperator iCarParkOperator = mock (ICarParkOperator.class);
	private final Controller controller = new Controller(iView, iValidator, iCarParkOperator);
	
	@Before
	public void setDevalultInitialValues() {
		when(iView.getNumOfSpaces()).thenReturn("20");
	}
	
	@Test
	public void get_initial_values_from_view_when_initialize_carpark() throws InvalidPropertiesException {
		
		controller.initialize();  
		 
		verifyViewInputs();
	} 
	
	@Test
	public void validate_after_input_is_received_and_set_values_of_carpark() throws InvalidPropertiesException {
		
		controller.initialize();
		
		verifyViewInputs();
		verify(iValidator).validateInitialValues("20");
		verify(iCarParkOperator).initializeCarPark(20);
	}
	 
	@Test
	public void never_set_initialize_carpark_properties_if_one_of_input_is_not_valid() throws InvalidPropertiesException {
		when(iView.getNumOfSpaces()).thenReturn("-10");
		InvalidPropertiesException ipe = mock(InvalidPropertiesException.class);
		doThrow(ipe).when(iValidator).validateInitialValues("-10");
		
		controller.initialize();
		verifyViewInputs();
		verify(iValidator).validateInitialValues("-10");
		verify(iCarParkOperator,never()).initializeCarPark(-10);
		verify(iView).display(ipe.getMessage());
		assertFalse(controller.isInitialized());
	}
	
	@Test
	public void get_command_from_view_and_validate_and_display_response() throws InvalidCommandException {
		when(iView.getCommand()).thenReturn("ENTER CAR");
		when(iCarParkOperator.executeCommand("ENTER CAR")).thenReturn("");
		
		controller.handleCommand();
		
		verify(iView).getCommand();
		verify(iValidator).validateCommand("ENTER CAR");
		verify(iCarParkOperator).executeCommand("ENTER CAR");
		verify(iView).display("");
	}
	
	@Test
	public void when_invalid_command_never_execute_command() throws InvalidCommandException {
		when(iView.getCommand()).thenReturn("ENTER CARs");
		InvalidCommandException ice = mock(InvalidCommandException.class);
		doThrow(ice).when(iValidator).validateCommand("ENTER CARs");
		
		controller.handleCommand();
		
		verify(iView).getCommand();
		verify(iValidator).validateCommand("ENTER CARs");
		verify(iCarParkOperator, never()).executeCommand("ENTER CARs");
		verify(iView, never()).display("");
		verify(iView).display(ice.getMessage());
	}
	
	@Test
	public void when_command_is_QUIT_never_execute_command() throws InvalidCommandException {
		when(iView.getCommand()).thenReturn("QUIT");
		
		controller.handleCommand();
		
		verify(iView).getCommand();
		verify(iValidator, never()).validateCommand("QUIT");
		verify(iCarParkOperator, never()).executeCommand("QUIT");
		verify(iView, never()).display("");
		assertTrue(controller.isExit());
	}
	 
	
	private void verifyViewInputs() {
		verify(iView).getNumOfSpaces();
	}

}
