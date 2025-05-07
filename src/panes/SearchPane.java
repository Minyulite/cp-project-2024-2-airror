package panes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
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
import utils.IOReader;
import interfaces.Searchable;
import java.util.ArrayList;

public class SearchPane extends VBox implements Searchable {
	public static SearchPane searchPageInstance = null;
	public static ArrayList<String> airports = IOReader.getStringsFromTextFile("res/text/Asian_Airports_Abbreviated.txt");
	
	public SearchPane(String condition) {
//		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		addSearchEachPane(condition);
	}
	
	@Override
	public void addSearchEachPane(Object obj) {
		String condition = (String) obj;
		int i = 0;
		for(String airport : airports) {
			if(airport == "" || airport.toLowerCase().contains(condition.toLowerCase())) {
				//not yet add EventListener!!!!!
				SearchEachPane searchEachPage = new SearchEachPane(airport, 280);
				this.getChildren().add(searchEachPage);					
				searchEachPage.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
				if(i%2 == 0) {
					searchEachPage.setBackground(new Background(new BackgroundFill(Color.web("#d4d1cb"), CornerRadii.EMPTY, Insets.EMPTY)));
				}else {
					searchEachPage.setBackground(new Background(new BackgroundFill(Color.web("#e8e7e6"), CornerRadii.EMPTY, Insets.EMPTY)));
				}
				++i;
			}
		}
	}
}
