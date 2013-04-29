/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : MovieMessageParcelable.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Clase que permite enviar mensaje compriemiendo las pel’culas
como android requiere.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class MovieMessageParceable implements Parcelable {
	static final long serialVersionUID = 1 ;
	
	/* Message types */ 
    static final int MSG_SEND_MOVIE = 1;
    static final int MSG_SAVE_MOVIE = 2;
    static final int MSG_VETE_MIERDA = 3;
   
	private int 		Type;
	private String		From; 
	private Location	Localization;
	private String 	    movieName;
	private Movie		movie;


	public MovieMessageParceable(int type) {
		this.setType(type);
		this.setFrom("None");
		this.setLocation(null);
		
	}	 
		
	public MovieMessageParceable(int t, String f, Location l, String c,String mName, Movie m) {
		this.setType(t);
		this.setFrom(f);
		this.setLocation(l);
		this.setMovieName(mName);
		this.setMovie(m);
	}
	
    public MovieMessageParceable(Parcel source){
       
        Type = source.readInt();
        From = source.readString();
        Localization = source.readParcelable(Location.class.getClassLoader());
        movieName=source.readString();
        //movie = (Movie) source.readSerializable();
        movie = (Movie) source.readValue(Movie.class.getClassLoader());
    }
    
    public int describeContents(){
    	return 0;
    }


	public void writeToParcel(Parcel dest, int flags) {
	      Log.v("MovieParceable", "writeToParcel..."+ flags);
	      dest.writeInt(Type);
	      dest.writeString(From);
	      dest.writeParcelable(Localization,0);
	      dest.writeString(movieName);
	      dest.writeValue(movie);    
	}
	
    public static final Parcelable.Creator< MovieMessageParceable> CREATOR
    = new Parcelable.Creator< MovieMessageParceable>() {
    		public  MovieMessageParceable createFromParcel(Parcel in) {
    			return new  MovieMessageParceable(in);
    		}

    		public  MovieMessageParceable[] newArray(int size) {
    			return new  MovieMessageParceable[size];
    	}
    };

	public void setType (int t) { 
		Type=t;
	}
		
	public void setFrom(String f) { 
		From=f;
	}
		
	public void setLocation(Location loc) { 
		Localization=loc;
	} 
		
	public int getType () { 
		return Type;
	}
		
	public String getFrom() { 
		return From;
	}
		
	public Location getLocation() { 
		return Localization;
	} 
	

	public String getMovieName() {
		return movieName;
		
	}
	
	public void setMovieName(String movieName) {
		this.movieName=movieName;
		
	}

	public Movie getMovie() {
		return this.movie;
	}
	
	public void setMovie(Movie movie){
		this.movie=movie;
	}

}
