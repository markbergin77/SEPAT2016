package EXPGUI.Layouts.GuiElements.ButtonsPane;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Pavel Nikolaev on 11/05/2016.
 */
public class ButtonsPane extends Parent {

    private Button exportGraph,exportTable,showGraph,showTable;

    public StackPane getButtonsPane(StackPane pane){

        pane.setMaxSize(630, 100);
        Rectangle rect = new Rectangle(630,100);rect.setArcHeight(20);

        rect.setArcWidth(20);
        rect.setStroke(Color.LIGHTSLATEGRAY);
        rect.setFill(Color.rgb(38, 38, 38));
        rect.setStrokeWidth(2);

        rect.setId("rect");
        rect.applyCss();

        exportGraph = new Button ("Export Graph");
        exportTable = new Button("Export Table");
        showGraph = new Button("Show Graph");
        showTable = new Button("Show table");

        exportGraph.setMaxSize(100, 35);
        exportTable.setMaxSize(100, 35);
        showGraph.setMaxSize(100, 35);
        showTable.setMaxSize(100, 35);

        showTable.getStyleClass().add("exportbutton");
        showGraph.getStyleClass().add("exportbutton");
        exportGraph.getStyleClass().add("exportbutton");
        exportTable.getStyleClass().add("exportbutton");

        showTable.setOnMouseEntered(e -> showTable.getStyleClass().add("export-button-bright"));
        showGraph.setOnMouseEntered(e -> showGraph.getStyleClass().add("export-button-bright"));
        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
        exportTable.setOnMouseEntered(e -> exportTable.getStyleClass().add("export-button-bright"));

        showTable.setOnMouseExited(e -> showTable.getStyleClass().remove("export-button-bright"));
        showGraph.setOnMouseExited(e -> showGraph.getStyleClass().remove("export-button-bright"));
        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));
        exportTable.setOnMouseExited(e -> exportTable.getStyleClass().remove("export-button-bright"));

        pane.getChildren().addAll(rect,exportGraph,exportTable,showGraph,showTable);

        StackPane.setMargin(showGraph, new Insets(0,465,0,0));
        StackPane.setMargin(showTable, new Insets(0,155,0,0));
        StackPane.setMargin(exportGraph, new Insets(0,0,0,465));
        StackPane.setMargin(exportTable, new Insets(0,0,0,155));
       return pane;
    }

}
