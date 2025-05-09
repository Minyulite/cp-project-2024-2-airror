package logics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.ImageView;

public class FlightData {
	private String departAbbr;
	private String destinyAbbr;
	private String airlineName;
	private String departAirportName;
	private String destinyAirportName;
	private ImageView airlineImage;
	private String departTime;
	private String arrivalTime;

	private double price;
	private LocalDate departDate;

	public FlightData(String departAbbr, String departAirportName, String destinyAbbr, String destinyAirportName,
			String airlineName, ImageView airlineImage, String departTime, String arrivalTime, double price,
			LocalDate departDate) {
		this.setDepartAbbr(departAbbr);
		this.setDepartAirportName(departAirportName);
		this.setDestinyAbbr(destinyAbbr);
		this.setDestinyAirportName(destinyAirportName);
		this.setAirlineName(airlineName);
		this.setAirlineImage(airlineImage);
		this.setDepartTime(departTime);
		this.setArrivalTime(arrivalTime);
		this.setPrice(price);
		this.setDepartDate(departDate);
	}

	@Override
	public String toString() {
		return this.getDepartAbbr() + "," + this.getDepartAirportName() + "," + this.getDestinyAbbr() + ","
				+ this.getDestinyAirportName() + "," + this.getAirlineName() + "," + this.getDepartTime() + ","
				+ this.getArrivalTime() + "," + this.getPrice() + ","
				+ this.getDepartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getDepartAirportName() {
		return departAirportName;
	}

	public void setDepartAirportName(String departAirportName) {
		this.departAirportName = departAirportName;
	}

	public String getDestinyAirportName() {
		return destinyAirportName;
	}

	public void setDestinyAirportName(String destinyAirportName) {
		this.destinyAirportName = destinyAirportName;
	}

	public LocalDate getDepartDate() {
		return departDate;
	}

	public void setDepartDate(LocalDate departDate) {
		this.departDate = departDate;
	}

	public String getDepartAbbr() {
		return departAbbr;
	}

	public void setDepartAbbr(String departAbbr) {
		this.departAbbr = departAbbr;
	}

	public String getDestinyAbbr() {
		return destinyAbbr;
	}

	public void setDestinyAbbr(String destinyAbbr) {
		this.destinyAbbr = destinyAbbr;
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
