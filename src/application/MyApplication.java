package application;

import java.io.IOException;
import java.util.ArrayList;
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
import pages.MainPage;
import utils.GoTo;
import utils.IOReaderWriter;
import utils.UIComponent;

public class MyApplication extends Application {
	
	private static Scene scene = null;
	public static Stage stage = null;
	private static ArrayList<Parent> listOfRoots = new ArrayList<>();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		GoTo.goToMainPage();
		setStageStyle(stage);

		stage.show();
	}
	
	public static void setStageStyle(Stage stage) {
		MyApplication.stage = stage;
		stage.setTitle("AIRROR");
		stage.setScene(scene);
		stage.getIcons().add(UIComponent.getImage("img/logo_real.png"));

		stage.setMaxWidth(UIComponent.USER_MAX_SCREEN_WIDTH);
		
		stage.setOnCloseRequest((e) -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
			alert.setHeaderText(null);
			alert.setContentText("Are you sure to exit?");
			Optional<ButtonType> response = alert.showAndWait();
			if(response.isPresent() && response.get() == ButtonType.NO) {
				e.consume();
			}else {
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
