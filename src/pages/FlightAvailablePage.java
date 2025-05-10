package pages;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logics.FlightData;
import logics.RequestData;
import panes.SearchFlightAvailableEachPane;
import panes.SearchFlightAvailablePane;
import utils.GoTo;
import utils.IOReaderWriter;
import utils.UIComponent;

public class FlightAvailablePage extends Page {

	private static ArrayList<String> airlinesCondition = new ArrayList<>();
	private RequestData requestData;

	public FlightAvailablePage(RequestData requestData) {
		this.setRequestData(requestData);

		this.getChildren().add(this.getCanvas());
		GraphicsContext gc = canvas.getGraphicsContext2D();

		setStyle();

		setHeader(gc);

		setMiddle(gc, requestData);
	}

	@Override
	public void setStyle() {
		this.setMaxWidth(UIComponent.USER_MAX_SCREEN_WIDTH);
//		this.setPrefHeight(UIComponent.USER_MAX_SCREEN_HEIGHT + 300);
//		this.setMaxHeight(UIComponent.USER_MAX_SCREEN_HEIGHT + 300);
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
//		BorderPane.setAlignment(header, Pos.CENTER);
		// **header
		this.getChildren().add(borderPane);
		FlightAvailablePage.setLeftAnchor(borderPane, 0.0);
		FlightAvailablePage.setRightAnchor(borderPane, 50.0);

		gc.setFill(Color.web("#51abf5"));
		gc.fillRect(0, 0, UIComponent.USER_MAX_SCREEN_WIDTH, 100);
		UIComponent.drawLine(0, 100, UIComponent.USER_MAX_SCREEN_WIDTH, 100, gc);
		// **header
	}

//	@Override
	@SuppressWarnings("unchecked")
	public void setMiddle(GraphicsContext gc, RequestData requestData) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 100, UIComponent.USER_MAX_SCREEN_WIDTH, UIComponent.USER_MAX_SCREEN_HEIGHT - 100);

		UIComponent.drawLine(0, 260, UIComponent.USER_MAX_SCREEN_WIDTH, 260, gc);
		Map<String, String> townName = IOReaderWriter.getMap("/text/town.txt");
		String[] departSp = requestData.getDepartField().split(" - ");
		String[] destinySp = requestData.getDestinyField().split(" - ");
		Label departLabel = UIComponent.getLabel(departSp[0] + "(" + townName.get(departSp[0]) + ")", 20);
		Label destinyLabel = UIComponent.getLabel(destinySp[0] + "(" + townName.get(destinySp[0]) + ")", 20);
		departLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
		destinyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
		this.getChildren().add(departLabel);
		this.getChildren().add(destinyLabel);
		FlightAvailablePage.setTopLeftAnchor(departLabel, 100, 125);
		ImageView arrow = UIComponent.getImageView("img/arrow.png", 45, true);
		this.getChildren().add(arrow);
		Label departName = UIComponent.getLabel(departSp[1], 15);
		Label destinyName = UIComponent.getLabel(destinySp[1], 15);
//		departName.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
//		destinyName.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
		this.getChildren().addAll(departName, destinyName);

		Map<String, String> months = new HashMap<String, String>();
		months.put("01", "Jan");
		months.put("02", "Feb");
		months.put("03", "Mar");
		months.put("04", "Apr");
		months.put("05", "May");
		months.put("06", "Jun");
		months.put("07", "Jul");
		months.put("08", "Aug");
		months.put("09", "Sep");
		months.put("10", "Oct");
		months.put("11", "Nov");
		months.put("12", "Dec");

		String[] departDate = requestData.getDepartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).split("-");
		String format = departDate[0] + " " + months.get(departDate[1]) + " " + departDate[2] + "  /  " + "Adult : "
				+ requestData.getAdultField() + " Children : " + requestData.getChildrenField() + " Toddler : "
				+ requestData.getToddlerField() + "  /  " + requestData.getClasses();
		Label infoLabel = UIComponent.getLabel(format, 20);
		this.getChildren().add(infoLabel);

		Platform.runLater(() -> {
			FlightAvailablePage.setTopLeftAnchor(arrow, 100 + departLabel.getWidth() + 20, 125);
			FlightAvailablePage.setTopLeftAnchor(destinyLabel, 100 + departLabel.getWidth() + 175, 125);
			FlightAvailablePage.setTopLeftAnchor(departName, 100, 167);
			FlightAvailablePage.setTopLeftAnchor(destinyName, 100 + departLabel.getWidth() + 175, 167);
			FlightAvailablePage.setTopLeftAnchor(infoLabel, 100, 207);
		});

		Button backBtn = UIComponent.getButton("Back");
		backBtn.setPrefWidth(180);
		backBtn.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
		this.getChildren().add(backBtn);
		FlightAvailablePage.setTopLeftAnchor(backBtn, UIComponent.USER_MAX_SCREEN_WIDTH - 220, 160);
		backBtn.setOnMouseClicked((e) -> {
//			IOReaderWriter.clearTextFile("res/text/pending.txt");
			if(PurchasePage.getPendingList().size() > 0) {
				PurchasePage.getPendingList().remove(PurchasePage.getPendingList().size() - 1);
			}
			GoTo.back();
		});

		gc.setFill(Color.web("#83cff7"));
		gc.fillRect(300, 340, UIComponent.USER_MAX_SCREEN_WIDTH - 300, UIComponent.USER_MAX_SCREEN_HEIGHT);
		gc.setFill(Color.BLACK);
		UIComponent.drawLine(300, 260, 300, UIComponent.USER_MAX_SCREEN_HEIGHT, gc);

		Label selectAirline = UIComponent.getLabel("Choose Airlines", 20);
		this.getChildren().add(selectAirline);
		FlightAvailablePage.setTopLeftAnchor(selectAirline, 15, 295);
		selectAirline.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		Label goatAirline = UIComponent.getLabel("Goat airline", 15);
		Label penguinAirline = UIComponent.getLabel("Penguin airline", 15);
		Label shibaAirline = UIComponent.getLabel("Shiba airline", 15);
		Label giraffeAirline = UIComponent.getLabel("Giraffe airline", 15);
		Label chickenAirline = UIComponent.getLabel("Chicken airline", 15);
		Label bananaAirline = UIComponent.getLabel("Banana airline", 15);

		CheckBox goatCheck = UIComponent.getCheckBox();
		CheckBox penguinCheck = UIComponent.getCheckBox();
		CheckBox shibaCheck = UIComponent.getCheckBox();
		CheckBox giraffeCheck = UIComponent.getCheckBox();
		CheckBox chickenCheck = UIComponent.getCheckBox();
		CheckBox bananaCheck = UIComponent.getCheckBox();

		Label[] labelNode = { goatAirline, penguinAirline, shibaAirline, giraffeAirline, chickenAirline,
				bananaAirline };
		CheckBox[] checkNode = { goatCheck, penguinCheck, shibaCheck, giraffeCheck, chickenCheck, bananaCheck };

		this.getChildren().addAll(labelNode);
		this.getChildren().addAll(checkNode);

		for (int i = 1; i <= labelNode.length; ++i) {
			FlightAvailablePage.setTopLeftAnchor(checkNode[i - 1], 15, 295 + 50 * i);
			FlightAvailablePage.setTopLeftAnchor(labelNode[i - 1], 15 + 40, 295 + 50 * i);
		}

		SearchFlightAvailablePane searchPane = new SearchFlightAvailablePane(requestData);

		ScrollPane flightAvailablePage = new ScrollPane(searchPane);
		flightAvailablePage.setHbarPolicy(ScrollBarPolicy.NEVER);
		flightAvailablePage.setVbarPolicy(ScrollBarPolicy.NEVER);

		this.getChildren().add(flightAvailablePage);
		flightAvailablePage.setPrefWidth(UIComponent.USER_MAX_SCREEN_WIDTH - 300);
		flightAvailablePage.setMaxHeight(UIComponent.USER_MAX_SCREEN_HEIGHT - 410);
//		flightsAvailable.setMaxHeight(UIComponent.USER_MAX_SCREEN_HEIGHT);
//		flightsAvailable.setBackground(new Background(new BackgroundFill(Color.web("#83cff7"), CornerRadii.EMPTY, Insets.EMPTY)));
		FlightAvailablePage.setTopLeftAnchor(flightAvailablePage, 302, 340);

		// Add EventListener

		this.setAirlineEventListener(searchPane, requestData, goatCheck, "Goat airline");
		this.setAirlineEventListener(searchPane, requestData, penguinCheck, "Penguin airline");
		this.setAirlineEventListener(searchPane, requestData, shibaCheck, "Shiba airline");
		this.setAirlineEventListener(searchPane, requestData, giraffeCheck, "Giraffe airline");
		this.setAirlineEventListener(searchPane, requestData, chickenCheck, "Chicken airline");
		this.setAirlineEventListener(searchPane, requestData, bananaCheck, "Banana airline");

		// sorting by price, duration, departTime, arrivalTime
		Label priceSort = UIComponent.getLabel("Price", 20);
		Label durationSort = UIComponent.getLabel("Duration", 20);
		Label arrivalTimeSort = UIComponent.getLabel("Arrival Time", 20);

		ImageView priceTriangle = UIComponent.getImageView("img/triangle.png", 30, true);
		ImageView durationTriangle = UIComponent.getImageView("img/triangle.png", 30, true);
		ImageView arrivalTimeTriangle = UIComponent.getImageView("img/triangle.png", 30, true);

		priceSort.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		durationSort.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		arrivalTimeSort.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		this.getChildren().addAll(priceSort, durationSort, arrivalTimeSort, priceTriangle, durationTriangle,
				arrivalTimeTriangle);

		FlightAvailablePage.setTopLeftAnchor(priceSort, 330, 290);
		FlightAvailablePage.setTopLeftAnchor(durationSort, 330 + 500, 290);
		FlightAvailablePage.setTopLeftAnchor(arrivalTimeSort, 330 + 1000, 290);

		Platform.runLater(() -> {
			FlightAvailablePage.setTopLeftAnchor(priceTriangle, 330 + priceSort.getWidth(), 285);
			FlightAvailablePage.setTopLeftAnchor(durationTriangle, 330 + 500 + durationSort.getWidth(), 285);
			FlightAvailablePage.setTopLeftAnchor(arrivalTimeTriangle, 330 + 1000 + arrivalTimeSort.getWidth(), 285);
		});

		FlightAvailablePage.setSortingEventListener(searchPane, priceTriangle, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				SearchFlightAvailableEachPane left = (SearchFlightAvailableEachPane) o1;
				SearchFlightAvailableEachPane right = (SearchFlightAvailableEachPane) o2;
				if (left.getFlightData().getPrice() < right.getFlightData().getPrice())
					return -1;
				else if (left.getFlightData().getPrice() > right.getFlightData().getPrice())
					return 1;
				return 0;
			}
		});

		FlightAvailablePage.setSortingEventListener(searchPane, durationTriangle, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				SearchFlightAvailableEachPane left = (SearchFlightAvailableEachPane) o1;
				SearchFlightAvailableEachPane right = (SearchFlightAvailableEachPane) o2;
				int leftDuration = UIComponent.getDuration(left.getFlightData().getDepartTime(),
						left.getFlightData().getArrivalTime());
				int rightDuration = UIComponent.getDuration(right.getFlightData().getDepartTime(),
						right.getFlightData().getArrivalTime());
				if (leftDuration < rightDuration)
					return -1;
				else if (leftDuration > rightDuration)
					return 1;
				return 0;
			}
		});

		FlightAvailablePage.setSortingEventListener(searchPane, arrivalTimeTriangle, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				SearchFlightAvailableEachPane left = (SearchFlightAvailableEachPane) o1;
				SearchFlightAvailableEachPane right = (SearchFlightAvailableEachPane) o2;
				int arrivalTimeLeft = UIComponent.getDuration(left.getFlightData().getDepartTime(),
						left.getFlightData().getArrivalTime())
						+ UIComponent.strHourToIntMin(left.getFlightData().getDepartTime());
				int arrivalTimeRight = UIComponent.getDuration(right.getFlightData().getDepartTime(),
						right.getFlightData().getArrivalTime())
						+ UIComponent.strHourToIntMin(right.getFlightData().getDepartTime());
				if (arrivalTimeLeft < arrivalTimeRight)
					return -1;
				else if (arrivalTimeLeft > arrivalTimeRight)
					return 1;
				return 0;
			}

		});

	}

	public static void setSortingEventListener(SearchFlightAvailablePane searchPane, ImageView imageView,
			Comparator comparator) {
		imageView.setOnMouseClicked((event) -> {
			new Thread(() -> {
//				ArrayList<FlightData> temp = new ArrayList<>();
				if(searchPane.getChildren().get(0).getClass() == Label.class) {
					return;
				}
				ArrayList<SearchFlightAvailableEachPane> temp = new ArrayList<>();
				for (Node s : searchPane.getChildren()) {
//					temp.add(((SearchFlightAvailableEachPane) s).getFlightData());
					temp.add((SearchFlightAvailableEachPane) s);
				}
				
				Collections.sort(temp, comparator);

				Platform.runLater(() -> {
					searchPane.getChildren().clear();
					for (SearchFlightAvailableEachPane s : temp) {
						searchPane.getChildren().add(s);
					}
				});
			}).start();
		});
	}

	public void getByConditions(SearchFlightAvailablePane searchPane, RequestData requestData) {
		new Thread(() -> {
			Platform.runLater(() -> {
				searchPane.getChildren().clear();
			});
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			System.out.println("BEFORE : " + searchPane.getChildren().size());

			for (int i = 0; i < searchPane.flightsSelected.size(); ++i) {
				if (airlinesCondition.contains(searchPane.flightsSelected.get(i).getAirlineName())) {
					int f = i;
					Platform.runLater(() -> {
						SearchFlightAvailableEachPane temp = new SearchFlightAvailableEachPane(
								searchPane.flightsSelected.get(f), requestData, UIComponent.USER_MAX_SCREEN_WIDTH - 500,
								200);
						searchPane.getChildren().add(temp);
					});
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if(searchPane.getChildren().size() == 0) {
				Platform.runLater(() -> {
					searchPane.setAlignment(Pos.CENTER);
					Label label = UIComponent.getLabel("SORRY, THERE AREN'T ANY AVAILABLE FLIGHTS", 40);
					label.setFont(Font.font("verdana", FontWeight.BOLD, 40));
					searchPane.getChildren().add(label);					
				});
			}
		}).start();
	}

	public void setAirlineEventListener(SearchFlightAvailablePane searchPane, RequestData requestData,
			CheckBox checkBox, String condition) {
		checkBox.setOnMouseClicked((event) -> {
			if (checkBox.isSelected()) {
				if (!airlinesCondition.contains(condition)) {
					airlinesCondition.add(condition);
				}
				getByConditions(searchPane, requestData);
			} else if (airlinesCondition.size() == 1) {
				airlinesCondition.remove(condition);
				searchPane.getChildren().clear();
				airlinesCondition.remove(condition);
				for (int i = 0; i < searchPane.flightsSelected.size(); ++i) {
					int f = i;
					SearchFlightAvailableEachPane temp = new SearchFlightAvailableEachPane(
							searchPane.flightsSelected.get(f), requestData, UIComponent.USER_MAX_SCREEN_WIDTH - 500,
							200);
					Platform.runLater(() -> {
						searchPane.getChildren().add(temp);
					});
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Platform.runLater(() -> {
					if(searchPane.getChildren().size() == 0) {
							searchPane.setAlignment(Pos.CENTER);
							Label label = UIComponent.getLabel("SORRY, THERE AREN'T ANY AVAILABLE FLIGHTS", 40);
							label.setFont(Font.font("verdana", FontWeight.BOLD, 40));
							searchPane.getChildren().add(label);					
					}
				});
			} else {
				airlinesCondition.remove(condition);
				getByConditions(searchPane, requestData);
			}
		});
	}

	public RequestData getRequestData() {
		return requestData;
	}

	public void setRequestData(RequestData requestData) {
		this.requestData = requestData;
	}
}
