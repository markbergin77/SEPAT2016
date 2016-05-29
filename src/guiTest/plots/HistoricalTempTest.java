package guiTest.plots;

import gui.plots.HistoricalTemp;
import gui.plots.PlotBase;

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
