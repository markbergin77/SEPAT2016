package guiTest.plots;

import gui.plots.PlotBase;
import gui.plots.Table72Hr;

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
