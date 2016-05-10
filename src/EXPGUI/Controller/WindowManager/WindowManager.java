package EXPGUI.Controller.WindowManager;


import EXPGUI.Controller.Controller;
import EXPGUI.WindowCreator.WindowCreator;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.fxml.Initializable;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pavel Nikolaev on 8/05/2016.
 */
public class WindowManager {
//
//    private Service<Void> thread;
//    private Controller controller;
//    private OpenWindows openWindows;
//
//    public WindowManager(Controller controller) {
//        this.controller = controller;
//        this.openWindows = new OpenWindows();
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//    }
//
//
//    public void onStartUp() throws Exception {
//
//        FileInputStream fileInputStream = new FileInputStream("wndwLoc.txt");
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//        this.openWindows = (OpenWindows) objectInputStream.readObject();
//        objectInputStream.close();
//
//    }
//
//    public void loadAllWindows() {
//        for (OpenWindow openWindow : openWindows) {
//            openWindow(openWindow);
//        }
//    }
//
//    public void saveAllWindows() throws Exception {
//
//        FileOutputStream fileOutputStream = new FileOutputStream("wndwLoc.txt");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(openWindows);
//        objectOutputStream.close();
//    }
//
//    public void openWindow(OpenWindow openWindow) {
//
//        Stage window = new Stage();
//        window.setTitle(openWindow.getStation() + ", " + openWindow.getType());
//
//        thread = new Service<Void>() {
//
//            @Override
//            protected Task<Void> createTask() {
//                return new Task<Void>() {
//                    @Override
//                    protected Void call() throws Exception {
//
//                        if(openWindow.getType().equals("72HrTable")){
//
//                            window.setScene(WindowCreator.get72HrTable(openWindow.getStation()));
//                            window.show();
//
//                        }
//                        else if(openWindow.getType().equals("72HrGraph")){
//
//                            window.setScene(WindowCreator.get72HrGraph(openWindow.getStation()));
//                            window.show();
//
//                        }
//                        else if(openWindow.getType().equals("HistTable")){
//
//                            window.setScene(WindowCreator.getHistoricalTable(openWindow.getStation()));
//                            window.show();
//                        }
//                        else{
//                            window.setScene(WindowCreator.getHistoricalGraph(openWindow.getStation()));
//                        }
//                        return null;
//                    }
//                };
//            }
//        };
//        thread.start();
//
//        thread.setOnSucceeded(e ->{
//            Platform.runLater(() -> {
//                window.setX(openWindow.getxPos());
//                window.setY(openWindow.getyPos());
//                window.show();
//            });
//        });
//
//    }
//
//    public void add(OpenWindow window) {
//        openWindows.add(window);
//    }
//
//    public void remove(OpenWindow window) {
//        openWindows.remove(window);
//    }


}
