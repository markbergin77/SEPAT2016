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
	implements PaneAllStations.EventInterface
	, PaneFavourites.EventInterface
{	
	public interface EventInterface 
	{
		void onStationClicked(Station station);
		void onFavClicked(Favourite fav);
	}
	
	TabPane tabPane;
	Tab allStationsTab;
	Tab favouritesTab;
	PaneAllStations allStationsPane;
	PaneFavourites favouritesPane;
	
	EventInterface eventHandler;
	
	public Explorer(EventInterface callbackObj)
	{
		super();
		this.eventHandler = callbackObj;
		createGuiElements();
	}
	
	void createGuiElements()
	{
		allStationsPane = new PaneAllStations(this);
		favouritesPane = new PaneFavourites(this);
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
	
	public void addStationsFav(FavouritesList stations)
	{
		favouritesPane.createFavButtons(stations);
	}

	public void addStationsAll(StationList stations) 
	{
		allStationsPane.createStationButtons(stations);
	}

	@Override
	public void onFavClicked(Favourite fav)
	{
		eventHandler.onFavClicked(fav);		
	}

	@Override
	public void onStationClicked(Station station)
	{
		eventHandler.onStationClicked(station);
	}
}