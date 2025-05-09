package logics;

import java.time.LocalDate;

import javafx.scene.image.ImageView;

public class PurchaseData extends FlightData {

	private String classes;
	private int adultField, childrenField, toddlerField;
	
	public PurchaseData(String departAbbr, String departAirportName, String destinyAbbr, String destinyAirportName,
			String airlineName, ImageView airlineImage, String departTime, String arrivalTime, double price,
			LocalDate departDate, String classes, int adultField, int childrenField, int toddlerField) {
		super(departAbbr, departAirportName, destinyAbbr, destinyAirportName, airlineName, airlineImage, departTime,
				arrivalTime, price, departDate);
	
		this.setClasses(classes);
		this.setAdultField(adultField);
		this.setChildrenField(childrenField);
		this.setToddlerField(toddlerField);
	}
	
	public PurchaseData(FlightData flightData, String classes, int adultField, int childrenField, int toddlerField) {
		super(flightData.getDepartAbbr(), flightData.getDepartAirportName(), flightData.getDestinyAbbr(), flightData.getDestinyAirportName(), 
				flightData.getAirlineName(), flightData.getAirlineImage(), flightData.getDepartTime(), flightData.getArrivalTime(), flightData.getPrice(), flightData.getDepartDate());
		this.setClasses(classes);
		this.setAdultField(adultField);
		this.setChildrenField(childrenField);
		this.setToddlerField(toddlerField);
	}
	
	@Override
	public String toString() {
		return super.toString() + "," + this.getClasses() + "," + this.getAdultField() + "," + this.getChildrenField() + "," + this.getToddlerField();
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public int getAdultField() {
		return adultField;
	}

	public void setAdultField(int adultField) {
		this.adultField = adultField;
	}

	public int getChildrenField() {
		return childrenField;
	}

	public void setChildrenField(int childrenField) {
		this.childrenField = childrenField;
	}

	public int getToddlerField() {
		return toddlerField;
	}

	public void setToddlerField(int toddlerField) {
		this.toddlerField = toddlerField;
	}

}
