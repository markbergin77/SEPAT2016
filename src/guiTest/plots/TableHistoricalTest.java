package guiTest.plots;

import gui.plots.PlotBase;
import gui.plots.TableHistorical;

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
