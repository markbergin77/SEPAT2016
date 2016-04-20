package GuiTableTest;
import data.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel Nikolaev on 20/04/2016.
 */
public class guiTableTest extends Application{

    StationList allStations;
    TableView dataTable;

    public static void main(String args[])
    {
        launch(args);
    }

    public void start(Stage Window) throws  Exception{


    //----------------------------------------------------------------------------------//
    // grabbing all the stations


        try {
            allStations = Bom.getAllStations();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


     //--------------------------------------------------------------------------------//
     //  creating a table of monthly data. currently not styled

        dataTable = new TableView<WthrSampleDaily>();
        ObservableList content = getData();
        dataTable.setItems(content);

    //-------------------------------------------------------------------------------------//
    // in order to create a table you have to specify the class / type of object it is representing
    // it then automatically detects the values of a particular instance of object
    // this is done when you specify the propertyValueFactory


        TableColumn<WthrSampleDaily,String> date = new TableColumn<>("Date");
        TableColumn<WthrSampleDaily,String> maxTemp = new TableColumn<>("maxTemp");
        TableColumn<WthrSampleDaily,String> minTemp = new TableColumn<>("minTemp");
        TableColumn<WthrSampleDaily,String> rain = new TableColumn<>("Rain");
        TableColumn<WthrSampleDaily,String> evap = new TableColumn<>("Evaporation");
        TableColumn<WthrSampleDaily,String> sun = new TableColumn<>("sun");
        TableColumn<WthrSampleDaily,String> temp9am = new TableColumn<>("temp9am");

        date.setCellValueFactory(new PropertyValueFactory<WthrSampleDaily, String>("date"));
        maxTemp.setCellValueFactory(new PropertyValueFactory<WthrSampleDaily, String>("maxTemp"));
        minTemp.setCellValueFactory(new PropertyValueFactory<WthrSampleDaily, String>("minTemp"));
        rain.setCellValueFactory(new PropertyValueFactory<WthrSampleDaily, String>("rain"));
        evap.setCellValueFactory(new PropertyValueFactory<WthrSampleDaily, String>("evap"));
        sun.setCellValueFactory(new PropertyValueFactory<WthrSampleDaily, String>("sun"));
        temp9am.setCellValueFactory(new PropertyValueFactory<WthrSampleDaily, String>("temp9am"));


        dataTable.getColumns().setAll(date,maxTemp,minTemp,rain,temp9am,sun);

        date.setMinWidth(100);
        maxTemp.setMinWidth(100);
        minTemp.setMinWidth(100);
        rain.setMinWidth(100);
        evap.setMinWidth(100);
        sun.setMinWidth(100);
        temp9am.setMinWidth(100);

        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(dataTable);


        Window.setTitle("Table test");
        Scene scene = new Scene(container,800,400);
        Window.setScene(scene);
        Window.show();

    }

    public ObservableList getData()throws Exception{

        // here we create a list of weather sample objects
        // each weather sample object contains data from charlton on a particular day
        // the values of each sample objects variables is automatically detected by the tableView

        List list = new ArrayList();

        Station charlton = allStations.get(0);
        YearMonth start = YearMonth.now().minusMonths(12);
        YearMonth end = YearMonth.now().plusMonths(1);

        WthrSamplesDaily dataCharlton = new WthrSamplesDaily();
        dataCharlton = Bom.getWthrRange(charlton, start, end);


        for(WthrSampleDaily sample: dataCharlton) {
            list.add(sample);
        }

        ObservableList data = FXCollections.observableList(list);

        return data;
    }

}
