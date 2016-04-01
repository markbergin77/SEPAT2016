package bomWeatherUser;

import java.util.Vector;

public class FavouriteList extends Vector<Favourite> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4022414816209237935L;

	//Initialize vector of favourites
	public static FavouriteList create()
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
