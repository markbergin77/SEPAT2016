package guiTest.explorer;

import data.Bom;
import data.StationList;
import dataTest.SaveTestData;
import gui.home.explorer.Explorer;
import gui.home.explorer.Explorer.EventInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExplorerTest extends Application 
	implements Explorer.EventInterface
{
	//Explorer test, includes pan for location lists and favorites tab
	StationList allStations;	
	@Override
	public void start(Stage window) throws Exception 
	{
		
	    window.setTitle("Login");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        allStations = SaveTestData.loadAllStations();
        Explorer explorer = new Explorer(this);
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
