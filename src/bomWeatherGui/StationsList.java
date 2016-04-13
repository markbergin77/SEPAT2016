package bomWeatherGui;

import bomData.Station;
import bomData.StationList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Vector;

public class StationsList {

    private static VBox stationsList;

    public StationsList(HomeScreen home){ // change constructor to accept stations list as well

        //for each station object in the vector....
        this.stationsList = new VBox(8);

      // for(Station station : stations){ // currently doesnt work so i have a standard for loop for the moment


          // ListNode node = new ListNode("", station);//if you have "s" as the argument, it creates a smaller panel
         //  this.stationsList.add(node);

      // }

        stationsList.setAlignment(Pos.CENTER);
        stationsList.setPadding(new Insets(15, 0, 15, 0));
        for(int i =0;i<100;i++){

            ListNode node = new ListNode("", new Station("hey","hey","hey","heyyy"));

            node.setOnMouseClicked(e ->{

               home.setChart(node.returnChart());

                if(home.getSize().equals("L")){
                    home.setTable(node.getTable(800, 250));
                }
                if(home.getSize().equals("MH")){
                    home.setTable(node.getTable(730, 200));
                }
                if(home.getSize().equals("ML")){
                    home.setTable(node.getTable(630, 200));
                }

            });

            stationsList.getChildren().add(node);
            node.toFront();

        }

    }

    public VBox returnList(){
        return stationsList;
    }

    public void updateList(){


    }

    // need to think of a way to visually update the GUI when we search for a station. maybe have a listUpdate() method
    // and return that list for the gui to fade in
    // if you do think of a way to do this, implement all the back end for it and tell me i will find a way to represent it


}
