package guiLookTest;

import data.Station;
import gui.ExplorerButtonStation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StationButtonTest extends Application
{
	public void start(Stage window) throws Exception 
	{
		//The roughest design we had, purely showing button that is preceeded by station list
	    window.setTitle("Login");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        Station station = new Station("Station Name", "", "", "");
		ExplorerButtonStation button = new ExplorerButtonStation(station);
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
