package EXPGUI.Utilities2;

import EXPGUI.Gui.Utilities;
import javafx.scene.Scene;


/**
 * Created by Pavel Nikolaev on 4/04/2016.
 */

// class for resizing window by dragging edges
public class WindowResizeListener {

    private Scene window;
    private static double width;
    private static double height;
    private static Utilities util = new Utilities();

    public WindowResizeListener(Scene window) {
        this.window = window;
    }

    public void setResizeListener() {

        width = window.getWidth();
        height = window.getHeight();

        window.setOnMousePressed(e -> {
            //   util.print(e.getSceneX());
        });
    }
}
