package guiTest.plots;

import gui.plots.ExperimentalPlot;
import gui.plots.PlotBase;

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
