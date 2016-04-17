package gui;

import data.StationList;
import guiCallbacks.FavClicked;
import guiCallbacks.StationClicked;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import user.FavouritesList;

/* Interface through which user selects weather stations.
 */
public class Explorer extends StackPane
{	
	TabPane tabPane;
	Tab allStationsTab;
	Tab favouritesTab;
	StationsPaneAll allStationsPane;
	StationsPaneFav favouritesPane;
	
	
	
	public Explorer()
	{
		super();
		createGuiElements();
	}
	
	void createGuiElements()
	{
		allStationsPane = new StationsPaneAll();
		favouritesPane = new StationsPaneFav();
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
	
	public void addStationsFav(FavouritesList stations, FavClicked listener)
	{
		favouritesPane.createFavButtons(stations, listener);
	}

	/* Add stations to the "all stations" tab. */
	public void addStationsAll(StationList stations, StationClicked listener) 
	{
		allStationsPane.createStationButtons(stations, listener);
	}
	/* For testing purposes, no click handler. */
	public void addStationsAll(StationList stations) 
	{
		allStationsPane.createStationButtons(stations);
	}
}