package utils;

import application.MyApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import logics.RequestData;
import pages.AboutUsPage;
import pages.FlightAvailablePage;
import pages.LoginPage;
import pages.MainPage;
import pages.PurchasePage;

public class GoTo {

	public static void goToMainPage(boolean bool) {
		ScrollPane root = new ScrollPane(MainPage.getInstance(bool));

		root.setHbarPolicy(ScrollBarPolicy.NEVER);
		if (MyApplication.getScene() == null) {
			MyApplication.setScene(new Scene(root, 800, 800));
		} else {
			MyApplication.getScene().setRoot(root);
		}
		MyApplication.getListOfRoots().clear();
		MyApplication.getListOfRoots().add(root);
	}

	public static void goToFlightAvailablePage(RequestData requestData) {
		AnchorPane root = new FlightAvailablePage(requestData);

		if (MyApplication.getScene() == null) {
			MyApplication.setScene(new Scene(root, 800, 800));

		} else {
			MyApplication.getScene().setRoot(root);
		}
		MyApplication.getListOfRoots().add(root);
	}

	public static void goToPurchasePage() {
		AnchorPane root = new PurchasePage();

		if (MyApplication.getScene() == null) {
			MyApplication.setScene(new Scene(root, 800, 800));
		} else {
			MyApplication.getScene().setRoot(root);
		}
	}

	public static void goToAboutUsPage() {
		AnchorPane root = AboutUsPage.getInstance();

		if (MyApplication.getScene() == null) {
			MyApplication.setScene(new Scene(root, 800, 800));
		} else {
			MyApplication.getScene().setRoot(root);
		}
	}

	public static void goToLoginPage() {
		AnchorPane root = new LoginPage();

		if (MyApplication.getScene() == null) {
			MyApplication.setScene(new Scene(root, 800, 800));
		} else {
			MyApplication.getScene().setRoot(root);
		}
	}

	public static void back() {
		MyApplication.getListOfRoots().remove(MyApplication.getListOfRoots().size() - 1);
		MyApplication.getScene().setRoot(MyApplication.getListOfRoots().get(MyApplication.getListOfRoots().size() - 1));
	}
}
