package user;

import java.io.IOException;

import bomData.Location;

public class Favourite extends Location 
{
	private int timesViewed;
	
	public Favourite(String name, String jsonUrl, String htmlUrl, String state, int timesViewed) {
		super(name, jsonUrl, htmlUrl, state);
		this.timesViewed = timesViewed; 
	}	
	
	public int timesViewed()
	{
		return timesViewed;
	}
	
	//Add favorite to vector from initialized vector and matched location.
	public static void addselectedFavourite(Location favouriteLocation, FavouriteList favouritesList) throws IOException
	{
		favouritesList.add(new Favourite(favouriteLocation.getName(), favouriteLocation.getJsonUrl(), favouriteLocation.getHtmlUrl(), 
				favouriteLocation.getState(), 1));	
	}
	//Function for returning the favourite as a Location (to use in weather locations
	//Remember to increment times Viewed by one
	public Location checkWthrFavourite(Favourite matched )
	{
		return null;
		
	}
	//Function for removing Favourite from vector
	public void removeFavourite(Favourite matched)
	{
		
	}

}
