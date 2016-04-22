package gui;

/**
 * Created by Pavel Nikolaev on 13/03/2016.
 */

import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.geometry.*;


public class Alert 
{
	Stage window;
    //Design and execution for any alerts needed
    public Alert(String title, String message, EventHandler<WindowEvent> eventHandler)
    {

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);

        Label label1 = new Label();
        label1.setText(message);

        Button button1 = new Button();
        button1.setText("Close");
        button1.setOnAction(e -> window.close() );

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label1,button1);
        layout1.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout1);

        window.setScene(scene1);
        window.showAndWait();
        window.setOnCloseRequest(eventHandler);
    }

    
}
