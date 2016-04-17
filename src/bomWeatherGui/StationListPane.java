package bomWeatherGui;

import bomData.Station;
import bomData.StationList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
public class StationListPane {

    private static VBox vbox;
    /* why the variable? Because otherwise,
     * if we change the handler, we have to 
       loop through all the buttons and change
       them individually. */
    EventHandler<? super MouseEvent> clickHandler;

    
    
    public StationListPane()
    {
    	vbox = new VBox(8);
    	vbox.setPadding(new Insets(15, 0, 15, 0));
    }
    
    public void createStationButtons(StationList bomStations, 
    		EventHandler<? super MouseEvent> clickHandler)
    {
    	this.clickHandler = clickHandler;
    	for (Station bomStation : bomStations)
    	{
    		ListNode node = new ListNode(bomStation);
    		node.setOnMouseClicked(e -> this.clickHandler.handle(e));
            vbox.getChildren().add(node);
            node.toFront();
    	}
    }
    
    public void setOnMouseClicked(EventHandler<? super MouseEvent> clickHandler)
    {
    	this.clickHandler = clickHandler;
    }

    public VBox getVBox(){
        return vbox;
    }

    public VBox updateList(){


        return null;
    }

    // need to think of a way to visually update the GUI when we search for a station. maybe have a listUpdate() method
    // and return that list for the gui to fade in
    // if you do think of a way to do this, implement all the back end for it and tell me i will find a way to represent it


}
