package panes;

import java.util.ArrayList;

import interfaces.Searchable;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logics.PurchaseData;
import pages.PurchasePage;
import utils.IOReaderWriter;
import utils.UIComponent;

public class SearchPurchasePane extends VBox implements Searchable {
	
	public SearchPurchasePane() {
		this.setPrefWidth(UIComponent.USER_MAX_SCREEN_WIDTH);
		
		addSearchEachPane(null);
		if(this.getChildren().size() == 0) {
			this.setAlignment(Pos.CENTER);
			Label label = UIComponent.getLabel("SORRY, THERE AREN'T ANY PURCHASES", 40);
			label.setPadding(new Insets(50, 0, 0, 0));
			label.setFont(Font.font("verdana", FontWeight.BOLD, 40));
			this.getChildren().add(label);					
		}
	}
	
	@Override
	public void addSearchEachPane(Object obj) {
		for(int i = 1; i <= PurchasePage.getPurchasesList().size(); ++i) {
			this.getChildren().add(new SearchPurchaseEachPane(PurchasePage.getPurchasesList().get(i - 1), i, this));
		}
	}
	
}
