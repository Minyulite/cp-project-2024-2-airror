package panes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logics.FlightData;
import logics.PurchaseData;
import logics.RequestData;
import pages.MainPage;
import pages.PurchasePage;
import utils.GoTo;
import utils.IOReaderWriter;
import utils.UIComponent;

public class SearchFlightAvailableEachPane extends AnchorPane {

	private FlightData flightData;

	public SearchFlightAvailableEachPane(FlightData flightData, RequestData requestData, double prefWidth,
			double prefHeight) {
		super();
		this.setFlightData(flightData);
		this.setPrefWidth(prefWidth);
		this.setPrefHeight(prefHeight);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));

		Label nameLabel = UIComponent.getLabel(flightData.getAirlineName(), 16);
		Label departTimeLabel = UIComponent.getLabel(flightData.getDepartTime(), 16);
		Label arrivalTimeLabel = UIComponent.getLabel(flightData.getArrivalTime(), 16);
		Label priceLabel = UIComponent.getLabel("฿" + flightData.getPrice(), 30);
		Label perPersonLabel = UIComponent.getLabel("Per Person", 30);
		Label departAbbr = UIComponent.getLabel(flightData.getDepartAbbr(), 16);
		Label destinyAbbr = UIComponent.getLabel(flightData.getDestinyAbbr(), 16);

		Label durationLabel = UIComponent.getLabel(UIComponent
				.intMintoStrHour(UIComponent.getDuration(flightData.getDepartTime(), flightData.getArrivalTime())), 16);
		Button selectBtn = UIComponent.getButton("Select");
		ImageView imageView = UIComponent.getImageView("img/circle_line.png", 16, true);

		nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		departTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		arrivalTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
//		priceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
//		perPersonLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		departAbbr.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 16));
		destinyAbbr.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 16));
		selectBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		flightData.getAirlineImage().setFitHeight(125);

		this.getChildren().addAll(flightData.getAirlineImage(), nameLabel, departAbbr, destinyAbbr, departTimeLabel,
				arrivalTimeLabel, priceLabel, perPersonLabel, selectBtn, imageView, durationLabel);

		SearchFlightAvailableEachPane.setTopLeftAnchor(flightData.getAirlineImage(), 10, 10);
		SearchFlightAvailableEachPane.setTopLeftAnchor(nameLabel, 10, 10 + flightData.getAirlineImage().getFitHeight());
		SearchFlightAvailableEachPane.setTopLeftAnchor(departTimeLabel, 300, 60);
		SearchFlightAvailableEachPane.setTopLeftAnchor(arrivalTimeLabel, 600, 60);
		SearchFlightAvailableEachPane.setTopLeftAnchor(departAbbr, 315, 100);
		SearchFlightAvailableEachPane.setTopLeftAnchor(destinyAbbr, 615, 100);
		SearchFlightAvailableEachPane.setTopLeftAnchor(priceLabel, 800, 55);
		SearchFlightAvailableEachPane.setTopLeftAnchor(perPersonLabel, 785, 95);
		SearchFlightAvailableEachPane.setTopLeftAnchor(selectBtn, 1000, 70);
		SearchFlightAvailableEachPane.setTopLeftAnchor(imageView, 420, 80);
		SearchFlightAvailableEachPane.setTopLeftAnchor(durationLabel, 445, 55);

		// add EventHandler
		selectBtn.setOnMouseClicked((event) -> {

			PurchasePage.getPendingList().add(new PurchaseData(flightData, requestData.getClasses(),
					requestData.getAdultField(), requestData.getChildrenField(), requestData.getToddlerField()));
			if (requestData.isReturn()) {

				RequestData returnRequestData = new RequestData(requestData);

				String departField = returnRequestData.getDepartField();
				returnRequestData.setDepartField(returnRequestData.getDestinyField());
				returnRequestData.setDestinyField(departField);
				LocalDate temp = returnRequestData.getDepartDate();
				returnRequestData.setDepartDate(returnRequestData.getDestinyDate());
				returnRequestData.setDestinyDate(temp);
				returnRequestData.setReturn(false);

				GoTo.goToFlightAvailablePage(returnRequestData);
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText(null);
				alert.setContentText("You are ineligible to refund tickets, please make certain before confirming");
				alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
				Optional<ButtonType> response = alert.showAndWait();
				if (response.isPresent() && response.get() == ButtonType.YES) {
					for (PurchaseData pd : PurchasePage.getPendingList()) {
						PurchasePage.getPurchasesList().add(pd);
						System.out.println(pd.getFlightData());
						System.out.println(SearchFlightAvailablePane.getFlightsList().remove(pd.getFlightData()));
					}

					PurchasePage.getPendingList().clear();
					MainPage.reset();
					GoTo.goToMainPage(false);
				} else {
					event.consume();
				}

			}
		});
		
		// set Style
		selectBtn.setStyle("-fx-background-color: #47a2fc; -fx-font-weight: bold; -fx-font-size: 25px;"
				+ " -fx-text-fill: white");
		selectBtn.setOnMouseEntered((event) -> {
			this.setCursor(Cursor.HAND);
			selectBtn.setStyle("-fx-background-color: #7dbeff; -fx-font-weight: bold; -fx-font-size: 25px;"
					+ " -fx-text-fill: white");	
		});
		selectBtn.setOnMouseExited((event) -> {
			this.setCursor(Cursor.DEFAULT);
			selectBtn.setStyle("-fx-background-color: #47a2fc; -fx-font-weight: bold; -fx-font-size: 25px;"
					+ " -fx-text-fill: white");
		});
	}

	public FlightData getFlightData() {
		return flightData;
	}

	public void setFlightData(FlightData flightData) {
		this.flightData = flightData;
	}

	public static void setTopLeftAnchor(Node node, double left, double top) {
		MainPage.setTopAnchor(node, top);
		MainPage.setLeftAnchor(node, left);
	}
}