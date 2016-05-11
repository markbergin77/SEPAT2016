package data;

import java.io.Serializable;

public class Station implements Serializable
{
	private String name;
	private String jsonUrl;
	private String htmlUrl;
	private String state;

	public Station(String name, String jsonUrl, String htmlUrl, String state)
	{
		this.name = name;
		this.jsonUrl = jsonUrl;
		this.htmlUrl = htmlUrl;
		this.state = state;
	}

	public Station(Station loc)
	{
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Station station)
	{
		return this.name.equals(station.name) && this.jsonUrl.equals(station.jsonUrl)
				&& this.htmlUrl.equals(station.htmlUrl) && this.state.equals(station.state);
	}

	public String getName()
	{
		return name;
	}

	public String getJsonUrl()
	{
		return jsonUrl;
	}

	public String getHtmlUrl()
	{
		return htmlUrl;
	}

	public String getState()
	{
		return state;
	}
}
