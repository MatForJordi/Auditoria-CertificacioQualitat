/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : TopFilm.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Lista cutre de los nombres de pel’culas que contiene nuestro 
catalogo actual.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import java.util.Iterator;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TopFilm extends Activity {
	public ImageButton home;
	public TextView list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_film);
        
        home= (ImageButton) findViewById(R.id.home_top);
        home.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a = new Intent(v.getContext(), MainActivity.class);
		        v.getContext().startActivity(a);				
			}
		});
        
        list= (TextView) findViewById(R.id.listMovies);
        Iterator<String> myVeryOwnIterator  =MovieCatalogService.movies.getMovies().keySet().iterator();
        while(myVeryOwnIterator.hasNext()) {
            String key=(String)myVeryOwnIterator.next();
            Movie value=(Movie) MovieCatalogService.movies.getMovies().get(key);
            list.append("\n" + "------------------"+ "\n" +"Name: " +value.getName() + "\n"+ "------------------"+ "\n");
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_top_film, menu);
        return true;
    }
}
