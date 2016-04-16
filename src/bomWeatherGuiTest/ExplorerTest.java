package bomWeatherGuiTest;

import java.io.IOException;

import bomData.Bom;
import bomData.Station;
import bomData.StationList;
import bomWeatherGui.Explorer;
import bomWeatherGui.GuiAppUtils;
import bomWeatherGui.HomeScreen;
import bomWeatherGui.SplashScreen;
import bomWeatherGui.StationButtonListener;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ExplorerTest extends Application
	implements StationButtonListener
{
	StationList allStations;	
	@Override
	public void start(Stage window) throws Exception 
	{
		
	    window.setTitle("Login");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        try {
				allStations = Bom.getAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        Explorer explorer = new Explorer();
		explorer.addStationsAll(allStations, this);
		Scene scene = new Scene(explorer);
		window.setScene(scene);
    	window.sizeToScene();
    	window.centerOnScreen();
    	window.show();
	}
	
	public static void main(String args[])
    {
        launch(args);
    }

	@Override
	public void onStationClicked(Station station) 
	{
				
	}
}
