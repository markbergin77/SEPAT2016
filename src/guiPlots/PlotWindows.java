package guiPlots;

import javafx.stage.Window;
import user.WindowLocation;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

/* Collection of all the open plot windows
 * to be saved in the User file later */
public class PlotWindows extends Vector<PlotWindow>
{
    public void showAll()
    {
    	for (PlotWindow window : this)
    	{
    		window.show();
    	}
    }
}
