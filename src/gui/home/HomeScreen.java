package gui.home;

import data.Station;
import gui.home.explorer.Explorer;
import gui.home.globalOptions.GlobalOptionsArea;
import gui.home.options.OptionsArea;
import javafx.scene.layout.*;
import user.Favourite;
import user.User;

/* The root of all the User's activities */
public class HomeScreen extends GridPane
	implements OptionsArea.EventInterface, Explorer.EventInterface
	, GlobalOptionsArea.EventInterface
{    
	public HomeScreen(HomeScreenInit init, EventInterface eventHandler)
    {

    	super();
    	user = init.user;
    	explorer = new Explorer(this);
    	optionsArea = new OptionsArea(this);
    	globalOptionsArea = new GlobalOptionsArea(this);
    	getRowConstraints().add(new RowConstraints());
    	getRowConstraints().get(0).setVgrow(Priority.ALWAYS);

        add(explorer, 0, 0);
        add(optionsArea, 1, 0);
        add(globalOptionsArea, 2, 0);

        setHgrow(optionsArea,Priority.ALWAYS);

        this.eventHandler = eventHandler;
        explorer.addStationsAll(init.getAllStations());
        explorer.addStationsFav(init.user.getFavs());
    }
	
	
	
	public void addFavourite(Favourite newFav)
	{
		explorer.addFavourite(newFav);
	}
	
	public void removeFavourite(Favourite newFav)
	{
		explorer.removeFavourite(newFav);
	}
	
    /* for testing purposes, no interaction */
    public HomeScreen()
    {
    	super();
    	explorer = new Explorer(this);
    	optionsArea = new OptionsArea(this);
    	globalOptionsArea = new GlobalOptionsArea(this);
        add(explorer, 0, 0);
        add(optionsArea, 1, 0);
    }
    
    Explorer explorer;
    OptionsArea optionsArea;
    GlobalOptionsArea globalOptionsArea;
    User user;
    
    public static double defaultHeight = 400;
    
    EventInterface eventHandler;
    
    public interface EventInterface 
	{
		abstract void onOpen72TempPlot(Station station);
		abstract void onOpenHisTempPlot(Station station);
		abstract void onAddFav(Station station);
		abstract void onOpenHisTable (Station station);
		abstract void onOpen72HrTable (Station station);
		abstract void onCloseAllPlots(Station station);
		abstract void onCloseAllPlots();
		abstract void onClearFavs();
		abstract void onFavRemove(Favourite fav);
		abstract void onExperimentalPlot(Station station);
	}
    
    @Override
    public void onFavSel(Favourite fav)
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
    
    @Override
    public void onStationSel(Station station)
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

	@Override
	public void onOpen72TempPlot(Station station)
	{
		eventHandler.onOpen72TempPlot(station);
	}

	@Override
	public void onOpenHisTempPlot(Station station)
	{
		eventHandler.onOpenHisTempPlot(station);
	}

	@Override
	public void onAddFav(Station station)
	{
		eventHandler.onAddFav(station);
	}

	@Override
	public void onOpenHisTable(Station station)
	{
		eventHandler.onOpenHisTable(station);
	}

	@Override
	public void onOpen72HrTable(Station station)
	{
		eventHandler.onOpen72HrTable(station);
	}

	@Override
	public void onCloseAllPlots(Station station)
	{
		eventHandler.onCloseAllPlots(station);
	}

	@Override
	public void onCloseAllPlots()
	{
		eventHandler.onCloseAllPlots();
	}

	@Override
	public void onClearFavs()
	{
		eventHandler.onClearFavs();		
	}

	@Override
	public void onCloseAllTabs()
	{
		optionsArea.closeAllTabs();
	}

	@Override
	public void onFavRemove(Favourite fav)
	{
		eventHandler.onFavRemove(fav);
	}

	@Override
	public void onExperimentalPlot(Station station) {
		eventHandler.onExperimentalPlot(station);
	}

	public void widthChanged(double oldWidth, double newWidth){


    }

    public void heightChanged(double oldHeight, double newHeight){


    }


	}