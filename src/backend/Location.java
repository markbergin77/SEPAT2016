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
	
	public Location(String name, String url, String lat, String lon, String state) {
		this.name = name;
		this.url = url;
		this.lat = lat;
		this.lon = lon;
		this.state = state;
		
	}
	
	private class Readings {
		private String localDateTime;
		private String localDateTimeFull;
		private String apparentT;
		private String cloud;
		private String gustKmh;
		private String gustKt;
		private String airTemp;
		private String relHumidity;
		private String dewPt;
		private String windDir;
		private String windSpdKmh;
		private String windSpdKt;
	}
	
	public String getName() {
		return name;
	}
}
