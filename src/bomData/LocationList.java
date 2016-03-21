package bomData;

import java.util.Iterator;
import java.util.Vector;


public class LocationList implements Iterable<Location>
{
	Vector<Location> locations = new Vector<Location>();
	// For searching
	Vector<String> locNames = new Vector<String>();
	
	public LocationList()
	{
	}
	
	public void add(Location l)
	{
		locations.add(l);
		locNames.add(l.getName());
	}
	
	public Vector<Location> fuzzySearch(String key)
	{
		Vector<Location> locationsMatching = new Vector<Location>();
		for (int i = 0; i < locations.size(); ++i)
		{
			Location iLoc = locations.get(i);
			if(iLoc.getName().toLowerCase().contains(key.toLowerCase()))
			{
					locationsMatching.add(iLoc);			
			}
		}
		return locationsMatching;
	}

	@Override
	public Iterator<Location> iterator() 
	{
		// TODO Auto-generated method stub
		return locations.iterator();
	}
}
