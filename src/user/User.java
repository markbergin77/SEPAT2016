package user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import data.Station;
import guiPlots.PlotWindow;
import guiPlots.PlotWindows;
import javafx.scene.effect.Light.Point;

public class User implements Serializable
{
	FavouritesList faves;
	Integer mainWindowX, mainWindowY;
	PlotWindowsSaved storedPlots;
	
	public User()
	{
		mainWindowX = new Integer(0);
		mainWindowY = new Integer(0);
		faves = FavouritesList.create();
		storedPlots = new PlotWindowsSaved();
	}
	

	public PlotWindows reconstructPlotWindows()
	{
		PlotWindows windowsOut = new PlotWindows();
		for (PlotWindowSaved savedWindow : storedPlots)
		{
			windowsOut.add(savedWindow.restorePlotWindow());
		}
		/* If you don't do this, window left open on close
		 * will restore on every open for ever. 
		 * perhaps make different variable for "loaded" plotWindows? */
		storedPlots.clear();
		return windowsOut;
	}
	
	public void storePlotWindows(PlotWindows windows)
	{
		for (PlotWindow window : windows)
		{
			storedPlots.add(new PlotWindowSaved(window));
		}
	}
	
	public void setMainWindowPosSave(double d, double e)
	{
		mainWindowX = (int)d;
		mainWindowY = (int)e;
	}
	
	public FavouritesList getFavs()
	{
		return faves;
	}
	
	public static User create()
	{
		return new User();
	}
	
	public static User loadUser(String pathToFile) throws FileNotFoundException
	{
		User newUser = null;
		try 
		{
			FileInputStream fis = new FileInputStream(pathToFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try 
			{
				newUser = (User) ois.readObject();
			} 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated catch block
			ois.close();
			fis.close();
		}
		catch (FileNotFoundException fnf)
		{
			throw(fnf);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return newUser;
	}
	//Saves user to file
	public static void saveUser(User user, String path)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(user);
			fos.close();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Sorts the favourites  on a number of views basis.
	public void SortFavourites(FavouritesList list)
		{	
			boolean notSorted = true;
			while (notSorted)
			{
				notSorted = false;
				for(int base = 0; base < list.size() - 1; base++)
				{
					if(list.get(base).timesViewed() < list.get(base+1).timesViewed())
						{
							Favourite temp = list.get(base);
							list.set(base,list.get(base + 1));
							list.set(base + 1, temp);
							notSorted = true;
						}	
				}
			}
							
		}
	public Favourite favFor(Station station) 
	{
		for (Favourite fav : faves)
		{
			if(fav.getStation().equals(station))
			{
				return fav;
			}
		}
		return null;
	}

	public double getWindowY()
	{
		return mainWindowY;
	}
	
	public double getWindowX() 
	{
		return mainWindowX;
	}
				
}
