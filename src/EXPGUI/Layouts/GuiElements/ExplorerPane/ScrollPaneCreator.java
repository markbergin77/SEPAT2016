package EXPGUI.Layouts.GuiElements.ExplorerPane;


import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class ScrollPaneCreator extends Parent{

    private ScrollPane scroll;


    public ScrollPane getScroll(ScrollPane scroll, int size) {

        this.scroll = scroll;

        switch (size) {
            case 1:
                scroll.setMaxSize(425, 570);
                break;
            case 2:
                scroll.setPrefSize(420, 550);
                break;
            case 3:
                scroll.setPrefSize(350, 475);
                break;
            default:
                scroll.setPrefSize(420, 580);
                break;

        }

       // scroll.setContent(Main.returnControl().populateScrollPane());
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setId("scroll");

        return scroll;
    }


    public void setScroll( VBox content) {
        this.scroll.setContent(content);
    }
}
