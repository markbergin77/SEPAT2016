package app;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import data.Bom;
import data.Station;
import data.StationList;
import gui.GuiEventInterface;
import gui.HomeScreen;
import gui.SafeTask;
import gui.SplashScreen;
import gui.StationButton;
import gui.StationsPaneAll;
import guiCallbacks.StationClicked;
import guiDataPlots.TempPlot;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application 
	implements GuiEventInterface
{
	/* This service will finish one at a time
	 * on another thread so that we don't block
	 * the gui thread */
    ExecutorService exec = 
    		Executors.newSingleThreadExecutor(
		    r -> 
		    {
		        Thread t = new Thread(r);
		        // allow app to exit if tasks are running
		        t.setDaemon(true); 
		        return t ;
		    });
    
    Dimension homeWindowSize;
	StationList allStations;
	HomeScreen homeScreen;
	Scene scene;
	
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
	    window.setTitle("aids");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        
        SplashScreen splash = new SplashScreen();
        
        EasyTask getStationsTask = new EasyTask(() ->
        { 
        	try {
        		/* Pass in splash so that this function can update
        		 * the splash screen's text when something changes.*/
					allStations = Bom.getAllStations(splash);
			} catch (Exception e1) {
				/* TODO User might not be able to connect to BOM!
				 * Must put something on the splash screen,  
				 * Use splash.loadingUpdate(String)
				 * maybe retry connecting in a loop */
				e1.printStackTrace();
				splash.loadingUpdate("Error: couldn't connect. Please restart");
			}
        	// Tricky: loadingUpdate actually does a runLater()
        	splash.loadingUpdate("Creating GUI elements");
			homeScreen = new HomeScreen();
			scene = new Scene(homeScreen);
			homeScreen.getExplorer().addStationsAll(allStations, s -> {});
			splash.loadingUpdate("");
			splash.startClosing();
        });
        
        splash.setOnClosed(e -> 
        {
        	window.setScene(scene);
        	homeScreen.startShowing();
        	window.sizeToScene();
        	window.centerOnScreen();
        	window.show();
        });
        
        /* Now start the chain of tasks, 
         * getStationsTask was the first.*/
        queueTask(getStationsTask);
	}
	
	void openChartWindow()
	{
		
	}
	
	void startResizingWindowAnim(Stage window, Dimension toSize)
	{
		
	}
	
	private void queueTask(Task<?> task)
	{
		exec.submit(task);
	}

	@Override
	public void onOpenTempPlot(Station station) 
	{
		TempPlot tempPlot = new TempPlot(station);
		Stage newPlotWindow = new Stage();
		Scene plotScene = new Scene(tempPlot);
		newPlotWindow.setTitle("Temperature");
		newPlotWindow.setScene(plotScene);
	}
}