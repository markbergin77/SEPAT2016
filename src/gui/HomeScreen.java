package gui;

import data.Station;
import javafx.scene.layout.*;
import user.Favourite;


public class HomeScreen extends GridPane
{
    Explorer explorer;
    OptionsArea optionsArea;
    
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
    
    public HomeScreen(HomeInitInfo init, GuiEventInterface callbackObj)
    {
    	super();
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
    	optionsArea.setStationFav(fav);
    }
    
    void onStationClicked(Station station)
    {
    	optionsArea.setStation(station);
    }
    
    public Explorer getExplorer()
    {
    	return explorer;
    }
    
    public void startShowing()
    {   	
    	
    }
}