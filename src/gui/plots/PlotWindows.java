package gui.plots;

import javafx.event.EventHandler;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
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
    
    public void removePlotsFor(Station station)
    {
    	Vector<PlotWindow> windowsToRemove = new Vector<PlotWindow>();
    	for (PlotWindow window : this)
    	{
    		if (window.getPlot().getStation().equals(station))
    		{
    			window.close();
    			windowsToRemove.add(window);
    		}
    	}
    	/* If we don't do this separately we get exceptions,
    	 * because you can't modify a list while you're iterating
    	 * through it. */
    	for (PlotWindow window : windowsToRemove)
    	{
    		remove(window);
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
				if(window.getPlot() instanceof HistoricalTemp)
					rightType = true;
				break;
			case Last72Hr:
				if(window.getPlot() instanceof Last72hrTemp)
					rightType = true;
				break;
			case Table72Hr:
				if(window.getPlot() instanceof Table72Hr)
					rightType = true;
				break;
			case TableHistorical:
				if(window.getPlot() instanceof TableHistorical)
					rightType = true;
				break;
			default:
				break;
			}
			if(rightType && window.getPlot().getStation().equals(station))
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
				if(window.getPlot() instanceof HistoricalTemp)
					rightType = true;
				break;
			case Last72Hr:
				if(window.getPlot() instanceof Last72hrTemp)
					rightType = true;
				break;
			case Table72Hr:
				if(window.getPlot() instanceof Table72Hr)
					rightType = true;
				break;
			case TableHistorical:
				if(window.getPlot() instanceof TableHistorical)
					rightType = true;
				break;
			default:
				break;
			}
			if(rightType && window.getPlot().getStation().equals(station))
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
