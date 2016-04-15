package bomWeatherGui;

import java.awt.Dimension;
import java.awt.Point;

import bomData.StationList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

// Currently has two tabs, all stations and favourite stations
public class Explorer extends StackPane
{	
	StationListPane allStations;
	public Explorer()
	{
		allStations = new StationListPane();
	}

	public void addStationsAll(StationList stations, EventHandler<MouseEvent> clickHandler) 
	{
		allStations.createStationButtons(stations, clickHandler);
	}
}