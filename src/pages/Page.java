package pages;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import utils.UIComponent;

public abstract class Page extends AnchorPane {
	public Canvas canvas = null;
	
	public abstract void setHeader(GraphicsContext gc);

//	public abstract void setMiddle(GraphicsContext gc);

	public abstract void setStyle();

	public static void setTopLeftAnchor(Node node, double left, double top) {
		AnchorPane.setTopAnchor(node, top);
		AnchorPane.setLeftAnchor(node, left);
	}

	public Canvas getCanvas() {
		if(canvas == null) {
			canvas = new Canvas(UIComponent.USER_MAX_SCREEN_WIDTH, UIComponent.USER_MAX_SCREEN_HEIGHT);
		}
		return canvas;
	}
}
