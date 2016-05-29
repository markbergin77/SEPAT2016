package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import gui.plots.ExperimentalPlot;
import gui.plots.PlotBase;
import gui.plots.PlotWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExperimentalPlotTest extends PlotTestBase
{
	StationList allStations;
	public static void main(String args[])
    {
        launch(args);
    }
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//Grabbing stations
		try {
			allStations = bom.getAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// Pick a station to test
		Station station = null;
		if (allStations != null) {
			station = allStations.get(0);
		}
		else {
			return;
		}
		
		ExperimentalPlot plot = new ExperimentalPlot(station);
		
		PlotWindow newWindow = new PlotWindow(plot);
		newWindow.show();
		plot.fetchData(bom, fio);
		plot.plotData();
		
	}
	@Override
	public void onRefresh(PlotBase plot)
	{
		// TODO Auto-generated method stub
		
	}
}
