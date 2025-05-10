package utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class UIComponent {

	public static final double USER_MAX_SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth(); // These return user screen's width and height
	public static final double USER_MAX_SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
	
	public static Label getLabel(String name, int fontSize) {
		Label label =  new Label(name);
		label.setFont(new Font(fontSize));
		return label;
	}
	
	public static Text getText(String name, int fontSize) {
		Text text =  new Text(name);
		text.setFont(new Font(fontSize));
		return text;
	}
	
	public static Text getText(String name, Font font) {
		Text text = new Text(name);
		text.setFont(font);
		return text;
	}
	
	public static ImageView getImageView(String url_string, double fitHeight, boolean isPreserveRatio) {
		ImageView imageView = new ImageView(ClassLoader.getSystemResource(url_string).toString());
		imageView.setFitHeight(fitHeight);
		imageView.setPreserveRatio(isPreserveRatio);
		return imageView;
	}
	
	public static Image getImage(String url_string) {
		return new Image(ClassLoader.getSystemResource(url_string).toString());
	}
	
	public static Image getImage(String url_string, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
		return new Image(ClassLoader.getSystemResource(url_string).toString(), requestedWidth, requestedHeight, preserveRatio, smooth);
	}
	
	public static TextField getTextField(String text, int prefWidth, int prefHeight) {
		TextField textField = new TextField(text);
		textField.setPrefWidth(prefWidth);
		textField.setPrefHeight(prefHeight);
		return textField;
	}
	
	public static void drawLine(double x1, double y1, double x2, double y2, GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.strokeLine(x1, y1, x2, y2);
		return;
	}
	
	public static int strHourToIntMin(String time) {
		String[] splitted = time.split(":");
		return Integer.parseInt(splitted[0]) * 60 + Integer.parseInt(splitted[1]);
	}
	
	public static String intMintoStrHour(int time) {
		int hour = time / 60;
		int min = time % 60;
		return hour + (hour > 1? " hrs " : " hr ") + min + (min <= 1? " min" : " mins");
	}
	
	public static int getDuration(String departTime, String arrivalTime) {
		int duration = UIComponent.strHourToIntMin(arrivalTime) - UIComponent.strHourToIntMin(departTime);
		if(duration < 0) duration += 1440;
		return duration;
	}
	
	public static boolean isBefore(String time1, String time2) {
		String[] sp1 = time1.split("/");
		String[] sp2 = time2.split("/");
		int[] t1 = {Integer.parseInt(sp1[0]), Integer.parseInt(sp1[1]), Integer.parseInt(sp1[2])};
		int[] t2 = {Integer.parseInt(sp2[0]), Integer.parseInt(sp2[1]), Integer.parseInt(sp2[2])};

		if(t1[2] < t2[2]) return true;
		else if(t1[2] > t2[2]) return false;
		else if(t1[1] < t2[1]) return true;
		else if(t1[1] > t2[1]) return false;
		else if(t1[0] <= t2[0]) return true;
		return false;
	}
	
	public static DatePicker getDatePicker() {
		return new DatePicker();
	}
	
	public static CheckBox getCheckBox() {
		return new CheckBox();
	}
	
	public static ChoiceBox<String> getChoiceBox() {
		return new ChoiceBox<String>();
	}
	
	public static Button getButton(String name) {
		return new Button(name);
	}
}
