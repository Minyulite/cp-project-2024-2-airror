package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logics.FlightData;
import logics.PurchaseData;
import panes.SearchPurchaseEachPane;
import panes.SearchPurchasePane;
import utils.GoTo;
import utils.UIComponent;

public class PurchasePage extends Page {

	public static ArrayList<PurchaseData> pendingList = new ArrayList<>();

	public static ArrayList<PurchaseData> purchasesList = new ArrayList<>();
	
	public PurchasePage() {
		this.getChildren().add(this.getCanvas());
		GraphicsContext gc = this.getCanvas().getGraphicsContext2D();

		setStyle();

		setHeader(gc);

		setMiddle(gc);
	}
	
	@Override
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
		
		HBox userProfile = new HBox();
		
		userProfile.getChildren().add(userImageView = UIComponent.getImageView("img/user.png", 50, true));
		userProfile.getChildren().add(UIComponent.getText(LoginPage.loginUsername, 20));
		userProfile.setSpacing(10);
		userProfile.setAlignment(Pos.CENTER);
		
		header.getChildren().add(userProfile);
		header.setSpacing(30);
		
		borderPane.setRight(header);
//BorderPane.setAlignment(header, Pos.CENTER);
		// **header
		this.getChildren().add(borderPane);
		PurchasePage.setLeftAnchor(borderPane, 0.0);
		PurchasePage.setRightAnchor(borderPane, 50.0);
		
		gc.setFill(Color.web("#51abf5"));
		gc.fillRect(0, 0, UIComponent.USER_MAX_SCREEN_WIDTH, 100);
		UIComponent.drawLine(0, 100, UIComponent.USER_MAX_SCREEN_WIDTH, 100, gc);
		// **header
	}


	public void setMiddle(GraphicsContext gc) {
		ImageView shoppingImageView = UIComponent.getImageView("img/shopping.png", 50, true);
		this.getChildren().add(shoppingImageView);
		PurchasePage.setTopLeftAnchor(shoppingImageView, 20, 130);

		Label purchaseLabel = UIComponent.getLabel("Purchases", 40);
		purchaseLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
		this.getChildren().add(purchaseLabel);
		PurchasePage.setTopLeftAnchor(purchaseLabel, 20 + 50 + 20, 128);

		Button upComingBtn = UIComponent.getButton("Up Coming");
		Button pastBtn = UIComponent.getButton("Past");
		upComingBtn.setStyle("-fx-background-color: lightblue; -fx-font-size: 20; -fx-font-weight: bold; -fx-background-radius: 30px;");
		pastBtn.setStyle("-fx-background-color: lightpink; -fx-font-size: 20; -fx-font-weight: bold; -fx-background-radius: 30px;");
		
		this.getChildren().addAll(upComingBtn, pastBtn);
		PurchasePage.setTopLeftAnchor(upComingBtn, 20, 200);
		PurchasePage.setTopLeftAnchor(pastBtn, 170, 200);

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 100, UIComponent.USER_MAX_SCREEN_WIDTH, 210);
		
		gc.setFill(Color.BLACK);
		UIComponent.drawLine(0, 260, UIComponent.USER_MAX_SCREEN_WIDTH, 260, gc);
		
		Label date = UIComponent.getLabel("date", 20);
		Label depart = UIComponent.getLabel("depart", 20);
		Label origin = UIComponent.getLabel("origin", 20);
		Label arrive = UIComponent.getLabel("arrive", 20);
		Label destination = UIComponent.getLabel("destination", 20);
		Label bookingNumber = UIComponent.getLabel("booking number", 20);
		
		double dist = UIComponent.USER_MAX_SCREEN_WIDTH / 5 - 45;
		
		Label[] labels = {date, depart, origin, arrive, destination, bookingNumber};
		
		this.getChildren().addAll(labels);
		for(int i = 0; i < labels.length; ++i) {
			PurchasePage.setTopLeftAnchor(labels[i], 30 + dist * i, 270);
		}

		UIComponent.drawLine(0, 310, UIComponent.USER_MAX_SCREEN_WIDTH, 310, gc);

		SearchPurchasePane purchasesPane = new SearchPurchasePane();
		ScrollPane purchasesScrollPane = new ScrollPane(purchasesPane);
		purchasesScrollPane.setPrefWidth(UIComponent.USER_MAX_SCREEN_WIDTH);
		purchasesScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		purchasesScrollPane.setPrefHeight(UIComponent.USER_MAX_SCREEN_HEIGHT - 381.5);
		
		this.getChildren().add(purchasesScrollPane);
		PurchasePage.setTopLeftAnchor(purchasesScrollPane, 0, 310);
		
		LocalDate nowDate = LocalDate.parse("07/05/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		int nowTime = UIComponent.strHourToIntMin("8:45");
		
		upComingBtn.setOnMouseClicked((event) -> {
			new Thread(() -> {
				ArrayList<Node> selected = new ArrayList<>();
				int i = 1;
				for(PurchaseData purchaseData : PurchasePage.getPurchasesList()) {
					if(purchaseData.getFlightData().getDepartDate().isAfter(nowDate) || (purchaseData.getFlightData().getDepartDate().isEqual(nowDate)
							&& nowTime <= UIComponent.strHourToIntMin(purchaseData.getFlightData().getDepartTime()))) {
						selected.add(new SearchPurchaseEachPane(purchaseData, i, purchasesPane));
					}
					++i;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Platform.runLater(() -> {
					purchasesPane.getChildren().clear();
					if(selected.size() == 0) {
						purchasesPane.setAlignment(Pos.CENTER);
						Label label = UIComponent.getLabel("SORRY, THERE AREN'T ANY PURCHASES", 40);
						label.setPadding(new Insets(50, 0, 0, 0));
						label.setFont(Font.font("verdana", FontWeight.BOLD, 40));
						purchasesPane.getChildren().add(label);		
					} else {
						purchasesPane.getChildren().setAll(selected);
					}
				});				
			}).start();			
		});
		
		pastBtn.setOnMouseClicked((event) -> {
			new Thread(() -> {
				ArrayList<Node> selected = new ArrayList<>();
				int i = 1;
				for(PurchaseData purchaseData : PurchasePage.getPurchasesList()) {
					if(purchaseData.getFlightData().getDepartDate().isBefore(nowDate) || (purchaseData.getFlightData().getDepartDate().isEqual(nowDate)
							&& nowTime > UIComponent.strHourToIntMin(purchaseData.getFlightData().getDepartTime()))) {
						selected.add(new SearchPurchaseEachPane(purchaseData, i, purchasesPane));
					}
					++i;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Platform.runLater(() -> {
					purchasesPane.getChildren().clear();
					if(selected.size() == 0) {
						purchasesPane.setAlignment(Pos.CENTER);
						Label label = UIComponent.getLabel("SORRY, THERE AREN'T ANY PURCHASES", 40);
						label.setPadding(new Insets(50, 0, 0, 0));
						label.setFont(Font.font("verdana", FontWeight.BOLD, 40));
						purchasesPane.getChildren().add(label);		
					} else {
						purchasesPane.getChildren().setAll(selected);
					}
				});				
			}).start();			
		});
	}

	@Override
	public void setStyle() {
		this.setMaxWidth(UIComponent.USER_MAX_SCREEN_WIDTH);
//		this.setPrefHeight(UIComponent.USER_MAX_SCREEN_HEIGHT + 300);
//		this.setMaxHeight(UIComponent.USER_MAX_SCREEN_HEIGHT + 300);
	}

	public static ArrayList<PurchaseData> getPendingList() {
		return pendingList;
	}
	
	public static void setPendingList(ArrayList<PurchaseData> pendingList) {
		PurchasePage.pendingList = pendingList;
	}
	
	public static ArrayList<PurchaseData> getPurchasesList() {
		return purchasesList;
	}
	
	public static void setPurchasesList(ArrayList<PurchaseData> purchasesList) {
		PurchasePage.purchasesList = purchasesList;
	}

}
