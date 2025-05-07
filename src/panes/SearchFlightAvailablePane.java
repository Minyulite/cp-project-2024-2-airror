package panes;

import interfaces.Searchable;
import javafx.scene.layout.VBox;
import logics.FlightData;

public class SearchFlightAvailablePane extends VBox implements Searchable {
	public SearchFlightAvailablePane() {
		super();
	}
	
	@Override
	public void addSearchEachPane(Object obj) {
		FlightData flightData = (FlightData) obj;
	}
}