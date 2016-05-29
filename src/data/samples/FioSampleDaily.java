package data.samples;

import java.time.LocalDateTime;

public class FioSampleDaily
{
	LocalDateTime time;
	String icon;
	String tempMin;
	String tempMax;
	String temp9am;
	String temp3pm;
	
	public FioSampleDaily(LocalDateTime time, String icon, String tempMin, String tempMax, String temp9am, String temp3pm)
	{
		super();
		this.time = time;
		this.icon = icon;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.temp9am = temp9am;
		this.temp3pm = temp3pm;
	}
	
	public LocalDateTime getDate()
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
	public String getTemp9am()
	{
		return temp9am;
	}
	public String getTemp3pm()
	{
		return temp3pm;
	}
	
}
