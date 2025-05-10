package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logics.FlightData;
import panes.SearchFlightAvailablePane;
import utils.IOReaderWriter;
import utils.UIComponent;

public class HelloWorld extends Application {
	
	public static Scene scene = null;
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(UIComponent.USER_MAX_SCREEN_HEIGHT + " " + UIComponent.USER_MAX_SCREEN_WIDTH);
//		IOReaderWriter.writeListOfFlightData(SearchFlightAvailablePane.flightsList, "res/text/pending.txt");
//		String date = "08/09/2005";
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate ld = LocalDate.parse(date, dtf);
//		LocalDate ld2 = ld;
//		System.out.println(ld2.format(dtf));
//		System.out.println(ld.format(dtf));
//		Thread t = new Thread(() -> {
//			for(int i = 0; i < 100; ++i) {
//				System.out.println("ha");
//				if(Thread.currentThread().isInterrupted()) {
//					break;
//				}
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					Thread.currentThread().interrupt();
//					break;
//				}
//			}
//			System.out.println("WHAT");
//		});
//		System.out.println(t.getState());
//		t.start();
//		System.out.println(t.getState());
//		t.interrupt();
//		Thread.sleep(1000);
//		System.out.println(t.getState());
//		t.start();
//		String s = null;
//		System.out.println(s.toString());
//		LocalDate d1 = LocalDate.now();
//		System.out.println(d1);
//		new Thread(() -> {
//			try {
//				Thread.sleep(1000);
//			}catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//			LocalDate d2 = LocalDate.now();
//			System.out.println(d2);
//			if(d1.isEqual(d2)) {
//				System.out.println("EQUALS");
//			}else if(d1.isAfter(d2)) {
//				System.out.println("WRONG");
//			}else if(d1.isBefore(d2)) {
//				System.out.println("SHOULD BE");
//			}
////			System.out.println(d1);		
//		}).start();
//		String str = "";
//		System.out.println(str == null);
//		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
//		ArrayList<FlightData> flightsList = IOReaderWriter.getFlightsList("res/text/fly_list.txt");
//		for(FlightData f : flightsList) {
//			System.out.println(f.getAirlineName() + " " +  f.getArrivalTime() + " " +  f.getDepartAbbr() + " " +  f.getDepartTime() + " " +  f.getDestinyAbbr() + " " +  
//				" " + f.getPrice() + " " + f.getDepartDate().toString());
//		}
		StackPane root = new StackPane();
		TextField textField = new TextField();
		ArrayList<Parent> arrList = new ArrayList<>();
		arrList.add(root);
//		choiceBox.setOnMouseClicked((e) -> {
//			System.out.println(choiceBox.getValue());
//		});
		System.out.println(textField.getText());
//		StackPane root = new StackPane();
//		DatePicker datePicker = new DatePicker();
//		root.getChildren().add(datePicker);
//		Button btn = new Button("CLICK");
//		btn.setOnMouseClicked((e) -> {
//			System.out.println(datePicker.getValue());
//		});
//		root.getChildren().add(btn);
//		HBox root2 = new HBox();
//		Button root1;
//		root2.getChildren().addAll(root1 = new Button("This is root2"), new Button("This is testing"));
//		root1.setOnMouseClicked((e) -> {
//			scene.setRoot(root);
//		});
//		Button btn = new Button("Hello World");
//		btn.setOnMouseClicked((e) -> {
//			Alert alert = new Alert(AlertType.CONFIRMATION);
//			alert.setHeaderText(null);
//			alert.setContentText("Hello World!");
//			alert.showAndWait();
//		});
//		root.getChildren().add(btn);
//		Rectangle2D r = Screen.getPrimary().getBounds();
//		System.out.println(r.getWidth() + " " + r.getHeight());
//		scene = new Scene(root, 300, 300);
//		scene.setRoot(root2);
		scene = new Scene(arrList.get(0), 300, 300);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Hello World Testing!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
