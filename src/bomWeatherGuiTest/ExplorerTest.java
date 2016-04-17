package bomWeatherGuiTest;

import java.io.IOException;

import bomData.Bom;
import bomData.StationList;
import bomWeatherGui.Explorer;
import bomWeatherGui.SplashScreen;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//Empty test for explorer, doesn't currently do anything.
//Would recommend removing
public class ExplorerTest extends Application
{
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
		
	    window.setTitle("Login");
		window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        
        window.setOnCloseRequest(e -> System.exit(0));

        
	}
}
