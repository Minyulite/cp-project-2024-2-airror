package pages;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.GoTo;
import utils.IOReaderWriter;
import utils.UIComponent;

public class LoginPage extends Page {

	private static Map<String, String> passwords;
	public static String loginUsername = null;
	
	public LoginPage() {
		passwords = IOReaderWriter.getMap("res/text/username_password.txt");
		BackgroundImage myBackground = new BackgroundImage(new Image(ClassLoader.getSystemResource("img/login_background_half.png").toString(), UIComponent.USER_MAX_SCREEN_WIDTH * 2 / 3, UIComponent.USER_MAX_SCREEN_HEIGHT, true, false),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		this.setBackground(new Background(myBackground));
		
		ImageView airrorLogo = UIComponent.getImageView("img/logo_real.png", 150, true);
		this.getChildren().add(airrorLogo);
		LoginPage.setTopLeftAnchor(airrorLogo, 1025, 150);
		
		Label greeting = UIComponent.getLabel("Hello Again!", 70);
		greeting.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		Label pers = UIComponent.getLabel("Sign in to unlock flights, freedom, and your next adventure.", 15);
		this.getChildren().addAll(greeting, pers);
		LoginPage.setTopLeftAnchor(greeting, 1095, 290);
		LoginPage.setTopLeftAnchor(pers, 1040, 350);
		
		TextField usernameField = UIComponent.getTextField("", 400, 40);
		usernameField.setPadding(new Insets(10, 0, 0, 15));
		PasswordField passwordField = new PasswordField();
		passwordField.setPadding(new Insets(10, 0, 0, 15));
		passwordField.setPrefHeight(40);
		passwordField.setPrefWidth(400);
		this.getChildren().addAll(usernameField, passwordField);
		
		LoginPage.setTopLeftAnchor(usernameField, 1040, 400);
		LoginPage.setTopLeftAnchor(passwordField, 1040, 450);
		
		usernameField.setFocusTraversable(false);
		usernameField.setStyle("-fx-background-radius: 20px;");
		passwordField.setFocusTraversable(false);
		passwordField.setStyle("-fx-background-radius: 20px;");
		
		Text username = UIComponent.getText("username", 13);
		Text password = UIComponent.getText("password", 13);
		this.getChildren().addAll(username, password);
		
		LoginPage.setTopLeftAnchor(username, 1055, 400);
		LoginPage.setTopLeftAnchor(password, 1055, 450);
		
		Button loginBtn = UIComponent.getButton("log in");
		loginBtn.setPrefWidth(400);
		loginBtn.setPrefHeight(40);
		this.getChildren().add(loginBtn);
		
		LoginPage.setTopLeftAnchor(loginBtn, 1040, 510);
		loginBtn.setStyle("-fx-background-radius: 20px; -fx-font-size: 15");
		
		loginBtn.setOnAction((event) -> {
			if(passwords.containsKey(usernameField.getText().strip()) && passwords.get(usernameField.getText().strip()).strip().equals(passwordField.getText().strip())) {
				loginUsername = usernameField.getText().strip();
				GoTo.goToMainPage();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText(null);
				alert.setContentText("Username or Password is not correct.");
				alert.showAndWait();
			}
		});
		
		Text signUp = UIComponent.getText("Sign Up?", 15);
		this.getChildren().add(signUp);
		LoginPage.setTopLeftAnchor(signUp, 1370, 570);
		
		signUp.setOnMouseClicked((event0) -> {
			usernameField.setText("");
			passwordField.setText("");
			greeting.setText("Let's Sign Up");
			pers.setText("Sign up to unlock flights, freedom, and your next adventure.");
			loginBtn.setText("sign up");
			loginBtn.setOnAction((event1) -> {
				ArrayList<String> pw = IOReaderWriter.getStringsFromTextFile("res/text/username_password.txt");
				pw.add(usernameField.getText().strip() + "," + passwordField.getText().strip());
				IOReaderWriter.writeTextFileFromStrings(pw, "res/text/username_password.txt");
				GoTo.goToLoginPage();
			});
		});
	}
	
	@Override
	public void setHeader(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStyle() {
		// TODO Auto-generated method stub
		
	}

}
