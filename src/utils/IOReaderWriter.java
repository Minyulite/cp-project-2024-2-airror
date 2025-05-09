package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				airports.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
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
			sc = new Scanner(new File(path));
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
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE NOT FOUND");
			alert.showAndWait();
		}
		return flightsList;
	}
	
	public static ArrayList<PurchaseData> getListOfPurchaseData(String path) {
		ArrayList<PurchaseData> purchasesList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				String[] splitted = sc.nextLine().strip().split(",");
				String[] path1 = splitted[4].split(" ");
				String imageViewPath = "img/" + path1[0] + "_" + path1[1] + ".png";
//				System.out.println(imageViewPath);
				PurchaseData purchaseData = new PurchaseData(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4],
						UIComponent.getImageView(imageViewPath, 80, true), splitted[5], splitted[6],
						Double.parseDouble(splitted[7]), LocalDate.parse(splitted[8], formatter), splitted[9], 
						Integer.parseInt(splitted[10]), Integer.parseInt(splitted[11]), Integer.parseInt(splitted[12]));
				purchasesList.add(purchaseData);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE NOT FOUND");
			alert.showAndWait();
		}
		return purchasesList;
	}

	public static void writeListOfFlightData(ArrayList<FlightData> flightsList, String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (FlightData fd : flightsList) {
				writer.write(fd.toString() + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
		} catch (IOException e2) {
			System.out.println("Could not write file");
			e2.printStackTrace();
		}
	}
	
	
	public static void writeListOfPurchaseData(ArrayList<PurchaseData> purchasesList, String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (PurchaseData pd : purchasesList) {
				writer.write(pd.toString() + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
		} catch (IOException e2) {
			System.out.println("Could not write file");
			e2.printStackTrace();
		}
	}
	
	public static void clearTextFile(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			writer.write("");
			writer.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
		} catch (IOException e2) {
			System.out.println("Could not write file");
			e2.printStackTrace();
		}
	}

	public static Map<String, String> getTownName(String path) {
		Map<String, String> townName = new HashMap<String, String>();
		try {
			sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				String[] splitted = sc.nextLine().split(",");
				townName.put(splitted[0].strip(), splitted[1].strip());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE NOT FOUND");
			alert.showAndWait();
		}
		return townName;
	}
}
