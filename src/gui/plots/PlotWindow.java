package gui.plots;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;

import org.controlsfx.control.CheckComboBox;

import com.sun.javafx.tk.TKStage;

import gui.Alert;
import gui.plots.PlotBase.EventInterface;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

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
		
		//homeButton.setOnMouseEntered(e -> homeButton.getStyleClass().add("button-hover"));
        //homeButton.setOnMouseExited(e -> homeButton.getStyleClass().remove("button-hover"));
		getCss(scene);
		setScene(scene);
		setTitle(plot.getName());
 		
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
