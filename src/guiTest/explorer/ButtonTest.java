package guiTest.explorer;

import data.Station;
import gui.home.explorer.ButtonNotFav;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ButtonTest extends Application
{
	public void start(Stage window) throws Exception 
	{
		//The roughest design we had, purely showing button that is preceeded by station list
	    window.setTitle("Login");
		window.setResizable(false);
        window.setOnCloseRequest(e -> System.exit(0));
        Station station = new Station("Station Name", "", "", "");
		ButtonNotFav button = new ButtonNotFav(station);
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
