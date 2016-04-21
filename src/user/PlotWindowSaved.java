package user;

import java.io.IOException;
import java.io.Serializable;

import data.Station;
import guiPlots.Plot72hrTemp;
import guiPlots.PlotBase;
import guiPlots.PlotHistoricalTemp;
import guiPlots.PlotWindow;

public class PlotWindowSaved implements Serializable
{
	WindowLocation location;
	Station station;
	PlotSaveType type;
	
	public PlotWindowSaved(PlotWindow window)
	{
		PlotBase plot = window.getPlot();
		this.location = new WindowLocation(window.getX(), window.getY());
		this.station = plot.getStation();
		if(plot instanceof Plot72hrTemp)
		{
			type = PlotSaveType.Last72Hr;
		}
		else if(plot instanceof PlotHistoricalTemp)
		{
			type = PlotSaveType.Historical;
		}
	}
	
	public PlotWindow restorePlotWindow()
	{
		PlotBase plot = null;
		switch (type)
		{
		case Historical:
			plot = new PlotHistoricalTemp(station);
			break;
		case Last72Hr:
			plot = new Plot72hrTemp(station);
			break;
		default:
			break;
		}
		PlotWindow window = new PlotWindow(plot);
		window.setX(location.xPos);
		window.setY(location.yPos);
		return window;
	}
}
