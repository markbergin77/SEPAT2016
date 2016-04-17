package guiLookTest;

import java.io.IOException;

import data.Bom;
import data.Station;
import data.StationList;
import gui.Explorer;
import gui.GuiAppUtils;
import gui.HomeScreen;
import gui.SplashScreen;
import gui.StationsPaneAll;
import guiCallbacks.StationClicked;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
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
        window.setOnCloseRequest(e -> System.exit(0));
        try {
				allStations = Bom.getAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        Explorer explorer = new Explorer();
		explorer.addStationsAll(allStations);
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
}
