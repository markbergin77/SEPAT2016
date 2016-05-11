package gui.home.explorer;

import javafx.scene.layout.VBox;

import data.Station;
import data.StationList;

/* List of all stations in BOM database */
public class PaneAllStations extends PaneBase
{
    public interface EventInterface
    {
    	void onStationClicked(Station station);
    }
    /* why the variable? Because otherwise,
     * if we change the handler, we have to 
       loop through all the buttons and change
       them individually. */
    EventInterface eventHandler;
    
    StationList stations;
    
    public PaneAllStations(StationList stations)
    {
    	super();
    }
    
    public PaneAllStations(EventInterface eventHandler)
    {
    	this.eventHandler = eventHandler;
    }
    
    /* For testing */
    public PaneAllStations()
    {
    	super();
    }
    
    /* For testing purposes, no click handler */
    public void createStationButtons(StationList bomStations)
    {
    	for (Station bomStation : bomStations)
    	{
    		ButtonNotFav node = new ButtonNotFav(bomStation);
    		node.setOnMouseClicked(e -> {
    			onStationClicked(node.getStation());
    		});
            addButton(node);
            node.toFront();
    	}
    }
    
    void onStationClicked(Station station)
    {
    	eventHandler.onStationClicked(station);
    }    

    public VBox updateList(){


        return null;
    }

    // need to think of a way to visually update the GUI when we search for a station. maybe have a listUpdate() method
    // and return that list for the gui to fade in
    // if you do think of a way to do this, implement all the back end for it and tell me i will find a way to represent it


}
