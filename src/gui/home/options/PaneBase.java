package gui.home.options;

import data.Station;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.Comparator;

/* All the options for one station.
 * Goes in the OptionsArea which is/has
 * a TapPane. */
public class PaneBase extends GridPane
{
	Station station;
    String addToFavsMsg = "Add To Favourites";
    Button addToFavsButton = new Button(addToFavsMsg);
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

        add(addToFavsButton,0,0);
		add(plot72hrButton,0,1);
        add(plotHisButton,0,2);
        add(table72hrButton,0,3);
        add(tableHisButton,0,4);
        add(plotExperimental,0,5);
        add(closePlotsButton,0,6);

        addToFavsButton.setOnMouseEntered(e -> addToFavsButton.getStyleClass().add("button-hover"));
        addToFavsButton.setOnMouseExited(e -> addToFavsButton.getStyleClass().remove("button-hover"));
        plot72hrButton.setOnMouseEntered(e -> plot72hrButton.getStyleClass().add("button-hover"));
        plot72hrButton.setOnMouseExited(e -> plot72hrButton.getStyleClass().remove("button-hover"));
        plotHisButton.setOnMouseEntered(e -> plotHisButton.getStyleClass().add("button-hover"));
        plotHisButton.setOnMouseExited(e -> plotHisButton.getStyleClass().remove("button-hover"));
        table72hrButton.setOnMouseEntered(e -> table72hrButton.getStyleClass().add("button-hover"));
        table72hrButton.setOnMouseExited(e -> table72hrButton.getStyleClass().remove("button-hover"));
        tableHisButton.setOnMouseEntered(e -> tableHisButton.getStyleClass().add("button-hover"));
        tableHisButton.setOnMouseExited(e -> tableHisButton.getStyleClass().remove("button-hover"));
        plotExperimental.setOnMouseEntered(e -> plotExperimental.getStyleClass().add("button-hover"));
        plotExperimental.setOnMouseExited(e -> plotExperimental.getStyleClass().remove("button-hover"));
        closePlotsButton.setOnMouseEntered(e -> closePlotsButton.getStyleClass().add("button-hover"));
        closePlotsButton.setOnMouseExited(e -> closePlotsButton.getStyleClass().remove("button-hover"));

        for(Node child : getChildren()){
            setHgrow(child,Priority.ALWAYS);
            ((Button)child).setTextAlignment(TextAlignment.LEFT);
            ((Button) child).setMaxWidth(900);
            ((Button) child).setMinWidth(500);
            ((Button) child).setPrefWidth(500);
        }

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(100);
        getColumnConstraints().add(c1);

        setMaxWidth(900);

	}
	
	void removeOption(Parent node)
	{
		node.setVisible(false);
		node.setManaged(false);
		getChildren().remove(node);

	}
	
	void removeOptionTop()
	{
		getChildren().remove(0);
	}
	
	void addOptionTop(Button node)
	{
		node.setPrefWidth(OptionsArea.defaultWidth);
		getChildren().add(node);
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
