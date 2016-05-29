package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import gui.plots.PlotBase;
import gui.plots.TableHistorical;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.EasyTask;
import utilities.JavaFXSafeTask;
import utilities.TestData;

public class TableHistoricalTest extends PlotTestBase
{
	public static void main(String args[])
    {
        launch(args);
    }

	@Override
	public PlotBase setPlot()
	{
		return new TableHistorical(allStations.get(0));
	}
}
