package backend;

import java.io.Serializable;
import java.util.Vector;

public class Location implements Serializable {
	private String name;
	private String url;
	private String lat;
	private String lon;
	private String state;
	private Vector<Readings> data;
	private boolean favourite;
	
	public Location(String name, String url, String lat, String lon, String state) {
		this.name = name;
		this.url = url;
		this.lat = lat;
		this.lon = lon;
		this.state = state;
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
	
	public void addFavourite() {
		this.favourite = true;
	}
	
	public void removeFavourite() {
		this.favourite = false;
	}
	
	public void addReadings(Readings readings) {
		data.add(readings);	
	}
	
}
