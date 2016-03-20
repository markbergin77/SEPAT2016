package bomData;

import java.io.Serializable;
import java.util.Vector;

public class Location implements Serializable 
{
	private String name;
	private String url;
	private String state;
	
	public Location(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public Location(String name, String url, String state) {
		this.name = name;
		this.url = url;
		this.state = state;
	}
	
	public String getName() {
		return name;
	}
	
	public String getURL() {
		return url;
	}
	
	public String getState() {
		return state;
	}
}
