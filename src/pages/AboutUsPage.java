package pages;

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.GoTo;
import utils.UIComponent;

public class AboutUsPage extends Page {
	
	public static AboutUsPage aboutUsPageInstance = null;
	
	private AboutUsPage() {
		canvas = this.getCanvas();
		this.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		setStyle();
		
		setHeader(gc);
		
		setMiddle(gc);
	}
	
	@Override
	public void setStyle() {
		this.setMaxWidth(UIComponent.USER_MAX_SCREEN_WIDTH);
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

		Text home, purchases, aboutUs;
		ImageView userImageView, logoutImageView;
		
		header.getChildren().add(home = UIComponent.getText("Home", 20));
		header.getChildren().add(purchases = UIComponent.getText("Purchases", 20));
		header.getChildren().add(aboutUs = UIComponent.getText("About us", 20));

		// add EventHandler
		home.setOnMouseClicked((e) -> GoTo.goToMainPage(false));
		purchases.setOnMouseClicked((e) -> GoTo.goToPurchasePage());
		aboutUs.setOnMouseClicked((e) -> GoTo.goToAboutUsPage());
		
		HBox userProfile = new HBox();
		
		userProfile.getChildren().add(userImageView = UIComponent.getImageView("img/user.png", 50, true));
		userProfile.getChildren().add(UIComponent.getText(LoginPage.loginUsername, 20));
		userProfile.getChildren().add(logoutImageView = UIComponent.getImageView("img/logout.png", 35, true));
		userProfile.setSpacing(10);
		userProfile.setAlignment(Pos.CENTER);
		
		logoutImageView.setOnMouseClicked((event) -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText(null);
			alert.setContentText("Do you want to leave?");
			alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> response = alert.showAndWait();
			if(response.isPresent() && response.get() == ButtonType.YES) {
				GoTo.goToLoginPage();
			}
		});
		
		Node[] toSetStyle = {home, purchases, aboutUs, logoutImageView};
		Node[] toSetCursor = {home, purchases, aboutUs, logoutImageView};
		for(Node node : toSetCursor) {
			node.setOnMouseEntered((event) -> {
				this.setCursor(Cursor.HAND);
			});
			node.setOnMouseExited((event) -> {
				this.setCursor(Cursor.DEFAULT);
			});
		}
		
		header.getChildren().add(userProfile);
		header.setSpacing(35);

		borderPane.setRight(header);
		
		this.getChildren().add(borderPane);
		MainPage.setLeftAnchor(borderPane, 0.0);
		MainPage.setRightAnchor(borderPane, 50.0);

		gc.setFill(Color.web("#b6e8f2"));
		gc.fillRect(0, 0, UIComponent.USER_MAX_SCREEN_WIDTH, 100);
//		UIComponent.drawLine(0, 100, UIComponent.USER_MAX_SCREEN_WIDTH, 100, gc);
		// **header
	}
	
	public void setMiddle(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 100, UIComponent.USER_MAX_SCREEN_WIDTH, 370);
		
		ImageView aboutUs = UIComponent.getImageView("img/about_us.png", 320, true);
		this.getChildren().add(aboutUs);
		AboutUsPage.setTopLeftAnchor(aboutUs, 0, 125);
		
		Label vibeShadow = UIComponent.getLabel("Fly Your Vibe", 30);
		vibeShadow.setFont(Font.font("arial", FontWeight.BOLD, 45));
		vibeShadow.setTextFill(Color.web("#a2a5a6"));
		this.getChildren().add(vibeShadow);
		AboutUsPage.setTopLeftAnchor(vibeShadow, UIComponent.USER_MAX_SCREEN_WIDTH / 2 - 93, 500);

		Label vibe = UIComponent.getLabel("Fly Your Vibe", 30);
		vibe.setFont(Font.font("arial", FontWeight.BOLD, 45));
		this.getChildren().add(vibe);
		AboutUsPage.setTopLeftAnchor(vibe, UIComponent.USER_MAX_SCREEN_WIDTH / 2 - 95, 500);
		
		String slog = "More than a ticket — it's the beginning of your next story.";
		Label slogan = UIComponent.getLabel(slog, 20);
		this.getChildren().add(slogan);
		AboutUsPage.setTopLeftAnchor(slogan, UIComponent.USER_MAX_SCREEN_WIDTH / 2 - 210, 560);
		
		ImageView airplane = UIComponent.getImageView("img/airplane.png", 100, true);
		ImageView money = UIComponent.getImageView("img/money.png", 100, true);
		ImageView cat = UIComponent.getImageView("img/cat.png", 100, true);
		this.getChildren().addAll(airplane, money, cat);
		AboutUsPage.setTopLeftAnchor(airplane, 40, 620);
		AboutUsPage.setTopLeftAnchor(money, 320, 620);
		AboutUsPage.setTopLeftAnchor(cat, 520, 620);
		
		Label quicker = UIComponent.getLabel("Quicker", 20);
		Label cheaper = UIComponent.getLabel("Cheaper", 20);
		Label cooler = UIComponent.getLabel("Cooler", 20);
		this.getChildren().addAll(quicker, cheaper, cooler);
		quicker.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		cheaper.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		cooler.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		AboutUsPage.setTopLeftAnchor(quicker, 120, 730);
		AboutUsPage.setTopLeftAnchor(cheaper, 350, 730);
		AboutUsPage.setTopLeftAnchor(cooler, 550, 730);
		
		String desc = "We built Airror for the new generation of travelers — bold, curious, and always ready for the next adventure.\nAirror isn’t just a flight booking app. It’s your travel wingman.\r\n"
				+ "Whether you're hunting last-minute deals, planning a dream trip with friends, or just escaping the ordinary,\nAirror gives you the power to explore the world without the headache.\r\n"
				+ "We combine slick design with smart tech to make booking flights fast, fun, and ridiculously easy.\nNo hidden fees, no outdated vibes — just clean UX, real-time prices, and smooth checkouts";
		Label description = UIComponent.getLabel(desc, 15);
		this.getChildren().add(description);
		AboutUsPage.setTopLeftAnchor(description, 760, 620);

	}
	
	public static AboutUsPage getInstance() {
		if(aboutUsPageInstance == null) {
			aboutUsPageInstance = new AboutUsPage();
		}
		
		return aboutUsPageInstance;
	}

}
