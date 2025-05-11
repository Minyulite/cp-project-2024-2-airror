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
		
//		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {

		primaryStage.setResizable(false);
		primaryStage.setTitle("Hello World Testing!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
