package gui;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/* Mostly static functions that will aid 
 * a Main class extending Javafx Application */
public class GuiAppUtils 
{
	static class Offset
	{
		double xOffset;
		double yOffset;
	};
	//Class for dragging the window around
	public static void setDragsWindow(Scene scene, Stage window)
	{
		Offset newOffset = new Offset();
		scene.setOnMousePressed(new EventHandler<MouseEvent>() 
		{
			Offset offset = newOffset;
            @Override
            public void handle(MouseEvent event) {
                offset.xOffset = window.getX() - event.getScreenX();
                offset.yOffset = window.getY() - event.getScreenY();
            }
        });
		
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() 
		{
			Offset offset = newOffset;
            @Override
            public void handle(MouseEvent event) 
            {
            	window.setX(event.getScreenX() + offset.xOffset);
            	window.setY(event.getScreenY() + offset.yOffset);
            }
        });
	}
}
