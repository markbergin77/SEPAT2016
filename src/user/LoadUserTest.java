package user;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadUserTest 
{
	public static void main(String args[]) 
	{
		//Class test program. Loading the already stored user,
		//Includes sorting test, can be moved to anywhere.
		try {
			User u = User.loadUser("data/user");
			System.out.println(u.getFavs().get(0).toString());
			System.out.println(u.getFavs().get(1).toString());
			//increases viewed value by 1 for sort test
			Favourite viewed = u.getFavs().get(1);
			viewed.view();
			u.SortFavourites(u.getFavs());
			//Re-prints to show swap
			System.out.println(u.getFavs().get(0).toString());
			System.out.println(u.getFavs().get(1).toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
