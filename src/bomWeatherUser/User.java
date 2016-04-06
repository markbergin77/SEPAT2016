package bomWeatherUser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class User 
{
	FavouriteList faves;
	
	private User()
	{
		faves = FavouriteList.create();
	}
	
	private User(String pathToFile) throws FileNotFoundException
	{
		try 
		{
			FileInputStream fis = new FileInputStream(pathToFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try 
			{
				faves = (FavouriteList) ois.readObject();
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
	}
	
	public FavouriteList getFaves()
	{
		return faves;
	}
	
	public static User create()
	{
		return new User();
	}
	
	public static User loadUser(String pathToFile) throws FileNotFoundException
	{
		return new User(pathToFile);
	}
	
	public void saveUser(String path)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(faves);
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
	public void SortFavourites(FavouriteList list)
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
				
}
