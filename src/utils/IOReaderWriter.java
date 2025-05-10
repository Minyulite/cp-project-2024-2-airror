package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logics.FlightData;
import logics.PurchaseData;

public class IOReaderWriter {

	public static Scanner sc;

	public static ArrayList<String> getStringsFromTextFile(String path) {
		ArrayList<String> airports = new ArrayList<>();
		try {
			InputStream in = IOReaderWriter.class.getResourceAsStream(path);
			if(in == null) {
				throw new Exception();
			}
			sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
			while (sc.hasNextLine()) {
				airports.add(sc.nextLine().strip());
			}
			sc.close();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE NOT FOUND");
			alert.showAndWait();
		}
		return airports;
	}

	public static ArrayList<FlightData> getListOfFlightData(String path) {
		ArrayList<FlightData> flightsList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			InputStream in = IOReaderWriter.class.getResourceAsStream(path);
			if(in == null) {
				throw new Exception();
			}
			sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
			while (sc.hasNextLine()) {
				String[] splitted = sc.nextLine().strip().split(",");
				String[] path1 = splitted[4].split(" ");
				String imageViewPath = "img/" + path1[0] + "_" + path1[1] + ".png";
//				System.out.println(imageViewPath);
				FlightData flightData = new FlightData(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4],
						UIComponent.getImageView(imageViewPath, 80, true), splitted[5], splitted[6],
						Double.parseDouble(splitted[7]), LocalDate.parse(splitted[8], formatter));
				flightsList.add(flightData);
			}
			sc.close();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE NOT FOUND");
			alert.showAndWait();
		}
		return flightsList;
	}
	

	public static Map<String, String> getMap(String path) {
		Map<String, String> mp = new HashMap<String, String>();
		try {
			InputStream in = IOReaderWriter.class.getResourceAsStream(path);
			if(in == null) {
				throw new Exception();
			}
			sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
			while (sc.hasNextLine()) {
				String[] splitted = sc.nextLine().split(",");
				mp.put(splitted[0].strip(), splitted[1].strip());
			}
			sc.close();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE NOT FOUND");
			alert.showAndWait();
		}
		return mp;
	}
}
