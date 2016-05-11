package gui.home.globalOptions;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class GlobalOptionsArea extends TabPane
{
	public interface EventInterface
	{
		public void onCloseAllPlots();
		public void onClearFavs();
		
	}
	VBox vbox = new VBox();
	String mainTabTitle = "Options";
	Tab mainTab = new Tab(mainTabTitle);
	static double defaultWidth = 120;

	String closeAllPlotsLabel = "Close All Charts";
	Button closeAllPlotsButton = new Button(closeAllPlotsLabel);
	
	String clearFavsLabel = "Clear Favourites";
	Button clearFavsButton = new Button(clearFavsLabel);
	
	EventInterface eventHandler;
	
	public GlobalOptionsArea(EventInterface eventHandler)
	{
		super();
		this.eventHandler = eventHandler;
		vbox.getChildren().addAll(closeAllPlotsButton, clearFavsButton);
		closeAllPlotsButton.setMinWidth(defaultWidth);
		clearFavsButton.setMinWidth(defaultWidth);
		closeAllPlotsButton.setOnMouseClicked(e -> 
		{
			eventHandler.onCloseAllPlots();
		});
		mainTab.setContent(vbox);
		mainTab.setClosable(false);
		getTabs().add(mainTab);
	}
}
