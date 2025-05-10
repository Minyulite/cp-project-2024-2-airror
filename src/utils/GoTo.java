package utils;

import application.MyApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import logics.RequestData;
import pages.FlightAvailablePage;
import pages.MainPage;
import pages.PurchasePage;

public class GoTo {

	public static void goToMainPage() {
		ScrollPane root = new ScrollPane(MainPage.getInstance());

		root.setHbarPolicy(ScrollBarPolicy.NEVER);
		if (MyApplication.getScene() == null) {
//			MyApplication.scene = new Scene(root, 800, 800);
			MyApplication.setScene(new Scene(root, 800, 800));
		} else {
//			MyApplication.scene.setRoot(root);
			MyApplication.getScene().setRoot(root);
		}
		MyApplication.getListOfRoots().clear();
		MyApplication.getListOfRoots().add(root);
	}

	public static void goToFlightAvailablePage(RequestData flightData) {
//		MainPage.adsThread.interrupt();
		AnchorPane root = new FlightAvailablePage(flightData);

//		root.setHbarPolicy(ScrollBarPolicy.NEVER);
		if (MyApplication.getScene() == null) {
//			MyApplication.scene = new Scene(root, 800, 800);
			MyApplication.setScene(new Scene(root, 800, 800));

		} else {
//			MyApplication.scene.setRoot(root);
			MyApplication.getScene().setRoot(root);
		}
		MyApplication.getListOfRoots().add(root);
	}

	public static void goToPurchasePage() {
		AnchorPane root = new PurchasePage();
		
		if(MyApplication.getScene() == null) {
			MyApplication.setScene(new Scene(root, 800, 800));
		} else {
			MyApplication.getScene().setRoot(root);
		}
	}

	public static void goToAboutUsPage() {
		
	}
	
	public static void back() {
		MyApplication.getListOfRoots().remove(MyApplication.getListOfRoots().size() - 1);
		MyApplication.getScene().setRoot(MyApplication.getListOfRoots().get(MyApplication.getListOfRoots().size() - 1));
	}
}
