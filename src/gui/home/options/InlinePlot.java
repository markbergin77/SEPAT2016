package gui.home.options;

import data.Station;
import gui.plots.Last72hrTemp;
import gui.plots.PlotBase;
import gui.plots.PlotBase.EventInterface;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * Created by Pavel Nikolaev on 28/05/2016.
 */
public class InlinePlot extends GridPane
	implements PlotBase.EventInterface
{
	public InlinePlot(Station station)
	{
		StackPane pane = new StackPane();
		pane.setMaxSize(1300, 900);
		pane.setMinSize(300, 300);
		add(pane, 0, 0);

		Last72hrTemp plot = new Last72hrTemp(station);
		plot.setEventHandler(this);

		plot.setMaxSize(1000, 500);
		plot.setMinSize(500, 300);

		pane.getChildren().add(plot);
		StackPane.setAlignment(plot, Pos.CENTER);

		setFillHeight(pane, true);
		setFillWidth(pane, true);

		setHgrow(pane, Priority.ALWAYS);
		setVgrow(pane, Priority.ALWAYS);

		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(100);
		getColumnConstraints().add(c1);

	}
	
	public void setEventHandler(EventInterface handler)
	{
		this.eventHandler = handler;
	}

	@Override
	public void onRefresh(PlotBase plot)
	{
		eventHandler.onRefresh(plot);
	}
	
	public interface EventInterface
	{
		public void onRefresh(PlotBase plot);
	}
	EventInterface eventHandler = voidHandler;
	
	private static class VoidEventHandler implements EventInterface
	{
		@Override
		public void onRefresh(PlotBase plot)
		{
			// TODO Auto-generated method stub
			
		}
	}
	private static VoidEventHandler voidHandler = new VoidEventHandler();
}
