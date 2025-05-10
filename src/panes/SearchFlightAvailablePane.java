package panes;

import java.util.ArrayList;

import interfaces.Searchable;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logics.FlightData;
import logics.RequestData;
import utils.IOReaderWriter;
import utils.UIComponent;

public class SearchFlightAvailablePane extends VBox implements Searchable {
	public static ArrayList<FlightData> flightsList = IOReaderWriter.getListOfFlightData("/text/fly_list_extended.txt");
	public ArrayList<FlightData> flightsSelected = new ArrayList<>();
	
	public SearchFlightAvailablePane(RequestData requestData) {
		super();
		this.addSearchEachPane(requestData);
		this.setBackground(new Background(new BackgroundFill(Color.web("#83cff7"), CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPrefWidth(UIComponent.USER_MAX_SCREEN_WIDTH - 300);
		this.setPadding(new Insets(10, 35, 10, 35));
		this.setSpacing(10);
		if(this.getChildren().size() == 0) {
			this.setAlignment(Pos.CENTER);
			Label label = UIComponent.getLabel("SORRY, THERE AREN'T ANY AVAILABLE FLIGHTS", 40);
			label.setFont(Font.font("verdana", FontWeight.BOLD, 40));
			this.getChildren().add(label);
		}
	}

	@Override
	public void addSearchEachPane(Object obj) {
		RequestData requestData = (RequestData) obj;
		String reqDepartAbbr = requestData.getDepartField().split(" - ")[0];
		String reqDestinyAbbr = requestData.getDestinyField().split(" - ")[0];
		for(FlightData flightData : flightsList) {
			if(flightData.getDepartAbbr().equals(reqDepartAbbr) && flightData.getDestinyAbbr().equals(reqDestinyAbbr) && flightData.getDepartDate().isEqual(requestData.getDepartDate())) {
				AnchorPane temp;
				this.getChildren().add(temp = new SearchFlightAvailableEachPane(flightData, requestData, UIComponent.USER_MAX_SCREEN_WIDTH - 500, 200));
				flightsSelected.add(flightData);
			}
		}
	}

	public static ArrayList<FlightData> getFlightsList() {
		return flightsList;
	}

	public static void setFlightsList(ArrayList<FlightData> flightsList) {
		SearchFlightAvailablePane.flightsList = flightsList;
	}

	public ArrayList<FlightData> getFlightsSelected() {
		return flightsSelected;
	}

	public void setFlightsSelected(ArrayList<FlightData> flightsSelected) {
		this.flightsSelected = flightsSelected;
	}
	
	
}