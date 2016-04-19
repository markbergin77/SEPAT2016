package gui;


import data.Station;
import javafx.geometry.Pos;
import javafx.scene.control.Button;


public class ExplorerButtonStation extends Button 
{
	static double buttonWidth = 150;
  //  private String LOCATION;
  //  private int TEMP;
  //  private Boolean RAINING, SUNNY;
    private Station station;

    public ExplorerButtonStation(Station station)
    {
    	super(station.getName());
    	this.station = station;
    	setMinWidth(buttonWidth);
    	setMaxWidth(buttonWidth);
    	// Text/image alignment
    	setAlignment(Pos.CENTER_LEFT);
    }
    
	public Station getStation() 
	{
		return station;
	}
}
