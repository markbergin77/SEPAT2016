package guiTest.plots;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.Bom;
import data.Fio;
import gui.plots.PlotBase;
import gui.plots.PlotWindow;
import guiTest.GuiTestBase;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public abstract class PlotTestBase extends GuiTestBase
	implements PlotBase.EventInterface
{
	@Override
	public abstract void onRefresh(PlotBase plot);
}
