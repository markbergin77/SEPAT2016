package app;

import data.Station;
import gui.plots.ExperimentalPlot;
import gui.plots.HistoricalTemp;
import gui.plots.Last72hrTemp;
import gui.plots.PlotBase;
import gui.plots.PlotType;
import gui.plots.PlotWindow;
import gui.plots.PlotWindows;
import gui.plots.Table72Hr;
import gui.plots.TableHistorical;
import user.Favourite;
import utilities.EasyTask;
import utilities.JavaFXSafeTask;

public class Controller
	implements View.EventInterface
{
	Model model;
	View view;
	
	public Controller(Model model, View view) 
	{
		this.model = model;
		this.view = view;
	}

	void openPlot(PlotBase plot)
	{
		PlotWindow newWindow = new PlotWindow(plot);
		newWindow.show();
		newWindow.setOnCloseRequest(e -> {
			plotWindows.remove(newWindow);
		});
		plotWindows.add(newWindow);
		plot.setEventHandler(this);
		fillPlot(plot);
	}
	
	void fillPlot(PlotBase plot)
	{
		EasyTask fetchDataTask = new EasyTask(() ->
		{
			plot.fetchData();
		});
		JavaFXSafeTask plotTask = new JavaFXSafeTask(() ->
		{
			plot.plotData();
		});
		queueTask(fetchDataTask);
		queueTask(plotTask);
	}
	
	void  fillPlots()
	{
		for (PlotWindow window : plotWindows)
		{
			PlotBase plot = window.getPlot();
			fillPlot(plot);
		}
	}
	
	void addPlotWindows(PlotWindows windows)
	{
		plotWindows.addAll(windows);
    	plotWindows.setOnCloseRequest(event -> 
    	{
    		plotWindows.remove(event.getSource());
    	});
    	for (PlotWindow win : windows)
    	{
    		win.getPlot().setEventHandler(this);
    	}
	}

	@Override
	public void onOpen72TempPlot(Station station) 
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, PlotType.Last72Hr);
		if(existingPlotWindow == null)
		{		
			openPlot(new Last72hrTemp(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}

	@Override
	public void onAddFav(Station station) 
	{
		/* Add to user's fav list */
		if(! user.getFavs().hasForStation(station))
		{
			Favourite newFav = Favourite.create(station);
			user.getFavs().add(newFav);
			homeScreen.addFavourite(newFav);
		}
	}

	@Override
	public void onOpenHisTempPlot(Station station) 
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, PlotType.Historical);
		if (existingPlotWindow == null)
		{
			openPlot(new HistoricalTemp(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}
	
	@Override
	public void onExperimentalPlot(Station station) 
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, PlotType.Historical);
		if (existingPlotWindow == null)
		{
			openPlot(new ExperimentalPlot(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}
	
	
	// test please --------------------------------
	
	@Override
	public void onOpen72HrTable (Station station)
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, PlotType.Table72Hr);
		if (existingPlotWindow == null)
		{
			openPlot(new Table72Hr(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}
	
	// test please -------------------------------
	
	@Override
	public void onOpenHisTable (Station station)
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, PlotType.TableHistorical);
		if (existingPlotWindow == null)
		{
			openPlot(new TableHistorical(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}

	@Override
	public void onCloseAllPlots(Station station) 
	{
		plotWindows.removePlotsFor(station);	
	}

	@Override
	public void onRefresh(PlotBase plot)
	{
		fillPlot(plot);
	}

	@Override
	public void onGoHome()
	{
		window.toFront();
	}

	@Override
	public void onCloseAllPlots()
	{
		plotWindows.closeAll();
		plotWindows.clear();
	}

	@Override
	public void onClearFavs()
	{
		
	}

	@Override
	public void onFavRemove(Favourite fav)
	{
		user.getFavs().remove(fav);
		homeScreen.removeFavourite(fav);
	}
}
