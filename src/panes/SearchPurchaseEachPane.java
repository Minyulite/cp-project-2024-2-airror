package panes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logics.PurchaseData;
import pages.PurchasePage;
import utils.IOReaderWriter;
import utils.UIComponent;

public class SearchPurchaseEachPane extends AnchorPane {

	private PurchaseData purchaseData;

	public SearchPurchaseEachPane(PurchaseData purchaseData, int number, SearchPurchasePane purchasePane) {
		this.setPurchaseData(purchaseData);

		Label date = UIComponent
				.getLabel(purchaseData.getDepartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 16);
		Label depart = UIComponent.getLabel(purchaseData.getDepartTime(), 16);
		Label origin = UIComponent
				.getLabel(purchaseData.getDepartAirportName() + "(" + purchaseData.getDepartAbbr() + ")", 16);
		Label arrive = UIComponent.getLabel(purchaseData.getArrivalTime(), 16);
		Label destination = UIComponent
				.getLabel(purchaseData.getDestinyAirportName() + "(" + purchaseData.getDestinyAbbr() + ")", 16);
		Label bookingNumber = UIComponent.getLabel(number + "", 16);
		ImageView cancelImageView = UIComponent.getImageView("img/cancel.png", 40, true);

		Label[] labels = { date, depart, origin, arrive, destination, bookingNumber };
		this.getChildren().addAll(labels);
		this.getChildren().add(cancelImageView);

		double dist = UIComponent.USER_MAX_SCREEN_WIDTH / 5 - 45;

		for (int i = 0; i < labels.length; ++i) {
			SearchPurchaseEachPane.setTopLeftAnchor(labels[i], 30 + dist * i, 20);
		}

		SearchPurchaseEachPane.setTopLeftAnchor(origin, 30 + dist * 2 - 50, 20);
		SearchPurchaseEachPane.setTopLeftAnchor(destination, 30 + dist * 4 - 50, 20);
		SearchPurchaseEachPane.setTopLeftAnchor(bookingNumber, 30 + dist * 5 + 50, 20);
		SearchPurchaseEachPane.setTopLeftAnchor(cancelImageView, 30 + dist * 5 + 50 + 90, 10);

		cancelImageView.setOnMouseClicked((event) -> {
			String toAdd = purchaseData.getDepartAbbr() + "," + purchaseData.getDepartAirportName() + ","
					+ purchaseData.getDestinyAbbr() + "," + purchaseData.getDestinyAirportName() + ","
					+ purchaseData.getAirlineName() + "," + purchaseData.getDepartTime() + ","
					+ purchaseData.getArrivalTime() + "," + purchaseData.getPrice() + ","
					+ purchaseData.getDepartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			ArrayList<String> listOfFlightsString = IOReaderWriter
					.getStringsFromTextFile("res/text/fly_list_extended.txt");
			listOfFlightsString.add(toAdd);
			IOReaderWriter.writeTextFileFromStrings(listOfFlightsString, "res/text/fly_list_extended.txt");
			purchasePane.getChildren().remove(this);
			if (purchasePane.getChildren().size() == 0) {
				purchasePane.setAlignment(Pos.CENTER);
				Label label = UIComponent.getLabel("SORRY, THERE AREN'T ANY PURCHASES", 40);
				label.setPadding(new Insets(50, 0, 0, 0));
				label.setFont(Font.font("verdana", FontWeight.BOLD, 40));
				purchasePane.getChildren().add(label);
			}
			SearchPurchasePane.purchasesList.remove(this.getPurchaseData());
			IOReaderWriter.writeListOfPurchaseData(SearchPurchasePane.purchasesList, "res/text/purchases.txt");
		});

	}

	public static void setTopLeftAnchor(Node node, double left, double top) {
		AnchorPane.setTopAnchor(node, top);
		AnchorPane.setLeftAnchor(node, left);
	}

	public PurchaseData getPurchaseData() {
		return purchaseData;
	}

	public void setPurchaseData(PurchaseData purchaseData) {
		this.purchaseData = purchaseData;
	}
}
