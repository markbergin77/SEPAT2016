package gui;

import data.Station;
import javafx.scene.layout.*;
import user.Favourite;
import user.User;


public class HomeScreen extends GridPane
{
    Explorer explorer;
    OptionsArea optionsArea;
    User user;
    
    static double defaultHeight = 400;
    
    GuiEventInterface callbackObj;

    /* for testing purposes, no interaction */
    public HomeScreen()
    {
    	super();
    	explorer = new Explorer();
    	optionsArea = new OptionsArea(callbackObj);
        add(explorer, 0, 0);
        add(optionsArea, 1, 0);
    }
    
    public HomeScreen(HomeScreenInit init, GuiEventInterface callbackObj)
    {
    	super();
    	user = init.user;
    	explorer = new Explorer();
    	optionsArea = new OptionsArea(callbackObj);
        add(explorer, 0, 0);
        add(optionsArea, 1, 0);
        this.callbackObj = callbackObj;
        explorer.addStationsAll(init.getAllStations(),
        		StationButton -> onStationClicked(StationButton));
        explorer.addStationsFav(init.user.getFavs(), f -> onFavClicked(f));
    }
    
    void onFavClicked(Favourite fav)
    {
    	if(! optionsArea.hasTabFor(fav.getStation()))
    	{
    		optionsArea.addTab(fav);
    		optionsArea.selectLastAddedTab();
    	}
    }
    
    void onStationClicked(Station station)
    {
    	if(! optionsArea.hasTabFor(station))
    	{
    		/* Is this station on the Favourites list? */
    		Favourite possibleFav = user.favFor(station);
    		if(possibleFav == null)
    		{
    			optionsArea.addTab(station);
    		}
    		else
    		{
    			optionsArea.addTab(possibleFav);
    		}
    		optionsArea.selectLastAddedTab();
    	}
    }
    
    public Explorer getExplorer()
    {
    	return explorer;
    }
    
    public void startShowing()
    {   	
    	
    }
}