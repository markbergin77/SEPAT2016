package bomWeatherGuiTest;

import java.io.IOException;

import bomData.StationList;
import bomWeatherGui.HomeScreen;
import bomWeatherGui.SplashScreen;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashScreenTest extends Application
{

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
        	splash.begin();
        });
        
        window.show();
        Task<StationList> getStationsTask = new Task<StationList>(){
        		@Override protected StationList call() throws IOException
        		{
        			StationList locs = StationList.getAllFromServer(splash);
        			return locs;
        		}
        };
        splash.setOnFinished(e -> System.exit(0));
        getStationsTask.setOnSucceeded(e -> splash.finish());
        new Thread(getStationsTask).start();
	}
	
	public void transSplashToHome()
	{
		
	}

}
