package gui.home.explorer;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import data.Station;
import data.StationList;
import gui.callbacks.StationClicked;

/* List of all stations in BOM database */
public class PaneAllStations extends PaneBase
{
    
    /* why the variable? Because otherwise,
     * if we change the handler, we have to 
       loop through all the buttons and change
       them individually. */
    StationClicked clickHandler;
    
    StationList stations;
    
    public PaneAllStations(StationList stations)
    {
    	super();
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
            getVBox().getChildren().add(node);
            node.toFront();
    	}
    }
    
    public void createStationButtons(StationList bomStations, 
    		StationClicked clickHandler)
    {
    	this.clickHandler = clickHandler;
    	for (Station bomStation : bomStations)
    	{
    		ButtonNotFav node = new ButtonNotFav(bomStation);
    		node.setOnMouseClicked(e -> onStationClicked(e));
    		addButton(node);
    	}
    }
    
    void onStationClicked(MouseEvent e)
    {
    	ButtonNotFav button = (ButtonNotFav)e.getSource();
    	/* Do anything to the button's appearance you want */
    	clickHandler.onStationClicked(button.getStation());
    }
    
    public void setOnButtonClicked(StationClicked clickHandler)
    {
    	this.clickHandler = clickHandler;
    }

    

    public VBox updateList(){


        return null;
    }

    // need to think of a way to visually update the GUI when we search for a station. maybe have a listUpdate() method
    // and return that list for the gui to fade in
    // if you do think of a way to do this, implement all the back end for it and tell me i will find a way to represent it


}
