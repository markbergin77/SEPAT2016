package data.samples;

import java.time.LocalDateTime;

//Data types/attributes provided by BOM data, used for Monthly Observations
public class WthrSampleFine
{
	private LocalDateTime localDateTime;
	private String lat;
	private String lon;
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

	public WthrSampleFine(LocalDateTime localDateTime, String lat, String lon, String apparentT, String cloud,
			String gustKmh, String gustKt, String airTemp, String relHumidity, String dewPt, String windDir,
			String windSpdKmh, String windSpdKt)
	{

		this.localDateTime = localDateTime;
		this.lat = lat;
		this.lon = lon;
		this.apparentT = apparentT;
		this.cloud = cloud;
		this.gustKmh = gustKmh;
		this.gustKt = gustKt;
		this.airTemp = airTemp;
		this.relHumidity = relHumidity;
		this.dewPt = dewPt;
		this.windDir = windDir;
		this.windSpdKmh = windSpdKmh;
		this.windSpdKt = windSpdKt;
	}

	public String toString()
	{
		String output = new String("Date: ");
		output += localDateTime;
		output += "\nApparent temperature: ";
		output += apparentT;
		output += "\nCloud: ";
		output += cloud;
		output += "\nHumidity: ";
		output += relHumidity;
		output += "\ntoString for WthrSample not fully implemented.\n";
		return output;
	}

	public LocalDateTime getLocalDateTime()
	{
		return localDateTime;
	}
	
	public String getLat() 
	{
		return lat;
	}

	public String getLon() 
	{
		return lon;
	}
	
	public String getApparentT()
	{
		return apparentT;
	}

	public String getCloud()
	{
		return cloud;
	}

	public String getGustKmh()
	{
		return gustKmh;
	}

	public String getGustKt()
	{
		return gustKt;
	}

	public String getAirTemp()
	{
		return airTemp;
	}

	public String getRelHumidity()
	{
		return relHumidity;
	}

	public String getDewPt()
	{
		return dewPt;
	}

	public String getWindDir()
	{
		return windDir;
	}

	public String getWindSpdKmh()
	{
		return windSpdKmh;
	}

	public String getWindSpdKt()
	{
		return windSpdKt;
	}
}
