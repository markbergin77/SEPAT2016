package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import gui.plots.PlotBase;
import gui.plots.Table72Hr;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Table72HrTest extends PlotTestBase
{
	public static void main(String args[])
    {
        launch(args);
    }

	@Override
	public PlotBase setPlot()
	{
		return new Table72Hr(allStations.get(0));
	}

}
