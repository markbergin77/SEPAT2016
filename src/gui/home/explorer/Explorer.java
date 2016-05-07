package gui.home.explorer;

import data.Station;
import data.StationList;
import gui.callbacks.FavClicked;
import gui.callbacks.StationClicked;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import user.Favourite;
import user.FavouritesList;

/* Interface through which user selects weather stations.
 */
public class Explorer extends StackPane
{	
	public interface EventInterface 
	{

	}
	TabPane tabPane;
	Tab allStationsTab;
	Tab favouritesTab;
	PaneAllStations allStationsPane;
	PaneFavourites favouritesPane;
	
	EventInterface callbackObj;
	
	public Explorer(EventInterface callbackObj)
	{
		super();
		this.callbackObj = callbackObj;
		createGuiElements();
	}
	
	void createGuiElements()
	{
		allStationsPane = new PaneAllStations();
		favouritesPane = new PaneFavourites();
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
	
	public void onAddFavourite(Favourite fav)
	{
		favouritesPane.addFavButton(fav);
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