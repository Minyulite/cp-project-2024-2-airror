package panes;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SearchFlightAvailableEachPane extends HBox {
	private String depart;
	private String destiny;
	private String airlineName;
	private ImageView airlineImage;
	private String departTime;
	private String arrivalTime;
	private double price;

	public SearchFlightAvailableEachPane(String airlineName, ImageView airlineImage, String departTime, String arrivalTime,
			double price) {
		super();
		this.setAirlineName(airlineName);
		this.setAirlineImage(airlineImage);
		this.setDepartTime(departTime);
		this.setArrivalTime(arrivalTime);

	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public ImageView getAirlineImage() {
		return airlineImage;
	}

	public void setAirlineImage(ImageView airlineImage) {
		this.airlineImage = airlineImage;
	}

	public String getDepartTime() {
		return departTime;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}