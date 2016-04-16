package gui;

import java.awt.Dimension;
import java.awt.Point;

import data.Station;
import data.StationList;
import guiCallbacks.OnStationClicked;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/* Interface through which user selects weather stations.
 */
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

	/* Add stations to the "all stations" tab. */
	public void addStationsAll(StationList stations, OnStationClicked listener) 
	{
		allStationsPane.createStationButtons(stations, listener);
	}
	/* For testing purposes, no click handler. */
	public void addStationsAll(StationList stations) 
	{
		allStationsPane.createStationButtons(stations);
	}
}