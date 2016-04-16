package bomWeatherGuiTest;

import bomData.Bom;
import bomData.Station;
import bomWeatherGui.HomeScreen;
import bomWeatherGui.StationButton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StationButtonTest extends Application
{
	public void start(Stage window) throws Exception 
	{
		
	    window.setTitle("Login");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        Station station = new Station("Station Name", "", "", "");
		StationButton button = new StationButton(station);
		window.setScene(new Scene(button));
    	window.sizeToScene();
    	window.centerOnScreen();
    	window.show();
	}
	
	public static void main(String args[])
    {
        launch(args);
    }
	
}
