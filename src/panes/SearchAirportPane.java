package panes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pages.MainPage;
import utils.IOReaderWriter;
import interfaces.Searchable;
import java.util.ArrayList;

public class SearchAirportPane extends VBox implements Searchable {
	public static SearchAirportPane searchPageInstance = null;
	public static ArrayList<String> airports = IOReaderWriter
			.getStringsFromTextFile("res/text/Asian_Airports_Abbreviated.txt");

	public SearchAirportPane(String condition) {
//		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		String[] splitted = condition.strip().split(" ");
		addSearchEachPane(splitted);
	}

	@Override
	public void addSearchEachPane(Object obj) {
		String[] condition = (String[]) obj;
		int i = 0;
		for (String airport : airports) {
			if (condition.length == 1 || airport.toLowerCase().contains(condition[0].toLowerCase())) {
				// not yet add EventListener!!!!!
				SearchAirportEachPane searchEachPane = new SearchAirportEachPane(airport, 280);
				searchEachPane.setOnMouseClicked((event) -> {
					if(condition[condition.length - 1].equals("1")) {
						MainPage.departField.setText(airport);
						MainPage.getInstance().getChildren().remove(MainPage.departSearchBar);
						MainPage.departSearchBar = null;
					}else {
						MainPage.destinyField.setText(airport);
						MainPage.getInstance().getChildren().remove(MainPage.destinySearchBar);
						MainPage.destinySearchBar = null;
					}
				});
				this.getChildren().add(searchEachPane);
				searchEachPane.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
						CornerRadii.EMPTY, new BorderWidths(1))));
				if (i % 2 == 0) {
					searchEachPane.setBackground(
							new Background(new BackgroundFill(Color.web("#d4d1cb"), CornerRadii.EMPTY, Insets.EMPTY)));
				} else {
					searchEachPane.setBackground(
							new Background(new BackgroundFill(Color.web("#e8e7e6"), CornerRadii.EMPTY, Insets.EMPTY)));
				}
				++i;
			}
		}
	}
}
