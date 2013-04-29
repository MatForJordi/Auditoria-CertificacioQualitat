/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : SearchFilm.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Permite al usuario la bœsqueda de una pel’cula introduciendo su
t’tulo.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFilm extends Activity{
	static final int MSG_VETE_MIERDA=3;
	public ImageView confirm;
	public ImageButton home;
	public EditText movieName;
	public TextView info;
	public static String mName;
	public static Movie movie=null; 
	MovieMessageParceable inMessage;
   
	public static boolean mIsBound = false;
	public MovieCatalogService myService;
	public boolean mBounded;
	static final String UserName = "ActivityUser";	
    static Messenger mService = null;

    final  Messenger mMessenger = new Messenger(new IncomingHandler());
	 private ServiceConnection mConnection = new ServiceConnection() 
	    {
	    	
	    	public void onServiceConnected(ComponentName className,IBinder service) 
	    	{
	    		Log.e("ServiceBroadcastClientActivity", "onServiceConnected::Serviced connected");
	    		mService = new Messenger(service);
	    		Toast.makeText(SearchFilm.this, "servicio conectado",Toast.LENGTH_SHORT).show();
	    	}

	    	
	    	public void onServiceDisconnected(ComponentName className) 
	    	{

	    		mService = null;
	    		Toast.makeText(SearchFilm.this, "servicio desconectado",Toast.LENGTH_SHORT).show();
	    	}
	    };  
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_search_film);
        
        movieName=(EditText) findViewById(R.id.editName);
        
        confirm= (ImageView) findViewById(R.id.imageView3);
      
        
        

        confirm.setOnClickListener( new OnClickListener() {
        	@Override
			public void onClick(View v) {
                mName=movieName.getText().toString();
                
                MovieMessageParceable fmsg = new MovieMessageParceable(0,UserName,null,UserName+" Send.",SearchFilm.mName,null);
        		Bundle b = new Bundle();
        		b.putParcelable("MovieMessageParcelable",fmsg);
        		Message msg = Message.obtain(null, MovieCatalogService.MSG_SEND_MOVIE, 0, 0);
        		msg.setData(b);
        		msg.replyTo=mMessenger;
        		try {
        			mService.send(msg);
        		} catch (RemoteException e) {
        			e.printStackTrace();
        		}
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
    }
	
	class IncomingHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
    			case MovieCatalogService.MSG_VETE_MIERDA: 				
    				Bundle b = msg.getData();
    				b.setClassLoader(MovieMessageParceable.class.getClassLoader());
    				MovieMessageParceable fmsg  =  (MovieMessageParceable) b.getParcelable("MovieMessageParcelable");
    				movie=fmsg.getMovie();
    				
    				Intent a = new Intent(SearchFilm.this, Film_Info.class);
    				SearchFilm.this.startActivity(a);
    				break;
    			default:
    				super.handleMessage(msg);
    		}
    	}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_film, menu);
        return true;
    }
    
    public void alertbox() 
    {
    	Builder alert = new AlertDialog.Builder(SearchFilm.this);
        alert.setTitle(R.string.alert);
        alert.setMessage(R.string.alert_msg3);
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
}
