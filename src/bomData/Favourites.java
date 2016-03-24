package bomData;

public class Favourites extends Location 
{
	private int timesViewed;
	
	public Favourites(String name, String jsonUrl, String htmlUrl, String state, int timesViewed) {
		super(name, jsonUrl, htmlUrl, state);
	}	
	
	public void timesViewed(int newValue)
	{
		timesViewed = newValue;
	}
	
	
}
