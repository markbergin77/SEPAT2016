package bomWeatherApp;

import bomData.Bom;
import bomData.StationList;
import bomWeatherGui.SplashScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
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
        
        StationList locs = Bom.getAllStations(splash);
        
	}
}
