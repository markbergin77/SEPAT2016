package bomWeatherGui;

import java.awt.Dimension;
import java.awt.Point;

import bomData.Station;
import bomData.StationList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

// Currently has two tabs, all stations and favourite stations
public class Explorer extends StackPane
{	
	TabPane tabPane;
	Tab allStationsTab;
	Tab favouritesTab;
	StationButtonsPane allStationsPane;
	StationButtonsPane favouritesPane;
	
	public Explorer()
	{
		super();
		allStationsPane = new StationButtonsPane();
		favouritesPane = new StationButtonsPane();
		tabPane = new TabPane();
		allStationsTab = new Tab();
		allStationsTab.setContent(allStationsPane);
		allStationsTab.setText("All Stations");
		allStationsTab.setClosable(false);
		favouritesTab = new Tab();
		favouritesTab.setContent(favouritesPane);
		favouritesTab.setText("Favourites");
		favouritesTab.setClosable(false);
		tabPane.getTabs().addAll(allStationsTab, favouritesTab);
		getChildren().addAll(tabPane);
	}

	public void addStationsAll(StationList stations, StationButtonListener listener) 
	{
		allStationsPane.createStationButtons(stations, listener);
	}


}