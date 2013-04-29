/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : MovieMessage.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Clase que crear mensaje con la informaci—n de la pelis
--------------------------------------------------------------- */
package com.example.mateofornescatalog;

import java.io.Serializable;

public class MovieMessage implements Serializable  {
	
	static final long serialVersionUID = 1 ;
	
	/* Message types */
   static final int MSG_SEND_MOVIE = 3;
   static final int MSG_SAVE_MOVIE=4;
	
	private int 	Type;
	private String	From;
	private String 	ChatMessage;
	private double	LocLatitude;
	private double	LocLongitude;
	private String	LocProvider;
	private Movie movie;

	
	public MovieMessage() {
		this.setType(0);
		this.setFrom("None");
		this.setChatMessage("");
		this.setLatitude(0);
		this.setLongitude(0);
		this.setProvider(null);
	}	 
	
	
	public MovieMessage(int t, String f) {
		this.setType(t);
		this.setFrom(f);
		this.setChatMessage("");
		this.setLatitude(0);
		this.setLongitude(0);
		this.setProvider(null);
	}
	
	
	public 	MovieMessage(int t, String f, String chat, double lat, double lon, String pro) 
	{ 
		this.setType(t);
		this.setFrom(f);
		this.setChatMessage(chat);
		this.setLatitude(lat);
		this.setLongitude(lon);
		this.setProvider(pro);
	}
	
	public void setType (int t) { 
		Type=t;
	}
	
	public void setFrom(String f) { 
		From=f;
	}
	
	public void setChatMessage(String chat) { 
		ChatMessage=chat;
	}
	
	public void setLatitude(double lat) {
		LocLatitude=lat;
	}
	
	public void  setLongitude(double lon) {
		LocLongitude=lon;
	}
	
	public void setProvider(String pro) {
		LocProvider=pro;
	}
	
	
	public void setLocation(double lat, double lon, String pro) { 
		LocLatitude=lat;
		LocLongitude=lon;
		LocProvider=pro;
	}

	
	public int getType () { 
		return Type;
	}
	
	public String getFrom() { 
		return From;
	}
	
	public String getChatMessage() { 
		return ChatMessage;
	}
	
	public double getLatitude() {
		return LocLatitude;
	}
	
	public double getLongitude() {
		return LocLongitude;
	}
	
	public String getProvider() {
		return LocProvider;
	}
	
	public String toString() {
		return new String("Type: "+Type+" From: "+From+" Mes:"+ChatMessage+" Loc: "+LocLatitude+","+LocLongitude);
	}

	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
