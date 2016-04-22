package guiPlots;

import javafx.event.EventHandler;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import user.WindowProps;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import data.Station;

/* Collection of all the open plot windows
 * to be saved in the User file later */
public class PlotWindows extends Vector<PlotWindow>
{
    public void showAll()
    {
    	for (PlotWindow window : this)
    	{
    		window.show();
    	}
    }
    
	public boolean haveFor(Station station, PlotType type) 
	{
		for(PlotWindow window : this)
		{
			boolean rightType = false;
			
			switch (type)
			{
			case Historical:
				if(window.getPlot() instanceof PlotHistoricalTemp)
					rightType = true;
				break;
			case Last72Hr:
				if(window.getPlot() instanceof PlotLast72hrTemp)
					rightType = true;
				break;
			case Table72Hr:
				if(window.getPlot() instanceof Table72Hr)
					rightType = true;
			case TableHistorical:
				if(window.getPlot() instanceof TableHistorical)
					rightType = true;
			default:
				break;
			}
			if(rightType && window.getPlot().getStation() == station)
			{
				return true;
			}
		}
		return false;
	}

	public PlotWindow windowFor(Station station, PlotType type) 
	{
		for(PlotWindow window : this)
		{
			boolean rightType = false;
			
			switch (type)
			{
			case Historical:
				if(window.getPlot() instanceof PlotHistoricalTemp)
					rightType = true;
				break;
			case Last72Hr:
				if(window.getPlot() instanceof PlotLast72hrTemp)
					rightType = true;
				break;
			case Table72Hr:
				if(window.getPlot() instanceof Table72Hr)
					rightType = true;
			case TableHistorical:
				if(window.getPlot() instanceof TableHistorical)
					rightType = true;
			default:
				break;
			}
			if(rightType && window.getPlot().getStation() == station)
			{
				return window;
			}
		}
		return null;
	}

	public void setOnCloseRequest(EventHandler<WindowEvent> handler) 
	{
		for(Window window : this)
		{
			window.setOnCloseRequest(handler);
		}
	}
}
