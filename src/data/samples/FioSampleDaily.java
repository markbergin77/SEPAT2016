package data.samples;

public class FioSampleDaily
{
	String time;
	String icon;
	String tempMin;
	String tempMax;
	
	public FioSampleDaily(String time, String icon, String tempMin, String tempMax)
	{
		super();
		this.time = time;
		this.icon = icon;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
	}
	
	public String getTime()
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
