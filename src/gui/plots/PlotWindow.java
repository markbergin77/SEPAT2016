package gui.plots;

import java.net.URL;

import gui.Alert;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/* Convenience, takes a plot as constructor
 * argument and takes care of details
 * also state is savable to User file via
 * PlotWindowsOpen collection class */
public class PlotWindow extends Stage
	implements PlotBase.EventInterface
{
	Scene scene;
	PlotBase plot;
	static String homeButtonLabel = "Home";
	Button homeButton;

	public PlotWindow(PlotBase plot)
	{
		super();
		this.plot = plot;
		plot.setEventHandler(this);
		scene = new Scene(plot);
		homeButton = new Button(homeButtonLabel);
		homeButton.setOnMouseClicked(e -> 
		{
			eventHandler.onGoHome();
		});
		plot.addToolbarButton(homeButton);
		
		homeButton.setOnMouseEntered(e -> homeButton.getStyleClass().add("button-hover"));
        homeButton.setOnMouseExited(e -> homeButton.getStyleClass().remove("button-hover"));
		getCss(scene);
		setScene(scene);
		setTitle(plot.getName());
 		setMaxHeight(550);
		setMaxWidth(1000);
	}
	public PlotBase getPlot() 
	{
		return plot;
	}

	public interface EventInterface
	{
		public void onRefresh(PlotBase plot);
		public void onGoHome();
	}
	EventInterface eventHandler = voidHandler;
	
	private void getCss(Scene scene){

		try {
			URL url = this.getClass().getResource("plots.css");
			if (url == null) {
				Alert alert = new Alert("Error","Could not load resource : plots.css ", event -> System.exit(-1));

			}
			String css = url.toExternalForm();
			scene.getStylesheets().add(css);
		}
		catch(Exception e){
			Alert alert = new Alert("Error","Could not load resource : plots.css ",event -> System.exit(-1));
		}
	}
	
	private static class VoidEventHandler implements EventInterface
	{
		@Override
		public void onRefresh(PlotBase plot)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGoHome()
		{
			// TODO Auto-generated method stub
			
		}
	}
	private static VoidEventHandler voidHandler = new VoidEventHandler();

	@Override
	public void onRefresh(PlotBase plot)
	{
		eventHandler.onRefresh(plot);
	}
	public void onGoHome()
	{
		eventHandler.onGoHome();
	}
	public void setEventHandler(EventInterface eventHandler)
	{
		this.eventHandler = eventHandler;
	}
}
