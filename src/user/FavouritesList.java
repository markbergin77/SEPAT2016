package user;

import java.util.Vector;

public class FavouritesList extends Vector<Favourite> 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4022414816209237935L;

	//Initialize vector of favourites
	public static FavouritesList create()
	{
		FavouritesList favourites = new FavouritesList();
		return favourites;
	}

}
