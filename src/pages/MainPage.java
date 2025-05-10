package pages;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import logics.RequestData;
import logics.UserData;
import utils.GoTo;
import utils.IOReaderWriter;
import utils.UIComponent;
import application.MyApplication;
import exceptions.IncorrectAndPartialFillFormException;
import exceptions.IncorrectFillFormException;
import exceptions.PartialFillFormException;
import panes.SearchAirportPane;

import java.io.IOException;
import java.util.ArrayList;

public class MainPage extends Page {
	public static MainPage mainPageInstance = null;
	public static ScrollPane departSearchBar = null;
	public static ScrollPane destinySearchBar = null;

	public static TextField departField = null;
	public static TextField destinyField = null;
	public static DatePicker departDatePicker = null;
	public static DatePicker destinyDatePicker = null;
	public static TextField adultField = null;
	public static TextField childrenField = null;
	public static TextField toddlerField = null;
	public static CheckBox checkBox = null;
	public static ChoiceBox<String> choiceBox = null;

	public static Thread adsThread = null;

	private MainPage() {
		// initiate canvas
		this.getChildren().add(this.getCanvas());
		GraphicsContext gc = canvas.getGraphicsContext2D();

		setStyle();

		// **header**//
		setHeader(gc);

		// **middle**//
		setMiddle(gc);

//		gc.fillRect(0, UIComponent.USER_MAX_SCREEN_HEIGHT, UIComponent.USER_MAX_SCREEN_WIDTH, 2000);
		// **ads"//
//		setAds(gc);

	}

	public void setAds(GraphicsContext gc) {

		adsThread = new Thread(() -> {
			int start = 0;
			Image ads = UIComponent.getImage("img/ads.png", 1847 / 461 * 325, 520, true, true);
			while (true) {
//				ImageView ads = UIComponent.getImageView("img/ads.png", 325, true);
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
				WritableImage croppedImage = new WritableImage(ads.getPixelReader(), start, 0,
						(int) (ads.getWidth() / 2) - 10, (int) ads.getHeight());
				Platform.runLater(() -> {
					gc.clearRect((UIComponent.USER_MAX_SCREEN_WIDTH - 980) / 2 + 175, 520,
							(int) (ads.getWidth() / 2) - 10, (int) ads.getHeight());
					gc.drawImage(croppedImage, (UIComponent.USER_MAX_SCREEN_WIDTH - 980) / 2 + 175, 520);
				});
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					break;
//					e.printStackTrace();
				}
//				MainPage.setTopLeftAnchor(ads, (UIComponent.USER_MAX_SCREEN_WIDTH - 1847/461*325)/2, 520);
				start += 3.5;
				if (start > (int) (ads.getWidth()) / 2) {
					start = 0;
				}
			}
		});

		adsThread.start();

	}

	public static void setFont(Label[] arr, Font font) {
		for (Label node : arr) {
			node.setFont(font);
		}
	}

	public void setStyle() {
		this.setMaxWidth(UIComponent.USER_MAX_SCREEN_WIDTH);
//		this.setPrefHeight(UIComponent.USER_MAX_SCREEN_HEIGHT + 300);
//		this.setMaxHeight(UIComponent.USER_MAX_SCREEN_HEIGHT + 300);
	}

	public void setHeader(GraphicsContext gc) {
		BorderPane borderPane = new BorderPane();

		// set width
		borderPane.setPrefWidth(UIComponent.USER_MAX_SCREEN_WIDTH);

		ImageView imageView = UIComponent.getImageView("img/logo_real.png", 100, true);

		borderPane.setLeft(imageView);

		HBox header = new HBox();

		header.setAlignment(Pos.CENTER);

		// not yet add EventHandler!!!!!!!!!!!
		Text home, purchases, aboutUs;
		ImageView userImageView;
		header.getChildren().add(home = UIComponent.getText("Home", 20));
		home.setOnMouseClicked((e) -> GoTo.goToMainPage());
		header.getChildren().add(purchases = UIComponent.getText("Purchases", 20));
		purchases.setOnMouseClicked((e) -> GoTo.goToPurchasePage());
		header.getChildren().add(aboutUs = UIComponent.getText("About us", 20));
		aboutUs.setOnMouseClicked((e) -> GoTo.goToAboutUsPage());
		header.getChildren().add(userImageView = UIComponent.getImageView("img/user.png", 50, true));
		header.setSpacing(20);

		borderPane.setRight(header);
//		BorderPane.setAlignment(header, Pos.CENTER);
		// **header
		this.getChildren().add(borderPane);
		MainPage.setLeftAnchor(borderPane, 0.0);
		MainPage.setRightAnchor(borderPane, 50.0);

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, UIComponent.USER_MAX_SCREEN_WIDTH, 100);
//		UIComponent.drawLine(0, 100, UIComponent.USER_MAX_SCREEN_WIDTH, 100, gc);
		// **header
	}

//	@Override
	public void setMiddle(GraphicsContext gc) {
		// **middle
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0, 100, UIComponent.USER_MAX_SCREEN_WIDTH, 400);
		gc.setFill(Color.web("#3c9cf0"));
		gc.fillRoundRect(200, 150, UIComponent.USER_MAX_SCREEN_WIDTH - 2 * 200, 300, 30, 30);
		gc.setFill(Color.WHITE);
		gc.fillRoundRect(200, 190, UIComponent.USER_MAX_SCREEN_WIDTH - 2 * 200, 260, 30, 30);

		Text head = UIComponent.getText("One-Way / Round-Trip", Font.font("Arial", FontWeight.BOLD, 20));
		head.setFill(Color.WHITE);
		this.getChildren().add(head);
		MainPage.setTopAnchor(head, 160.0);
		MainPage.setLeftAnchor(head, 210.0);

		ImageView depart, target, date1, date2, swap, people, classes;
		MainPage.setTopLeftAnchor(depart = UIComponent.getImageView("img/depart.png", 50, true), 230.0, 200.0);
		MainPage.setTopLeftAnchor(target = UIComponent.getImageView("img/target.png", 50, true), 580.0, 200.0);
		MainPage.setTopLeftAnchor(people = UIComponent.getImageView("img/people.png", 50, true), 930.0, 200.0);
		MainPage.setTopLeftAnchor(date1 = UIComponent.getImageView("img/date.png", 50, true), 230.0, 355.0);
		MainPage.setTopLeftAnchor(date2 = UIComponent.getImageView("img/date.png", 50, true), 580.0, 355.0);
		MainPage.setTopLeftAnchor(classes = UIComponent.getImageView("img/classes.png", 50, true), 930.0, 315.0);
		MainPage.setTopLeftAnchor(swap = UIComponent.getImageView("img/swap.png", 50, true), 520.0, 260.0);

		this.getChildren().addAll(depart, target, people, date1, date2, classes, swap);

		Label departLabel, targetLabel, dateLabel1, dateLabel2, peopleLabel, classLabel;
		MainPage.setTopLeftAnchor(departLabel = UIComponent.getLabel("\u00B7 From", 16), 300.0, 215.0);
		MainPage.setTopLeftAnchor(targetLabel = UIComponent.getLabel("\u00B7 To", 16), 650.0, 215.0);
		MainPage.setTopLeftAnchor(peopleLabel = UIComponent.getLabel("\u00B7 Number of Passengers", 16), 1000.0, 215.0);
		MainPage.setTopLeftAnchor(dateLabel1 = UIComponent.getLabel("\u00B7 Depart date", 16), 230.0, 330.0);
		MainPage.setTopLeftAnchor(dateLabel2 = UIComponent.getLabel("\u00B7 Return date", 16), 580.0, 330.0);
		MainPage.setTopLeftAnchor(classLabel = UIComponent.getLabel("\u00B7 Class", 16), 1000.0, 330.0);
		this.getChildren().addAll(departLabel, targetLabel, peopleLabel, dateLabel1, dateLabel2, classLabel);
		Label[] arr = { departLabel, targetLabel, peopleLabel, dateLabel1, dateLabel2, classLabel };
		setFont(arr, Font.font("Arial", FontWeight.BOLD, 16));

		// Search
		departField = UIComponent.getTextField("", 280, 30);
		departField.setPromptText("Select your departure");
		MainPage.setTopLeftAnchor(departField, 230, 270);
		this.getChildren().add(departField);

		departField.setOnKeyPressed((e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (departSearchBar != null) {
					this.getChildren().remove(departSearchBar);
					departSearchBar = null;
				}
				VBox departSearchPage = new SearchAirportPane(departField.getText() + " 1");
				if (departSearchPage.getChildren().size() == 0) {
					return;
				}
				departSearchBar = new ScrollPane(departSearchPage);
				departSearchBar.setMaxHeight(300);
//				searchBar.setMaxWidth(280);
				departSearchBar.setPrefWidth(280);
				this.getChildren().add(departSearchBar);
				MainPage.setTopLeftAnchor(departSearchBar, 230, 300);
			}
		});
		ImageView cancel;
		MainPage.setTopLeftAnchor(cancel = UIComponent.getImageView("img/cancel.png", 30, true), 480.0, 270.0);
		this.getChildren().add(cancel);

		cancel.setOnMouseClicked((e) -> {
			if (departSearchBar != null) {
				this.getChildren().remove(departSearchBar);
				departSearchBar = null;
			}
			departField.setText("");
		});

		destinyField = UIComponent.getTextField("", 280, 30);
		destinyField.setPromptText("Select your destiny");
		MainPage.setTopLeftAnchor(destinyField, 580, 270);
		this.getChildren().add(destinyField);

		destinyField.setOnKeyPressed((e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (destinySearchBar != null) {
					this.getChildren().remove(destinySearchBar);
					destinySearchBar = null;
				}
				VBox destinySearchPage = new SearchAirportPane(destinyField.getText() + " 2");
				if (destinySearchPage.getChildren().size() == 0) {
					return;
				}
				destinySearchBar = new ScrollPane(destinySearchPage);
				destinySearchBar.setMaxHeight(300);
				destinySearchBar.setPrefWidth(280);
				this.getChildren().add(destinySearchBar);
				MainPage.setTopLeftAnchor(destinySearchBar, 580, 300);
			}
		});

		swap.setOnMouseClicked((event) -> {
			String temp = departField.getText();
			departField.setText(destinyField.getText());
			destinyField.setText(temp);
		});

		ImageView cancel2;
		MainPage.setTopLeftAnchor(cancel2 = UIComponent.getImageView("img/cancel.png", 30, true), 830.0, 270.0);
		this.getChildren().add(cancel2);

		cancel2.setOnMouseClicked((e) -> {
			if (destinySearchBar != null) {
				this.getChildren().remove(destinySearchBar);
				destinySearchBar = null;
			}
			destinyField.setText("");
		});

		// DatePicker
		departDatePicker = UIComponent.getDatePicker();
		departDatePicker.setPrefWidth(210);
		this.getChildren().add(departDatePicker);
		MainPage.setTopLeftAnchor(departDatePicker, 300, 365);

		destinyDatePicker = UIComponent.getDatePicker();
		destinyDatePicker.setPrefWidth(210);
		destinyDatePicker.setDisable(true);
		this.getChildren().add(destinyDatePicker);
		MainPage.setTopLeftAnchor(destinyDatePicker, 648, 365);

		// CheckBox
		checkBox = UIComponent.getCheckBox();
		this.getChildren().add(checkBox);
		MainPage.setTopLeftAnchor(checkBox, 685, 330);
		checkBox.setOnMouseClicked((e) -> {
			if (checkBox.isSelected()) {
				destinyDatePicker.setDisable(false);
			} else {
				destinyDatePicker.setDisable(true);
			}
		});

		// Number of passengers
		adultField = UIComponent.getTextField("", 93, 30);
		adultField.setPromptText("# Adult");
		childrenField = UIComponent.getTextField("", 93, 30);
		childrenField.setPromptText("# Children");
		toddlerField = UIComponent.getTextField("", 93, 30);
		toddlerField.setPromptText("# Toddler");
		this.getChildren().addAll(adultField, childrenField, toddlerField);
		MainPage.setTopLeftAnchor(adultField, 1000, 270);
		MainPage.setTopLeftAnchor(childrenField, 1100, 270);
		MainPage.setTopLeftAnchor(toddlerField, 1200, 270);

		Label adultLabel = UIComponent.getLabel("Adult", 14);
		Label childrenLabel = UIComponent.getLabel("Children", 14);
		Label toddlerLabel = UIComponent.getLabel("Toddler", 14);
		this.getChildren().addAll(adultLabel, childrenLabel, toddlerLabel);
		MainPage.setTopLeftAnchor(adultLabel, 1027, 250);
		MainPage.setTopLeftAnchor(childrenLabel, 1121, 250);
		MainPage.setTopLeftAnchor(toddlerLabel, 1223, 250);

		// ChoiceBox
		choiceBox = UIComponent.getChoiceBox();
		choiceBox.setPrefWidth(100);
		this.getChildren().add(choiceBox);
		MainPage.setTopLeftAnchor(choiceBox, 1000.0, 365.0);
		choiceBox.getItems().addAll(IOReaderWriter.getStringsFromTextFile("res/text/classes.txt"));

		Button searchBtn, resetBtn;
		this.getChildren().add(searchBtn = UIComponent.getButton("Search"));
		this.getChildren().add(resetBtn = UIComponent.getButton("Reset"));
		MainPage.setTopLeftAnchor(searchBtn, 1245, 410);
		MainPage.setTopLeftAnchor(resetBtn, 1191, 410);

		resetBtn.setOnMouseClicked((e) -> {
			reset();
		});

		// Exception
		searchBtn.setOnMouseClicked((e) -> {
			boolean incorrectFill = false;
			boolean partialFill = false;
			try {
				int total = -1;
				try {
					int adult = Integer.parseInt(adultField.getText());
					int children = Integer.parseInt(childrenField.getText());
					int toddler = Integer.parseInt(toddlerField.getText());
					String test1 = departField.getText().split(" - ")[1];
					String test2 = destinyField.getText().split(" - ")[1];
					total = adult + children + toddler;
				} catch(Exception e0) {
					incorrectFill = true;
				}
				if (departField.getText() == "" || destinyField.getText() == "" || departDatePicker.getValue() == null
						|| (checkBox.isSelected() && destinyDatePicker.getValue() == null) || choiceBox.getValue() == null
						|| total == 0) {
					partialFill = true;
				}
				
				if(incorrectFill && partialFill) throw new IncorrectAndPartialFillFormException();
				else if(incorrectFill) throw new IncorrectFillFormException();
				else if(partialFill) throw new PartialFillFormException();

				GoTo.goToFlightAvailablePage(new RequestData(departField.getText(), destinyField.getText(),
						departDatePicker.getValue(), destinyDatePicker.getValue(),
						Integer.parseInt(adultField.getText()), Integer.parseInt(childrenField.getText()),
						Integer.parseInt(toddlerField.getText()), checkBox.isSelected(), choiceBox.getValue()));
			} catch (IncorrectFillFormException e1) {
				System.out.println("1");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText(null);
				alert.setContentText("Please fill the informations in its \"CORRECT\" form");
				alert.getButtonTypes().setAll(ButtonType.OK);
				alert.showAndWait();
			} catch (PartialFillFormException e2) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText(null);
				alert.setContentText("Please fill all the \"IMPORTANT\" information");
				alert.getButtonTypes().setAll(ButtonType.OK);
				alert.showAndWait();
			} catch (IncorrectAndPartialFillFormException e3) {
				System.out.println("3");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText(null);
				alert.setContentText("Please fill the informations in its \"CORRECT\" form and fill all the \"IMPORTANT\" information");
				alert.getButtonTypes().setAll(ButtonType.OK);
				alert.showAndWait();
			}
		});

	}

	public static void reset() {
		departField.setText("");
		destinyField.setText("");
		departDatePicker.setValue(null);
//		destinyDatePicker.setValue(null);
		destinyDatePicker.setDisable(true);
		adultField.setText("");
		childrenField.setText("");
		toddlerField.setText("");
		checkBox.setSelected(false);
		choiceBox.setValue(null);
		if (departSearchBar != null) {
			MainPage.getInstance().getChildren().remove(departSearchBar);
			departSearchBar = null;
		}
		if (destinySearchBar != null) {
			MainPage.getInstance().getChildren().remove(destinySearchBar);
			destinySearchBar = null;
		}
	}

	public static MainPage getInstance() {
		if (mainPageInstance == null) {
			mainPageInstance = new MainPage();
		}
		return mainPageInstance;
	}
}
