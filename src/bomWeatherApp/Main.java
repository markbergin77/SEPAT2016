package bomWeatherApp;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    // Use the following if you want the tasks to run concurrently, instead of consecutively:

    // private ExecutorService exec = Executors.newCachedThreadPool(r -> {
    //     Thread t = new Thread(r);
    //     t.setDaemon(true);
    //     return t ;
    // });
    
	StationList allStations;
	StationListPane allStationsPane;
	HomeScreen homeScreen;
	
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
	    window.setTitle("Login");
		window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        window.setOnCloseRequest(e -> System.exit(0));

        SplashScreen splash = new SplashScreen();
        window.setScene(splash.getScene());
        // Might not be showing immediately 
        // after calling .show()
        window.setOnShowing(e -> 
        {
        	splash.startShowing();
        });
        
        window.show();
        
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
			allStationsPane = new StationListPane(allStations);
			homeScreen = new HomeScreen(window);
			splash.loadingUpdate("");
			splash.startClosing();
        });
        
        splash.setOnClosed(e -> 
        {
        	homeScreen.display(window);
        });
 
        queue(getStationsTask);
	}
	
	private void queue(Task<?> task)
	{
		exec.submit(task);
	}
}
