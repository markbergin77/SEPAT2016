package bomWeatherGuiTest;

import bomData.StationList;
import bomWeatherGui.HomeScreen;
import bomWeatherGui.LoadingSplashScreen;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashScreen extends Application
{

	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
		LoadingSplashScreen splash = new LoadingSplashScreen();
		window.setScene(splash.getScene());
	    window.setTitle("Login");
		window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        
        window.setOnCloseRequest(e -> System.exit(0));

        // Might not be showing immediately 
        // after calling .show()
        window.setOnShowing(e -> 
        {
        	splash.fadeIn();
        });
        
        window.show();
        
        //StationList locs = StationList.getAllFromServer(splash);
        
	}

}
