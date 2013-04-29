/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : AddFilmCont.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Permite introducir los actores, los directores, seleccionar el 
genero y salvar la pel’cula en el catalogo.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddFilmCont extends Activity {
	
	public ImageButton home;
	public ImageView confirm;
	public Context context;
	public CharSequence text;
	public int duration;
	public EditText actors;
	public EditText directors;
	public Spinner genre;
	public Button addActors;
	public Button addDirectors;
	public Movie movie;
	public static boolean mIsBound = false;
	public MovieCatalogService myService;
	public boolean mBounded;
	static final String UserName = "ActivityUser";	
    static Messenger mService = null;

   
    private ServiceConnection mConnection = new ServiceConnection() 
    {  	
    	public void onServiceConnected(ComponentName className,IBinder service) 
    	{
    		Log.e("ServiceBroadcastClientActivity", "onServiceConnected::Serviced connected");
    		mService = new Messenger(service);
    		Toast.makeText(AddFilmCont.this, "servicio conectado",Toast.LENGTH_SHORT).show();
    	}
    	
    	public void onServiceDisconnected(ComponentName className) 
    	{
    		mService = null;
    		Toast.makeText(AddFilmCont.this, "servicio desconectado",Toast.LENGTH_SHORT).show();
    	}
    };  
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film_cont);
        movie=AddFilm.movie;
       
        context = getApplicationContext();
        text=getString(R.string.toast_addition);
        actors= (EditText) findViewById(R.id.editTextActors);
        directors=(EditText) findViewById(R.id.editTextDirectors);
        genre=(Spinner) findViewById(R.id.spinner1);
        addActors= (Button) findViewById(R.id.button1);
        addDirectors=(Button) findViewById(R.id.button2);
         
        addActors.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				movie.getActors().add(actors.getText().toString());
				actors.setText("");
			}
		});
        
        addDirectors.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				movie.getDirectors().add(directors.getText().toString());
				directors.setText("");
			}
		});
        
        
        home= (ImageButton) findViewById(R.id.imageButton1);
        home.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a = new Intent(v.getContext(), MainActivity.class);
		        v.getContext().startActivity(a);				
			}
		});
        
        confirm= (ImageView) findViewById(R.id.imageView3);
        confirm.setOnClickListener( new OnClickListener() {
        	@Override
			public void onClick(View v) {      	
        		Location MyLocation = new Location("OnClick");
        		MovieMessageParceable fmsg = new MovieMessageParceable(0,UserName,MyLocation,UserName+" Send.",movie.getName(),movie);
        		Bundle b = new Bundle();
				b.putParcelable("MovieMessageParcelable",fmsg);
				Message msg = Message.obtain(null, MovieCatalogService.MSG_SAVE_MOVIE, 0, 0);
				msg.setData(b);
				try {
					mService.send(msg);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				unbindService(mConnection);
				mConnection.onServiceDisconnected(getComponentName());
        		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
				Intent a = new Intent(v.getContext(), MainActivity.class);
		        v.getContext().startActivity(a);	
        		}
        });
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.genre_array , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);
        genre.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				movie.setGenre(parent.getItemAtPosition(position).toString());
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}  
    	});
    }
   
    @Override
    public void onStart(){
        super.onStart();
        doBindService();
    }
 
    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }
   
    void doBindService() {
    	try {
    		bindService(new Intent(this, 
    				MovieCatalogService.class), mConnection, Context.BIND_AUTO_CREATE);
    		
    	}
        catch (Exception e) {
            e.printStackTrace(); 
        }
        mIsBound = true;
    }
    
    void doUnbindService() {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_film_cont, menu);
        return true;
    }    
}
