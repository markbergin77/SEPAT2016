package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import gui.plots.HistoricalTemp;
import gui.plots.PlotBase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.TestData;

public class HistoricalTempTest extends PlotTestBase
{
	public static void main(String args[])
    {
        launch(args);
    }

	@Override
	public PlotBase setPlot()
	{
		return new HistoricalTemp(allStations.get(0));
	}

	
}
