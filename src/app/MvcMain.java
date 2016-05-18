package app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gui.Alert;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import utilities.MultipleInstanceLock;

public class MvcMain extends Application
{
	static String appName = "SEPAT2016";
	static MultipleInstanceLock preventMultInstances = 
			new MultipleInstanceLock(appName);
	
	Model model;
	View view;
	Controller controller;
	
	@Override
	public void start(Stage arg0) throws Exception 
	{
		if(preventMultInstances.isAppActive())
		{
			Alert close = new Alert("Stop", "An instance of this program is already open. "
					+ "\nPlease close the other instance or switch to it.",
					e -> System.exit(0));
		}
		else
		{
			model = new Model();
			view = new View(model);
			controller = new Controller(model, view);
		}
	}
	
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
