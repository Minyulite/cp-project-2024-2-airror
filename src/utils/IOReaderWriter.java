package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
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
		ArrayList<String> strings = new ArrayList<>();
		try {
			InputStream in = IOReaderWriter.class.getResourceAsStream(path);
			if (in == null) {
				throw new Exception();
			}
			sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
			while (sc.hasNextLine()) {
				strings.add(sc.nextLine().strip());
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("what");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE NOT FOUND");
			alert.showAndWait();
		}
		return strings;
	}

	public static ArrayList<String> getStringsFromTextFileExternally(String fileName) throws Exception {
		ArrayList<String> strings = new ArrayList<>();
		File jarFile = new File(IOReaderWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		File jarDir = jarFile.getParentFile();
		File targetFile = new File(jarDir, fileName);

		sc = new Scanner(new BufferedReader(new FileReader(targetFile)));
		while (sc.hasNextLine()) {
			strings.add(sc.nextLine().strip());
		}
		sc.close();
		return strings;
	}

	public static ArrayList<FlightData> getListOfFlightData(String path) {
		ArrayList<FlightData> flightsList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			InputStream in = IOReaderWriter.class.getResourceAsStream(path);
			if (in == null) {
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

	public static ArrayList<FlightData> getListOfFlightDataExternally(String fileName) throws Exception {
		ArrayList<FlightData> flightsList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		File jarFile = new File(IOReaderWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		File jarDir = jarFile.getParentFile();
		File targetFile = new File(jarDir, fileName);

		sc = new Scanner(new BufferedReader(new FileReader(targetFile)));
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
		return flightsList;
	}

	public static ArrayList<PurchaseData> getListOfPurchaseDataExternally(String fileName) throws Exception {
		ArrayList<PurchaseData> purchasesList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		File jarFile = new File(IOReaderWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		File jarDir = jarFile.getParentFile();
		File targetFile = new File(jarDir, fileName);

		sc = new Scanner(new BufferedReader(new FileReader(targetFile)));
		while (sc.hasNextLine()) {
			String[] splitted = sc.nextLine().strip().split(",");
			String[] path1 = splitted[4].split(" ");
			String imageViewPath = "img/" + path1[0] + "_" + path1[1] + ".png";
//				System.out.println(imageViewPath);
			FlightData flightData = new FlightData(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4],
					UIComponent.getImageView(imageViewPath, 80, true), splitted[5], splitted[6],
					Double.parseDouble(splitted[7]), LocalDate.parse(splitted[8], formatter));
			purchasesList.add(new PurchaseData(flightData, splitted[9], Integer.parseInt(splitted[10]),
					Integer.parseInt(splitted[11]), Integer.parseInt(splitted[12])));
		}
		sc.close();
		return purchasesList;
	}

	public static Map<String, String> getMap(String path) {
		Map<String, String> mp = new HashMap<String, String>();
		try {
			InputStream in = IOReaderWriter.class.getResourceAsStream(path);
			if (in == null) {
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

	public static Map<String, String> getMapExternally(String fileName) throws Exception {
		Map<String, String> mp = new HashMap<String, String>();

		File jarFile = new File(IOReaderWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		File jarDir = jarFile.getParentFile();
		File targetFile = new File(jarDir, fileName);

		sc = new Scanner(new BufferedReader(new FileReader(targetFile)));
		while (sc.hasNextLine()) {
			String[] splitted = sc.nextLine().split(",");
			mp.put(splitted[0].strip(), splitted[1].strip());
		}
		sc.close();
		return mp;
	}

	public static void writeStringsExternally(ArrayList<Object> objectsList, String fileName) {
		try {
			File jarFile = new File(IOReaderWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			File jarDir = jarFile.getParentFile();
			File outputFile = new File(jarDir, fileName);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
				for (Object obj : objectsList) {
					writer.write(obj.toString() + "\n");
				}
				writer.close();
			}
		} catch (IOException | URISyntaxException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("FILE CANNONT BE WRITTEN");
			alert.showAndWait();
		}
	}

}
