package guiLookTest;

import data.Bom;
import data.StationList;
import gui.Explorer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
