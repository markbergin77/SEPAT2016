package bomWeatherApp;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import bomData.Bom;
import bomData.StationList;
import bomWeatherGui.SplashScene;
import bomWeatherGui.TaskSafeFinish;
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

    // Use the following if you want the tasks to run concurrently, instead of consecutively:

    // private ExecutorService exec = Executors.newCachedThreadPool(r -> {
    //     Thread t = new Thread(r);
    //     t.setDaemon(true);
    //     return t ;
    // });
    
	StationList allStations;
	
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
		SplashScene splash = new SplashScene();
		window.setScene(splash.getScene());
	    window.setTitle("Login");
		window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        
        window.setOnCloseRequest(e -> System.exit(0));

        // Might not be showing immediately 
        // after calling .show()
        window.setOnShowing(e -> 
        {
        	splash.startShowing();
        });
        
        window.show();
        
        TaskSafeFinish getStationsTask = new TaskSafeFinish(() ->
        { 
        	try 
        	{
					allStations = Bom.getAllStations(splash);
			} 
        	catch (Exception e1) 
        	{
				/* TODO User might not be able to connect to BOM!
				 * Must put something on the splash screen, 
				 * maybe retry connecting in a loop */
				e1.printStackTrace();
			}
        }, 
        () -> // (onFinished)
        {
        	splash.startClosing();
        });
        
        splash.setOnClosed(e -> 
        {
        	;
        });
 
        queue(getStationsTask);
	}
	
	private void queue(Task<?> task)
	{
		exec.submit(task);
	}
}
