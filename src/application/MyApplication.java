package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

//import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logics.FlightData;
import logics.PurchaseData;
import pages.FlightAvailablePage;
import pages.LoginPage;
import pages.MainPage;
import pages.PurchasePage;
import panes.SearchAirportPane;
import panes.SearchFlightAvailablePane;
import utils.GoTo;
import utils.IOReaderWriter;
import utils.UIComponent;

public class MyApplication extends Application {
	
	private static Scene scene = null;
	private static Stage stage = null;
	private static ArrayList<Parent> listOfRoots = new ArrayList<>();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		loadData();
		GoTo.goToLoginPage();
//		GoTo.goToMainPage();
		setStageStyle(stage);

		stage.show();
	}
	
	public static void loadData() {
		// Load Data
		SearchAirportPane.setAirports(IOReaderWriter.getStringsFromTextFile("/text/Asian_Airports_Abbreviated.txt"));
		FlightAvailablePage.townName = IOReaderWriter.getMap("/text/town.txt");
		
		try {
			LoginPage.setPasswords(IOReaderWriter.getMapExternally("username_password_external.txt"));
		} catch (Exception e) {
			LoginPage.setPasswords(IOReaderWriter.getMap("/text/username_password.txt"));
		}		
		
		try {
			FlightAvailablePage.setFlightsList(IOReaderWriter.getListOfFlightDataExternally("fly_list_extended_external.txt"));
		} catch (Exception e) {
			FlightAvailablePage.setFlightsList(IOReaderWriter.getListOfFlightData("/text/fly_list_extended.txt"));
		}
		
		try {
			PurchasePage.setPurchasesList(IOReaderWriter.getListOfPurchaseDataExternally("purchases_external.txt"));
		} catch (Exception e) {
			PurchasePage.setPurchasesList(new ArrayList<PurchaseData>());
		}
	}
	
	public static void keepData() {
		ArrayList<Object> arrList = new ArrayList<>();
		for(String key : LoginPage.getPasswords().keySet()) {
			arrList.add(key + "," +  LoginPage.getPasswords().get(key));
		}
		IOReaderWriter.writeStringsExternally(arrList, "username_password_external.txt");
		arrList.clear();
		
		for(FlightData flightData : FlightAvailablePage.getFlightsList()) {
			arrList.add(flightData);
		}
		IOReaderWriter.writeStringsExternally(arrList, "fly_list_extended_external.txt");
		arrList.clear();
		
		for(PurchaseData purchaseData : PurchasePage.getPurchasesList()) {
			arrList.add(purchaseData);
		}
		IOReaderWriter.writeStringsExternally(arrList, "purchases_external.txt");
	}
	
	public static void setStageStyle(Stage stage) {
		MyApplication.stage = stage;
		stage.setTitle("AIRROR");
		stage.setScene(scene);
		stage.getIcons().add(UIComponent.getImage("img/logo_real.png"));

		stage.setMaxWidth(UIComponent.USER_MAX_SCREEN_WIDTH);
		stage.setMaxHeight(UIComponent.USER_MAX_SCREEN_HEIGHT);
		
		stage.setOnCloseRequest((e) -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
			alert.setHeaderText(null);
			alert.setContentText("Are you sure to exit?");
			Optional<ButtonType> response = alert.showAndWait();
			if(response.isPresent() && response.get() == ButtonType.NO) {
				e.consume();
			}else {
				keepData();
				Platform.exit();
				System.exit(0);				
			}
		});
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setScene(Scene scene) {
		MyApplication.scene = scene;
	}
	
	public static ArrayList<Parent> getListOfRoots(){
		return MyApplication.listOfRoots;
	}
}
