package guiLookTest;

import java.io.IOException;

import data.Bom;
import data.StationList;
import gui.SplashScreen;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreenTest extends Application
{
    //Testing the splash screen (what users view when application starts) functionality
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
		SplashScreen splash = new SplashScreen();
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
        Task<StationList> getStationsTask = new Task<StationList>(){
        		@Override protected StationList call() throws IOException
        		{
        			StationList locs = Bom.getAllStations(splash);
        			return locs;
        		}
        };
        splash.setOnClosed(e -> System.exit(0));
        getStationsTask.setOnSucceeded(e -> splash.startClosing());
        new Thread(getStationsTask).start();
	}
}
