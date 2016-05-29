package guiTest.home;

import data.Bom;
import data.StationList;
import gui.home.HomeScreen;
import guiTest.GuiTestBase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreenTest extends GuiTestBase
{
	//testing class for entire homescreen view
	StationList allStations;	
	@Override
	public void start(Stage window) throws Exception 
	{
		
	    window.setTitle("Login");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        try {
				allStations = bom.getAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        HomeScreen homeScreen = new HomeScreen();
        //homeScreen.getExplorer().addStationsAll(allStations);
        Scene scene = new Scene(homeScreen);
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
