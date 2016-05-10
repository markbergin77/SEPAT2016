package EXPGUI.Layouts.GuiElements.CustomToolBar;

import EXPGUI.Gui.Main;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class CustomToolBar {
    public static ToolBar returnToolbar (ToolBar toolBar, int size){

        switch(size){

            case 1:
                break;
            case 2:
                break;
            case 3:
                break;

            default:
                break;
        }


       //  creating a exit button for the toolbar
         Button exitButton = new Button("X");
         exitButton.setId("exit");
         exitButton.setMaxSize(50, 25);

        // apply a different css style when user hovers over
        // the button
         exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
         exitButton.setOnMouseExited(e -> exitButton.setId("exit"));
         exitButton.setOnMousePressed(e -> Main.returnControl().exit());


       // create a toolbar

        toolBar = new ToolBar(exitButton);
        toolBar.setMaxSize(1350, 35);
      //  toolBar.setOpacity(0);

        return toolBar;
    }
}
