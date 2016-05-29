package guiTest.plots;

import gui.plots.Last72hrTemp;
import gui.plots.PlotBase;

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
