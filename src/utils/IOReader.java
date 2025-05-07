package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class IOReader {
	
	public static Scanner sc;
	
	public static ArrayList<String> getStringsFromTextFile(String path){
		ArrayList<String> airports = new ArrayList<>();
		try {
			sc = new Scanner(new File(path));
			while(sc.hasNextLine()) {
				airports.add(sc.nextLine());
			}
			sc.close();
		} catch(FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE NOT FOUND");
		}
		return airports;
	}
	
//	public static ArrayList<String> getFlightsList(String path){
//		ArrayList<ArrayList<String>> flightsList = new ArrayList<>();
//		try {
//			sc = new Scanner(new File(path));
//			while(sc.hasNextLine()) {
//				
//			}
//		}
//	}
}
