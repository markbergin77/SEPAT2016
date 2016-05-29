package guiTest.plots;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gui.plots.PlotBase;
import gui.plots.PlotWindow;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public abstract class PlotTestBase extends Application
	implements PlotBase.EventInterface
{
	@Override
	public abstract void start(Stage arg0) throws Exception;	

	@Override
	public abstract void onRefresh(PlotBase plot);

	
	static ExecutorService exec = 
    		Executors.newSingleThreadExecutor(
		    r -> 
		    {
		        Thread t = new Thread(r);
		        // allow app to exit if tasks are running
		        t.setDaemon(true); 
		        return t ;
		    });
	
	private void queueTask(Task<?> task)
	{
		exec.submit(task);
	}
}
