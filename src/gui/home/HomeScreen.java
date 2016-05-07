package gui.home;

import data.Station;
import gui.home.explorer.Explorer;
import gui.home.explorer.Explorer.EventInterface;
import gui.home.options.OptionsArea;
import javafx.scene.layout.*;
import user.Favourite;
import user.User;

/* The root of all the User's activities */
public class HomeScreen extends GridPane
	implements OptionsArea.EventInterface, Explorer.EventInterface
{
	public interface EventInterface 
	{
		abstract void onOpen72TempPlot(Station station);
		abstract void onOpenHisTempPlot(Station station);
		abstract void onAddFav(Station station);
		abstract void onOpenHisTable (Station station);
		abstract void onOpen72HrTable (Station station);
		abstract void onCloseAllPlots(Station station);
	}
    Explorer explorer;
    OptionsArea optionsArea;
    User user;
    
    public static double defaultHeight = 400;
    
    EventInterface callbackObj;
    
    /* for testing purposes, no interaction */
    public HomeScreen()
    {
    	super();
    	explorer = new Explorer(this);
    	optionsArea = new OptionsArea(this);
        add(explorer, 0, 0);
        add(optionsArea, 1, 0);
    }
    
    public HomeScreen(HomeScreenInit init, EventInterface callbackObj)
    {
    	super();
    	user = init.user;
    	explorer = new Explorer(this);
    	optionsArea = new OptionsArea(this);
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
    	else
    	{
    		optionsArea.selectTabFor(fav.getStation());
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
    	else
    	{
    		optionsArea.selectTabFor(station);
    	}
    }
    
    public Explorer getExplorer()
    {
    	return explorer;
    }
    
    public void startShowing()
    {   	
    	
    }

	@Override
	public void onOpen72TempPlot(Station station)
	{
		callbackObj.onOpen72TempPlot(station);
	}

	@Override
	public void onOpenHisTempPlot(Station station)
	{
		callbackObj.onOpenHisTempPlot(station);
	}

	@Override
	public void onAddFav(Station station)
	{
		callbackObj.onAddFav(station);
	}

	@Override
	public void onOpenHisTable(Station station)
	{
		callbackObj.onOpenHisTable(station);
	}

	@Override
	public void onOpen72HrTable(Station station)
	{
		callbackObj.onOpen72HrTable(station);
	}

	@Override
	public void onCloseAllPlots(Station station)
	{
		callbackObj.onCloseAllPlots(station);
	}
}