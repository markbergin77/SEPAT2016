package user;

import java.io.Serializable;

/**
 * Created by Pavel Nikolaev on 21/04/2016.
 */
public class WindowLocation implements Serializable {

    // class for storing the users window position

    private int xPos, yPos;

    public WindowLocation (int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

}
