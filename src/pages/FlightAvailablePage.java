package pages;

import javafx.scene.layout.AnchorPane;

public class FlightAvailablePage extends AnchorPane {
	public static FlightAvailablePage flightAvailablePageInstance = null;
	
	private FlightAvailablePage() {
		
	}
	
	public static FlightAvailablePage getInstance() {
		if(flightAvailablePageInstance == null) {
			flightAvailablePageInstance = new FlightAvailablePage();
		}
		return flightAvailablePageInstance;
	}
}
