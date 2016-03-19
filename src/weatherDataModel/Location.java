package weatherDataModel;

import java.io.Serializable;
import java.util.Vector;

public class Location implements Serializable {
	private String name;
	private String url;
	private String lat;
	private String lon;
	private String state;
	private Vector<WthrSample> data;
	private boolean favourite;
	
	public Location(String name, String url, String lat, String lon, String state) {
		this.name = name;
		this.url = url;
		this.lat = lat;
		this.lon = lon;
		this.state = state;
		this.data = new Vector<WthrSample>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getURL() {
		return url;
	}
	
	public String getLat() {
		return lat;
	}
	
	public String getLon() {
		return lon;
	}
	
	public String getState() {
		return state;
	}
	
	public boolean isFavourite() {
		return favourite;
	}
	
	public void addFavourite() {
		this.favourite = true;
	}
	
	public void removeFavourite() {
		this.favourite = false;
	}
	
	public Vector<WthrSample> getData() {
		return data;
	}
}
