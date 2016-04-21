package guiPlots;

import javafx.scene.Scene;
import javafx.stage.Stage;

/* Convenience, takes a plot as constructor
 * argument and takes care of details
 * also state is savable to User file via
 * PlotWindowsOpen collection class */
public class PlotWindow extends Stage
{
	Scene scene;
	public PlotWindow(PlotBase plot)
	{
		scene = new Scene(plot);
		scene.getStylesheets().add(plot.getCssPath());
		setScene(scene);
		show();
	}
}
