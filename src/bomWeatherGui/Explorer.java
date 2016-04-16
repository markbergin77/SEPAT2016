package bomWeatherGui;

import java.awt.Dimension;
import java.awt.Point;

import bomData.Station;
import bomData.StationList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

// Currently has two tabs, all stations and favourite stations
public class Explorer extends StackPane
{	
	StationButtonsPane allStations;
	
	public Explorer()
	{
		allStations = new StationButtonsPane();
	}

	public void addStationsAll(StationList stations, StationButtonListener listener) 
	{
		allStations.createStationButtons(stations, listener);
	}


}