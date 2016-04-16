package bomWeatherGuiTest;

import bomData.Bom;
import bomData.Station;
import bomData.StationList;
import bomWeatherGui.StationButton;
import bomWeatherGui.StationButtonsPane;
import bomWeatherGui.StationButtonListener;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StationButtonsPaneTest extends Application
	implements StationButtonListener
{
	public void start(Stage window) throws Exception 
	{
		
	    window.setTitle("Login");
		window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        window.setOnCloseRequest(e -> System.exit(0));
        StationList stations = Bom.getAllStations();
		StationButtonsPane listPane = new StationButtonsPane();
		listPane.createStationButtons(stations, this);
		window.setScene(new Scene(listPane));
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
		// TODO Auto-generated method stub
		
	}
}
