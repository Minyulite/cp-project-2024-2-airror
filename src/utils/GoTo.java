package utils;

import application.MyApplication;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import pages.FlightAvailablePage;
import pages.MainPage;

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
		MyApplication.getListOfRoots().add(root);
	}

	public static void goToFlightAvailablePage() {
		ScrollPane root = new ScrollPane(FlightAvailablePage.getInstance());
		
		root.setHbarPolicy(ScrollBarPolicy.NEVER);
		if(MyApplication.getScene() == null) {
//			MyApplication.scene = new Scene(root, 800, 800);
			MyApplication.setScene(new Scene(root, 800, 800));

		} else {
//			MyApplication.scene.setRoot(root);
			MyApplication.getScene().setRoot(root);
		}
	}

	public static void goToPurchasePage() {
		
	}

	public static void goToAboutUsPage() {

	}
}
