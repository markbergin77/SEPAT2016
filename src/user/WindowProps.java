package user;

import java.io.Serializable;

/**
 * Created by Pavel Nikolaev on 21/04/2016.
 */
public class WindowProps implements Serializable 
{

    // class for storing the users window position

    double xPos, yPos;
    double xSize, ySize;
    
    public WindowProps (double xPos, double yPos,
    		double xSize, double ySize)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
    }

}
