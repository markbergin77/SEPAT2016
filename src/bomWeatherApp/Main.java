package bomWeatherApp;
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

import bomData.Bom;
import bomData.StationList;
import bomWeatherGui.HomeScreen;
import bomWeatherGui.SplashScreen;
import bomWeatherGui.StationListPane;
import bomWeatherGui.SafeTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class Main extends Application
{
    private ExecutorService exec = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r);
        t.setDaemon(true); // allows app to exit if tasks are running
        return t ;
    });
    
    ScheduledExecutorService scheduler =
    	     Executors.newScheduledThreadPool(1, r -> {
    	         Thread t = new Thread(r);
    	         t.setDaemon(true); // allows app to exit if tasks are running
    	         return t ;
    	     });


    // Use the following if you want the tasks to run concurrently, instead of consecutively:

    // private ExecutorService exec = Executors.newCachedThreadPool(r -> {
    //     Thread t = new Thread(r);
    //     t.setDaemon(true);
    //     return t ;
    // });
    
    Dimension homeWindowSize;
	StationList allStations;
	HomeScreen homeScreen;
	
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
	    window.setTitle("aids");
		window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        window.setOnCloseRequest(e -> System.exit(0));
        SplashScreen splash = new SplashScreen();
        window.setScene(splash.getScene());
        /* don't start the fade-in and all that
         * straight away because the window isn't 
           visible till the gui thread gets to it */
        window.setOnShowing(e -> 
        {
        	splash.startShowing();
        });
        
        window.show();
        homeWindowSize = calcHomeWindowSize();
        EasyTask getStationsTask = new EasyTask(() ->
        { 
        	try {
					allStations = Bom.getAllStations(splash);
			} catch (Exception e1) {
				/* TODO User might not be able to connect to BOM!
				 * Must put something on the splash screen,  
				 * Use splash.loadingUpdate(String)
				 * maybe retry connecting in a loop */
				e1.printStackTrace();
			}
        	// Tricky: loadingUpdate actually does a runLater()
        	splash.loadingUpdate("Creating GUI elements");
			homeScreen = new HomeScreen(window, homeWindowSize);
			homeScreen.addStationsAll(allStations);
			splash.loadingUpdate("");
			splash.startClosing();
        });
        
        splash.setOnClosed(e -> 
        {
        	window.setScene(homeScreen.getScene());
        	homeScreen.startShowing(window);
        	window.centerOnScreen();
        });
        
        /* Now start the chain of tasks, 
         * getStationsTask was the first.*/
        queue(getStationsTask);
	}
	
	void openChartWindow()
	{
		
	}
	
	void startResizingWindowAnim(Stage window, Dimension toSize)
	{
		
	}
	//Provides flexibility on user resolution differences 
	Dimension calcHomeWindowSize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		Dimension output = new Dimension();
		if(screenWidth > 1919){
            output.setSize(1320, 740);
        }
        else if(screenWidth > 1439){
        	output.setSize(1260,680);
        }
        else if( screenWidth > 1279){
        	output.setSize(1100,550);
        }
        else if(screenWidth > 1023){
        	output.setSize(900,500);
        }
        else
        	return null;
		return output;
	}
	//Might archive this, meant to be in use???
	void queueDelayed(Task<?> task, long millis)
	{
		scheduler.schedule(task, millis, TimeUnit.MILLISECONDS);
	}
	
	private void queue(Task<?> task)
	{
		exec.submit(task);
	}
}
