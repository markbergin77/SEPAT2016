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
	public static void main(String args[])
    {
        launch(args);
    }

	@Override
	public PlotBase setPlot()
	{
		return new ExperimentalPlot(allStations.get(0));
	}
}
