/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : Movie.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Define los objetos pel’cula
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable{

	private static final long serialVersionUID = 3066027875578369571L;
	private String name;
	private String summary;
	private float qualification;
	private boolean see;
	private boolean want2see;
	private List<String> directors;
	private List<String> actors;
	private String genre;
	
	public Movie(){
		this.name = "";
		this.summary = "";
		this.qualification = 0;
		this.see = false;
		this.want2see = false;
		this.actors= new ArrayList<String>();
		this.directors= new ArrayList<String>();
		this.genre="";
		
	}
	public Movie(String name, String summary, float qualification, boolean see,
			boolean want2see) {
		this.name = name;
		this.summary = summary;
		this.qualification = qualification;
		this.see = see;
		this.want2see = want2see;
	}
	
	public void copy(Movie movie){
		this.name = movie.getName();
		this.summary = movie.getSummary();
		this.qualification = movie.getQualification();
		this.see = movie.isSee();
		this.want2see = movie.isWant2see();
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public float getQualification() {
		return this.qualification;
	}
	public void setQualification(float f) {
		this.qualification = f;
	}
	public boolean isSee() {
		return this.see;
	}
	public void setSee(boolean see) {
		this.see = see;
	}
	public boolean isWant2see() {
		return this.want2see;
	}
	public void setWant2see(boolean want2see) {
		this.want2see = want2see;
	}
	public List<String> getDirectors() {
		return directors;
	}
	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}
	public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}	
}
