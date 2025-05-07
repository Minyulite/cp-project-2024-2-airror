package logics;

import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FlightData {
	private String departField, destinyField;
	private LocalDate departDate, destinyDate;
	private int adultField, childrenField, toddlerField;
	private boolean isReturn;
	private String classes;
	
	public FlightData(String departField, String destinyField, LocalDate departDate, LocalDate destinyDate,
			int adultField, int childrenField, int toddlerField, Boolean isReturn, String classes) {
		this.setDepartField(departField);
		this.setDestinyField(destinyField);
		this.setDepartDate(departDate);
		this.setDestinyDate(destinyDate);
		this.setAdultField(adultField);
		this.setChildrenField(childrenField);
		this.setToddlerField(toddlerField);
		this.setReturn(isReturn);
		this.setClasses(classes);
	}
	
	public static boolean isCorrectData(String departField, String destinyField, LocalDate departDate, LocalDate destinyDate,
			int adultField, int childrenField, int toddlerField, Boolean isReturn, String classes) {
		int total = adultField + childrenField + toddlerField;
		System.out.println(departDate);
		if(departField == null || destinyField == null || departDate == null || total == 0 || (isReturn && destinyDate == null)
			|| total == 0 || classes == null) {
			return false;
		}
		return true;
	}
	
	public String getDepartField() {
		return departField;
	}
	public void setDepartField(String departField) {
		this.departField = departField;
	}
	public String getDestinyField() {
		return destinyField;
	}
	public void setDestinyField(String destinyField) {
		this.destinyField = destinyField;
	}
	public LocalDate getDepartDate() {
		return departDate;
	}
	public void setDepartDate(LocalDate departDate) {
		this.departDate = departDate;
	}
	public LocalDate getDestinyDate() {
		return destinyDate;
	}
	public void setDestinyDate(LocalDate destinyDate) {
		this.destinyDate = destinyDate;
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
	public boolean isReturn() {
		return isReturn;
	}
	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
}
