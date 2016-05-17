package data.samples;

import java.time.LocalDateTime;

public class FioSampleDaily
{
	LocalDateTime time;
	String icon;
	String tempMin;
	String tempMax;
	
	public FioSampleDaily(LocalDateTime time, String icon, String tempMin, String tempMax)
	{
		super();
		this.time = time;
		this.icon = icon;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
	}
	
	public LocalDateTime getTime()
	{
		return time;
	}
	public String getIcon()
	{
		return icon;
	}
	public String getTempMin()
	{
		return tempMin;
	}
	public String getTempMax()
	{
		return tempMax;
	}
	
}
