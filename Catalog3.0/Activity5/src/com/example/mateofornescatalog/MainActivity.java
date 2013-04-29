/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : MainActivity.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Menu inicial que permite al usuario seleccionar la acci—n a 
realizar. Adem‡s inicia una notificaci—n status bar y define un
menu de opciones.
--------------------------------------------------------------- */
package com.example.mateofornescatalog;

import java.util.ArrayList;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    private GridAdapter gridAdapt;
    private ArrayList<String> mItems = new ArrayList<String>();
	private PicAdapter imgAdapt;
	
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
    		Toast.makeText(MainActivity.this, "servicio conectado",Toast.LENGTH_SHORT).show();
    	}
    	public void onServiceDisconnected(ComponentName className) 
    	{

    		mService = null;
    		Toast.makeText(MainActivity.this, "servicio desconectado",Toast.LENGTH_SHORT).show();
    	}
    };  

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*Codigo para definir la galeria de imagenes*/
        Gallery picGallery = (Gallery) findViewById(R.id.gallery1);
        imgAdapt = new PicAdapter(this);
        picGallery.setAdapter(imgAdapt);
        
        /*Codigo para definir el menu Grid*/
        for (int i = 0; i<8; i++) {
        	mItems.add(Integer.toString(i));
        	}
        gridAdapt = new GridAdapter(this, mItems);	
        GridView g = (GridView) findViewById(R.id.gridView1);
        g.setAdapter(gridAdapt);
        g.setOnItemClickListener(new MyOnItemClickListener() {
		});
        
        /*Codigo para crear las notificaciones del tipo 
         status bar y mostrarlas. */
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("My Cataleg")
                .setContentText("runnig!");
        
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int mId=0;
        mNotificationManager.notify(mId, mBuilder.build());
            
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
    	
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.layout.menu, menu);
        return true;
    }
    
	public boolean onOptionsItemSelected(MenuItem item)
    {
		Message msg;
		Bundle b;
		/*Menu de opciones, cuando se selecciona un item en el menu
    	  se lanza un aviso en forma de dialogo.*/
    	//Context context = getApplicationContext();
        switch (item.getItemId())
        {
        case R.id.menu_save:
        	b = new Bundle();
			msg = Message.obtain(null, MovieCatalogService.MSG_SAVE_CATALOG, 0, 0);
			msg.setData(b);
			try {
				mService.send(msg);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
            
            return true;
            
 
        case R.id.menu_paste:
         	b = new Bundle();
			msg = Message.obtain(null, MovieCatalogService.MSG_RESTORE_CATALOG, 0, 0);
			msg.setData(b);
			try {
				mService.send(msg);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
 
        	return true;
 
        case R.id.menu_pdf:
            Toast.makeText(MainActivity.this, "Pdf is Selected", Toast.LENGTH_SHORT).show();
            b = new Bundle();
			msg = Message.obtain(null, MovieCatalogService.MSG_RESTORE_CATALOG_BD, 0, 0);
			msg.setData(b);
			try {
				mService.send(msg);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
            return true;
 
        case R.id.menu_screenshot:
            Toast.makeText(MainActivity.this, "Screenshot is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.menu_searchgoogle:
            Toast.makeText(MainActivity.this, "Search in google is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.menu_about:
            Toast.makeText(MainActivity.this, "About is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
}



