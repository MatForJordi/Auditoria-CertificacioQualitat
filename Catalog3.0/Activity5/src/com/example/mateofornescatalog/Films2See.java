/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : Film2See.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Funci—n no implementada
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class Films2See extends Activity {
	public ImageButton home;
	public ToggleButton t;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films2_see);
        
        home= (ImageButton) findViewById(R.id.home_2see);
        home.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a = new Intent(v.getContext(), MainActivity.class);
		        v.getContext().startActivity(a);				
			}
		});
        
        
        
        
        
        t= (ToggleButton) findViewById(R.id.toggleButton1);
        t.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Uri uri = Uri.parse("http://www.youtube.com/watch?v=3CecaxAWueo");

				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				
			}
		});
			
		
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_films2_see, menu);
        return true;
    }
}
