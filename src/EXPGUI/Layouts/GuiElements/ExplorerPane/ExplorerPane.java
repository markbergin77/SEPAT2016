package EXPGUI.Layouts.GuiElements.ExplorerPane;


import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class ExplorerPane extends Parent {

    private TabPane tabPane;

    public  StackPane getExplorerLarge(StackPane pane) {

        Rectangle explorerRect = new Rectangle(430, 625);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);

        explorerRect.setId("rect3");
        explorerRect.applyCss();

        TabPaneCreator tabPaneCreator = new TabPaneCreator();
        tabPaneCreator.setId("tabpanecreator");

        this.tabPane = new TabPane();
        tabPane = tabPaneCreator.getTabLarge(tabPane);

        StackPane.setAlignment(tabPane,Pos.TOP_CENTER);

        pane.getChildren().addAll(explorerRect,tabPane,tabPaneCreator);
        pane.setMaxSize(430, 635);

        return pane;
    }

    public   StackPane getExplorerMedium(StackPane pane) {

        Rectangle explorerRect = new Rectangle(430, 580);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        // explorerRect.setOpacity(0);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);

        explorerRect.setId("rect3");
        explorerRect.applyCss();

        TabPaneCreator tabPaneCreator = new TabPaneCreator();
        this.tabPane = new TabPane();
        tabPane = tabPaneCreator.getTabMedium(tabPane);
        StackPane.setAlignment(tabPane,Pos.TOP_CENTER);

        pane.getChildren().addAll(explorerRect, tabPane);
        pane.setMaxSize(430, 580);

        return pane;

    }

    public  StackPane getExplorerSmall(StackPane pane) {

        Rectangle explorerRect = new Rectangle(400, 475);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        // explorerRect.setOpacity(0);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);

        explorerRect.setId("rect3");
        explorerRect.applyCss();

        TabPaneCreator tabPaneCreator = new TabPaneCreator();
        this.tabPane = new TabPane();
        tabPane = tabPaneCreator.getTabSmall(tabPane);

        StackPane.setAlignment(tabPane,Pos.TOP_CENTER);

        pane.getChildren().addAll(explorerRect, tabPane);
        pane.setMaxSize(400, 475);

        return pane;

    }



}
