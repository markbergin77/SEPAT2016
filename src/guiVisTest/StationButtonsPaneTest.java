package guiVisTest;

import data.Bom;
import data.Station;
import data.StationList;
import gui.StationButton;
import gui.StationButtonsPane;
import guiCallbacks.StationClicked;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StationButtonsPaneTest extends Application
{
	public void start(Stage window) throws Exception 
	{
		
	    window.setTitle("Login");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        StationList stations = Bom.getAllStations();
		StationButtonsPane listPane = new StationButtonsPane();
		listPane.createStationButtons(stations);
		window.setScene(new Scene(listPane));
    	window.sizeToScene();
    	window.centerOnScreen();
    	window.show();
	}
	
	public static void main(String args[])
    {
        launch(args);
    }

}
