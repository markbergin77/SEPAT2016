package app;

import javafx.stage.Window;
import user.WindowLocation;

import java.io.*;
import java.util.ArrayList;

/* Collection of all the open plot windows
 * to be saved in the User file later */
public class PlotWindowsOpen
{

    ArrayList<Window> openWindows;

    public PlotWindowsOpen(){
         this.openWindows = new ArrayList<Window>();
    }

    public void addWindow(Window window){
        openWindows.add(window);
    }

    public void saveWindowLocations(){

        for(Window window : openWindows){

            WindowLocation currentLoc = new WindowLocation(window.getX(),window.getY());

            FileOutputStream fos;
            try {

                //change the path to whatever later
                fos = new FileOutputStream("plotWindowLocations.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(currentLoc);
                fos.close();
                oos.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException e1) {

                // have a look at using the Alert.java class in the gui to raise
                // alerts that the user can actually see
                // instead of just printing the stack trace which the user
                // probably wont understand anyway
                // its a static method and no object instantiation needed
                e1.printStackTrace();
            }
        }
    }

    public void reOpenWindows(){

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("javaObjects.txt"));

    }






}
