package bomWeatherUser;

import java.io.IOException;

import bomData.StationList;

public class SaveUserTest 
{
	public static void main(String args[]) 
	{
		try {
			StationList stations = StationList.getAllFromServer();
			User user = User.create();
			user.getFaves().add(Favourite.create(stations.get(0)));
			user.getFaves().add(Favourite.create(stations.get(3)));
			user.saveUser("data/user");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
