package logics;

import java.time.LocalDate;

import javafx.scene.image.ImageView;

public class PurchaseData {

	private String classes;
	private int adultField, childrenField, toddlerField;
	private FlightData flightData;
	
	public PurchaseData(FlightData flightData, String classes, int adultField, int childrenField, int toddlerField) {
		this.setFlightData(flightData);
		this.setClasses(classes);
		this.setAdultField(adultField);
		this.setChildrenField(childrenField);
		this.setToddlerField(toddlerField);
	}
	
	@Override
	public String toString() {
		return this.getFlightData().toString() + "," + this.getClasses() + "," + this.getAdultField() + "," + this.getChildrenField() + "," + this.getToddlerField();
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
	
	public FlightData getFlightData() {
		return flightData;
	}
	
	public void setFlightData(FlightData flightData) {
		this.flightData = flightData;
	}

}
