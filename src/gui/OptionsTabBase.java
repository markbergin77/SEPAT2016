package gui;

import data.Station;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/* Goes in the OptionsArea which is/has
 * a TapPane.
 */
public class OptionsTabBase extends Tab
{
	Station station;
	GuiEventInterface eventHandler;
	VBox vbox;
	String button72HrText = "Open 72 Hour Temperature Plot";
	Button open72HrPlotButton = new Button(button72HrText);
	public OptionsTabBase(Station station, GuiEventInterface eventHandler)
	{
		super();
		vbox = new VBox();
		setContent(vbox);
		this.station = station;
		this.eventHandler = eventHandler;
		setText(station.getName());
		open72HrPlotButton.setOnMouseClicked(e -> eventHandler.onOpen72TempPlot(station));
		open72HrPlotButton.toFront();
		addOption(open72HrPlotButton);
	}
	
	void addOptionTop(Parent node)
	{
		vbox.getChildren().add(0, node);
	}
	
	void addOption(Parent node)
	{
		vbox.getChildren().add(node);
	}
}
