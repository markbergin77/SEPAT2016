package gui.home.explorer;

import data.Station;
import data.StationList;
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
	public Explorer(EventInterface callbackObj)
	{
		super();
		this.eventHandler = callbackObj;
		createGuiElements();
	}
	
	public void addStationsFav(FavouritesList stations)
	{
		favouritesPane.addFavButtons(stations);
	}
	
	public void addStationsAll(StationList stations) 
	{
		allStationsPane.createStationButtons(stations);
	}
	
	public void addFavourite(Favourite fav)
	{
		favouritesPane.addFavButton(fav);
	}
	
	public void removeFavourite(Favourite fav)
	{
		favouritesPane.removeFav(fav);
	}
	
	TabPane tabPane;
	Tab allStationsTab;
	Tab favouritesTab;
	PaneAllStations allStationsPane;
	PaneFavourites favouritesPane;
	
	EventInterface eventHandler;

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

        tabPane.setTabMaxWidth(125);
        tabPane.setTabMinWidth(125);
        favouritesPane.setMaxWidth(300);
        favouritesPane.setMinWidth(300);
        allStationsPane.setMaxWidth(300);
        allStationsPane.setMinWidth(300);
        allStationsPane.scrollPane.setMinWidth(300);
        allStationsPane.scrollPane.setMaxWidth(300);
        favouritesPane.scrollPane.setMinWidth(300);
        favouritesPane.scrollPane.setMaxWidth(300);
	}
	
	public interface EventInterface 
	{
		abstract void onStationSel(Station station);
		abstract void onFavSel(Favourite fav);
		abstract void onFavRemove(Favourite fav);
	}
	
	@Override
	public void onFavClicked(Favourite fav)
	{
		eventHandler.onFavSel(fav);		
	}

	@Override
	public void onStationClicked(Station station)
	{
		eventHandler.onStationSel(station);
	}

	@Override
	public void onFavRemove(Favourite fav)
	{
		eventHandler.onFavRemove(fav);
	}
}