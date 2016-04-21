package guiPlots;

import data.Station;
import javafx.scene.layout.GridPane;

public class PlotBase extends GridPane
{
	Station station; 
	
	public PlotBase(Station station)
	{
		super();
		this.station = station;
	}
	
	public String getCssPath()
	{
		return null;
	}
	
	public Station getStation()
	{
		return station;
	}
}
