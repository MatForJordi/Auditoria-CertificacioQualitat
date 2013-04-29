/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : MovieCatalog.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Clase que permite almacenar los objetos pel’cula.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;


import java.util.HashMap;

public class MovieCatalog {
	
	private HashMap<String, Movie> movies;

	public MovieCatalog() {
		super();
		this.setMovies(new HashMap<String, Movie>());
	}

	public HashMap<String, Movie> getMovies() {
		return movies;
	}

	public void setMovies(HashMap<String, Movie> movies) {
		this.movies = movies;
	}
	
	public Movie getFilm(String fName){
		return this.getMovies().get(fName);
	}
	
	public void setFilm(String fName, Movie fMovie){
		this.getMovies().put(fName, fMovie);
	}
	
	public void deleteFilm(String fName){
		this.getMovies().remove(fName);
	}
	
	public void reset(){
		this.movies.clear();
	}
	
	public Boolean existFilm(String fName){
		if(this.movies.containsKey(fName)){
			return true;
		}
		else{
			return false;
		}
	}
}

