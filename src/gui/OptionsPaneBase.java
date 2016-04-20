package gui;

import data.Station;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/* All the options for one station.
 * Goes in the OptionsArea which is/has
 * a TapPane. */
public class OptionsPaneBase extends VBox
{
	Station station;
	GuiEventInterface eventHandler;
	String button72HrText = "Open 72 Hour Temperature Plot";
	Button open72HrPlotButton = new Button(button72HrText);
	String buttonHisTempTxt = "Open Historical Temperature Plot";
	Button OpenHisTempPlotButton= new Button(buttonHisTempTxt);
	
	public OptionsPaneBase(Station station, GuiEventInterface eventHandler)
	{
		super();
		this.station = station;
		this.eventHandler = eventHandler;
		open72HrPlotButton.setOnMouseClicked(e -> eventHandler.onOpen72TempPlot(station));
		OpenHisTempPlotButton.setOnMouseClicked(e -> eventHandler.onOpenHisTempPlot(station));
		addOption(OpenHisTempPlotButton);
		addOption(open72HrPlotButton);
	}
	
	void removeOption(Parent node)
	{
		getChildren().remove(node);
	}
	
	void removeOptionTop()
	{
		getChildren().remove(0);
	}
	
	void addOptionTop(Parent node)
	{
		getChildren().add(0, node);
	}
	
	void addOption(Parent node)
	{
		getChildren().add(node);
	}

	public Station getStation() 
	{
		return station;
	}
}
