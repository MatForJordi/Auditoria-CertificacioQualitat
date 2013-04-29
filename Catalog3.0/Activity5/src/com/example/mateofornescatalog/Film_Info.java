/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : Film_Info.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Permite al usuario obtener la informaci—n sobre la pelicula
buscada.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Film_Info extends Activity {
	
	public TextView infoName;
	public TextView infoDesc;
	public RatingBar infoQual;
	public static Movie movie;
	public Spinner infoActors;
	public Spinner infoDirectors;
	public TextView infoGenre;
	public ImageButton home; 
	static final int MSG_SEND_MOVIE = 1;
	MovieMessageParceable inMovie;
	Bundle b;
	static final String UserName = "ActivityUser";	
	static final int MSG_VETE_MIERDA=3;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film__info);

        movie=SearchFilm.movie;

        infoName= (TextView) findViewById(R.id.infoMovieName);
        infoDesc= (TextView) findViewById(R.id.infoMovieDesc);
        infoQual= (RatingBar) findViewById(R.id.infoMovieQual);
        infoActors= (Spinner) findViewById(R.id.spinnerActors);
        infoDirectors= (Spinner) findViewById(R.id.spinnerDirectors);
        infoGenre= (TextView) findViewById(R.id.infoMovieGenre);
		
        infoName.setText(movie.getName());
        infoDesc.setText(movie.getSummary());
        infoQual.setRating(movie.getQualification());
        infoGenre.setText(movie.getGenre());
       
        ArrayList<String> objectsActors = (ArrayList<String>) movie.getActors();
        ArrayAdapter<String> adapterActors = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, objectsActors);
        infoActors.setAdapter(adapterActors);
        
        ArrayList<String> objectsDirectors = (ArrayList<String>) movie.getDirectors();
        ArrayAdapter<String> adapterDirectors = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, objectsDirectors);
        infoDirectors.setAdapter(adapterDirectors);
        
        home= (ImageButton) findViewById(R.id.imageButton1);
        home.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a = new Intent(v.getContext(), MainActivity.class);
		        v.getContext().startActivity(a);				
			}
		});        
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_film__info, menu);
        return true;
    }
}
