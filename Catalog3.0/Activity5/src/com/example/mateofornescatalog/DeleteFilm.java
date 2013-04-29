/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : DeleteFilm.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Permite al usuario eliminar una pel’cula del catalogo.
--------------------------------------------------------------- */
package com.example.mateofornescatalog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteFilm extends Activity {
	public ImageView confirm;
	public ImageButton home;
	public EditText movieName;
	public TextView info;
	public String mName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_film);
        
        movieName=(EditText) findViewById(R.id.deleteName);
     
        confirm= (ImageView) findViewById(R.id.confirm_delete);
        confirm.setOnClickListener( new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		mName=movieName.getText().toString();
        		if(MovieCatalogService.movies.existFilm(mName)){
				MovieCatalogService.movies.deleteFilm(mName);
				Context context = getApplicationContext();
			    String text = getString(R.string.toast_erase);
			    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        		Intent a = new Intent(v.getContext(), MainActivity.class);
		        v.getContext().startActivity(a);
        		}
        	}
        });
        
        home= (ImageButton) findViewById(R.id.home_delete);
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
        getMenuInflater().inflate(R.menu.activity_delete_film, menu);
        return true;
    }
}
