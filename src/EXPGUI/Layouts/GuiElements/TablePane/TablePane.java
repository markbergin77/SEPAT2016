package EXPGUI.Layouts.GuiElements.TablePane;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class TablePane extends Parent{

    private TableView dataTable;

    public StackPane getTable(StackPane tablePane, int size){


        switch(size){
            case 1:tablePane.setMaxSize(800, 200);
                break;
            case 2:tablePane.setMaxSize(800, 200);
                break;
            case 3:tablePane.setMaxSize(800, 200);
                break;
            default:
                tablePane.setMaxSize(800, 200);
                break;

        }

        this.dataTable = new TableView();
        dataTable.setPlaceholder(new Label("Select a station"));

        dataTable.setId("datatable");
        tablePane.getChildren().addAll(dataTable);
        StackPane.setAlignment(dataTable, Pos.CENTER);

       return tablePane;

    }

    public TableView getDataTable() {
        return dataTable;
    }

    public void setDataTable(TableView dataTable) {
        this.dataTable = dataTable;
    }
}
