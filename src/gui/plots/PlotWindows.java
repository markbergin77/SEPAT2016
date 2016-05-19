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
    
	public boolean haveFor(Station station, Class type) 
	{
		for(PlotWindow window : this)
		{
			if(window.getPlot().getClass().equals(type) 
					&& window.getPlot().getStation().equals(station))
			{
				return true;
			}
		}
		return false;
	}

	public PlotWindow windowFor(Station station, Class type) 
	{
		for(PlotWindow window : this)
		{
			if(window.getPlot().getClass().equals(type) 
					&& window.getPlot().getStation().equals(station))
			{
				return window;
			}
		}
		return null;
	}

	public void setOnCloseRequest(EventHandler<WindowEvent> handler) 
	{
		for(PlotWindow window : this)
		{
			window.setOnCloseRequest(handler);
		}
	}

	public void closeAll()
	{
		for (PlotWindow win : this)
		{
			win.close();
		}
	}
}
