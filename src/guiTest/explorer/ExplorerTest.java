package guiTest.explorer;

import data.Station;
import data.StationList;
import gui.home.explorer.Explorer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.Favourite;
import utilities.SaveTestData;

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

	@Override
	public void onStationClicked(Station station)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFavClicked(Favourite fav)
	{
		// TODO Auto-generated method stub
		
	}
}
