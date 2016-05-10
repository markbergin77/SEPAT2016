package gui;

import java.text.DecimalFormat;
import data.Station;
import guiCallbacks.GuiEventInterface;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/* All the options for one station.
 * Goes in the OptionsArea which is/has
 * a TapPane. */
public class OptionsPaneBase extends VBox
{
	Station station;
	GuiEventInterface eventHandler;	
	String button72HrText = "Open 72 Hour Temperature Plot";
	Button plot72hrButton = new Button(button72HrText);
	String buttonHisTempTxt = "Open Historical Temperature Plot";
	Button plotHisButton = new Button(buttonHisTempTxt);
	String buttonTable72hrTxt = "Open 72 Hour Weather Table";
	Button table72hrButton = new Button(buttonTable72hrTxt);
	String buttonTableYearlyTxt = "Open Historical Weather Table";
	Button tableHisButton = new Button(buttonTableYearlyTxt);
	
	String closePlotsText = "Close Open Charts";
	Button closePlotsButton = new Button(closePlotsText);
	
	public OptionsPaneBase(Station station, GuiEventInterface eventHandler)
	{
		super();
		setPrefWidth(OptionsArea.defaultWidth);
		this.station = station;
		this.eventHandler = eventHandler;
		
		plot72hrButton.setOnMouseClicked(e -> eventHandler.onOpen72TempPlot(station));
		plotHisButton.setOnMouseClicked(e -> eventHandler.onOpenHisTempPlot(station));
		table72hrButton.setOnMouseClicked(e -> eventHandler.onOpen72HrTable(station));
		tableHisButton.setOnMouseClicked(e -> eventHandler.onOpenHisTable(station));
		closePlotsButton.setOnMouseClicked(e -> eventHandler.onCloseAllPlots(station));
		addOption(plot72hrButton);
		addOption(plotHisButton);
		addOption(table72hrButton);
		addOption(tableHisButton);
		addOption(closePlotsButton);
		
		/* Some tasty data to add later */
		//Cheap methods for spacing used
		/*
		float[] averageTemps = new float[3];
		DecimalFormat roundtotwo = new DecimalFormat("##.00");
		averageTemps = station.findAverageTemps(station);
		Label labelHead = new Label("		     Average Temperatures:");
		Label labelEarlyMornings = new Label(" 	From 5am-10am: " + roundtotwo.format(averageTemps[0]) + " degress celcius");
		Label labelMidDays = new Label(" 	From 11am-4pm: " + roundtotwo.format(averageTemps[1]) + " degress celcius");
		Label labelNights = new Label(" 	From 5pm-10pm: " + roundtotwo.format(averageTemps[2]) + " degress celcius");
		Label labelLateNights = new Label(" 	From 11pm-4am: " + roundtotwo.format(averageTemps[3]) + " degress celcius");
		Label blankSpacing = new Label("");
		addOptionText(blankSpacing);
		addOptionText(labelHead);
		addOptionText(labelEarlyMornings);
		addOptionText(labelMidDays);
		addOptionText(labelNights);
		addOptionText(labelLateNights);*/
		
	}
	
	void removeOption(Parent node)
	{
		getChildren().remove(node);
	}
	
	void removeOptionTop()
	{
		getChildren().remove(0);
	}
	
	void addOptionTop(Button node)
	{
		node.setPrefWidth(OptionsArea.defaultWidth);
		getChildren().add(0, node);
	}
	
	void addOption(Button node)
	{
		node.setPrefWidth(OptionsArea.defaultWidth);
		getChildren().add(node);
	}
	
	void addOptionText(Label labelHead)
	{
		labelHead.setPrefWidth(OptionsArea.defaultWidth);
		getChildren().add(labelHead);
	}

	public Station getStation() 
	{
		return station;
	}
}
