/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : AddFilm.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Permite introducir el t’tulo, la descripci—n, la calificaci—n y
si el usuario ha visto/ho quiere ver la pel’cula.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

public class AddFilm extends Activity {

	public ImageButton home;
	public ImageView confirm;
	public EditText filmName;
	public EditText filmSumarize;
	public RatingBar filmQualification;
	public CheckBox filmNo;
	public CheckBox filmNoWant;
	public CheckBox filmYes;
	public CheckBox filmYesWant;
    public static Movie movie;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);
        
        home= (ImageButton) findViewById(R.id.imageButton1);
        home.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a = new Intent(v.getContext(), MainActivity.class);
		        v.getContext().startActivity(a);				
			}
		});
        
       
        movie=new Movie();
        filmName= (EditText) findViewById(R.id.editName);
        filmSumarize= (EditText) findViewById(R.id.editResume);
        filmQualification= (RatingBar) findViewById(R.id.ratingBar1);
        filmNo= (CheckBox) findViewById(R.id.CheckNo);
        filmYes= (CheckBox) findViewById(R.id.checkYes);
        filmNoWant= (CheckBox) findViewById(R.id.CheckNoWant);
        filmYesWant= (CheckBox) findViewById(R.id.CheckYesWant);
        
        
        if(filmNo.isChecked() && !filmYes.isChecked()){
        	movie.setSee(false);
        }
        else {
        	movie.setSee(true);
        }
        
        if(filmNoWant.isChecked() && !filmYesWant.isChecked()){
        	movie.setWant2see(false);
        }
        else {
        	movie.setWant2see(true);
        }
        
        confirm= (ImageView) findViewById(R.id.imageView3);
        confirm.setOnClickListener( new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		
        		if((filmNo.isChecked() && filmYes.isChecked()) || (filmNoWant.isChecked() && filmYesWant.isChecked())){
        			alertbox1();
        		}
        		else if(filmName.getText().length() == 0 || filmSumarize.getText().length() == 0){
        			alertbox2();
        		}
        		else{
        			movie.setName(filmName.getText().toString());
        			movie.setSummary(filmSumarize.getText().toString());
        			movie.setQualification(filmQualification.getRating());
        			Intent a = new Intent(v.getContext(), AddFilmCont.class);
        			v.getContext().startActivity(a);	
        		}
			}
        	
        });
        
    }
    
    public void alertbox1() 
    {
    	Builder alert = new AlertDialog.Builder(AddFilm.this);
        alert.setTitle(R.string.alert);
        alert.setMessage(R.string.alert_msg1);
        alert.setPositiveButton(R.string.alert_button, new DialogInterface.OnClickListener()
        {
             public void onClick(DialogInterface dialog, int whichButton)
             {
                dialog.cancel();
             }
        });
        AlertDialog alert1 = alert.create();
        alert1.show();
    }
    
    public void alertbox2() 
    {
    	Builder alert = new AlertDialog.Builder(AddFilm.this);
        alert.setTitle(R.string.alert);
        alert.setMessage(R.string.alert_msg2);
        alert.setPositiveButton(R.string.alert_button, new DialogInterface.OnClickListener()
        {
             public void onClick(DialogInterface dialog, int whichButton)
             {
                dialog.cancel();
             }
        });
        AlertDialog alert1 = alert.create();
        alert1.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_film, menu);
        return true;
    }

}
