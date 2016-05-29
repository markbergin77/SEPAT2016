package guiTest.plots;

import gui.plots.PlotBase;
import gui.plots.PlotWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public abstract class PlotTestBase extends Application
	implements PlotBase.EventInterface
{
	PlotWindow window;
	@Override
	public void start(Stage arg0) throws Exception
	{
		window.show();
	}
	
	

	@Override
	public abstract void onRefresh(PlotBase plot);

	@Override
	public abstract void onGoHome();

	PlotBase plot;
}
