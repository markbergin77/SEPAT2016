package app;

import java.awt.Dimension;

import gui.home.HomeScreen;
import gui.plots.PlotWindows;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View 
{
	public interface EventInterface 
		extends HomeScreen.EventInterface
	{
		
	}

	Scene scene;
	Stage window;
    Dimension homeWindowSize;
	HomeScreen homeScreen;
	PlotWindows plotWindows = new PlotWindows();
	
	public View() 
	{
		
	}

	public void setEventHandler(EventInterface handler)
	{
		homeScreen.
	}
}
