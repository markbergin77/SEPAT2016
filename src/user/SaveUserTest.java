package user;

import java.io.IOException;

import bomData.LocationList;

public class SaveUserTest 
{
	public static void main(String args[]) 
	{
		try {
			LocationList locations = LocationList.getAllFromServer();
			User user = User.create();
			user.getFaves().add(Favourite.create(locations.get(0)));
			user.saveUser("data/user");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
