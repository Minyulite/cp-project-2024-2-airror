package panes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import utils.UIComponent;

public class SearchEachPane extends BorderPane {
	private String name;
	
	public SearchEachPane(String name, double prefWidth) {
		super();
		this.setName(name);
		this.setPrefWidth(prefWidth);
		Label label = UIComponent.getLabel(name, 16);
//		this.setCenter(label);
		this.setLeft(label);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
