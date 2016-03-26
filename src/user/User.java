package user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class User 
{
	FavouriteList faves;
	
	private User() throws FileNotFoundException
	{
		try 
		{
			FileInputStream fis = new FileInputStream("/data/user");
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
	
	public static User loadUser() throws FileNotFoundException
	{
		return new User();
	}
}
