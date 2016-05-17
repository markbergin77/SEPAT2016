package gui.home.options;

import data.Station;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/* All the options for one station.
 * Goes in the OptionsArea which is/has
 * a TapPane. */
public class PaneBase extends VBox
{
	Station station;
	String button72HrText = "Open 72 Hour Temperature Plot";
	Button plot72hrButton = new Button(button72HrText);
	String buttonHisTempTxt = "Open Historical Temperature Plot";
	Button plotHisButton = new Button(buttonHisTempTxt);
	String buttonTable72hrTxt = "Open 72 Hour Weather Table";
	Button table72hrButton = new Button(buttonTable72hrTxt);
	String buttonTableYearlyTxt = "Open Historical Weather Table";
	Button tableHisButton = new Button(buttonTableYearlyTxt);
	String buttonExperimental = "Open Experimental Plot";
	Button plotExperimental = new Button("Open Experimental Plot");
	
	String closePlotsText = "Close Charts";
	Button closePlotsButton = new Button(closePlotsText);
	
	public PaneBase(Station station)
	{
		super();
		setPrefWidth(OptionsArea.defaultWidth);
		this.station = station;
		
		addOption(plot72hrButton);
		addOption(plotHisButton);
		addOption(table72hrButton);
		addOption(tableHisButton);
		addOption(plotExperimental);
		addOption(closePlotsButton);		
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
