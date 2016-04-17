package guiLookTest;

import data.Bom;
import data.StationList;
import gui.StationsPaneAll;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StationButtonsPaneTest extends Application
{
	public void start(Stage window) throws Exception 
	{
		//Testing the list of locations, back before there was a favourites tab
	    window.setTitle("Login");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        StationList stations = Bom.getAllStations();
		StationsPaneAll listPane = new StationsPaneAll();
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
