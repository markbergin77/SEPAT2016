package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import data.samples.WthrSamplesFine;
import gui.plots.Last72hrTemp;
import gui.plots.PlotBase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Last72hrTempTest extends PlotTestBase
{	
	public static void main(String args[])
    {
        launch(args);
    }

	@Override
	public PlotBase setPlot()
	{
		return new Last72hrTemp(allStations.get(0));
	}
}
