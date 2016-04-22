package userTest;

import java.io.IOException;

import data.Bom;
import data.StationList;
import user.Favourite;
import user.User;

public class SaveUserTest 
{
	public static void main(String args[]) 
	{
		//Class test program. Saving the user details (Just favourites, User can be expanded upon at any date)
		try {
			StationList stations = Bom.getAllStations();
			User user = User.create();
			user.getFavs().add(Favourite.create(stations.get(0)));
			user.getFavs().add(Favourite.create(stations.get(3)));
			User.saveUser(user, "data/user");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
