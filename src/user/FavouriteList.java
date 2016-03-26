package user;

import java.util.Vector;

import bomData.Location;

public class FavouriteList extends Vector<Location> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4022414816209237935L;

	//Initialize vector of favourites
	public static FavouriteList initialiseFavourites()
	{
		FavouriteList favourites = new FavouriteList();
		return favourites;
	}

	//Opens file uses it to add to vector list
	public void readFavouriteFile()
	{
		
	}
	
	//Sorts the favourites vector on a number of views basis.
	public void SortFavouriteList()
	{
		
	}
}
