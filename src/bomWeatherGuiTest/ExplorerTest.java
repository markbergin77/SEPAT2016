package bomWeatherGuiTest;

import java.io.IOException;

import bomData.Bom;
import bomData.StationList;
import bomWeatherGui.Explorer;
import bomWeatherGui.HomeScreen;
import bomWeatherGui.SplashScreen;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ExplorerTest extends Application
{
	StationList allStations;	
	@Override
	public void start(Stage window) throws Exception 
	{
		
	    window.setTitle("Login");
		window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        
        window.setOnCloseRequest(e -> System.exit(0));
        try {
				allStations = Bom.getAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        HomeScreen homeScreen = new HomeScreen();
		homeScreen.addStationsAll(allStations);
		window.setScene(homeScreen.getScene());
    	homeScreen.startShowing();
    	window.sizeToScene();
    	window.centerOnScreen();
    	window.show();
	}
	
	public static void main(String args[])
    {
        launch(args);
    }
}
