/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : MyOnItemClickListener.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Clase que permite la implementaci—n del click en los botones 
del menœ
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyOnItemClickListener implements OnItemClickListener{

	public MyOnItemClickListener()  
	{  
	  
	}  
	
	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position, long l) {
		switch(position){
		case 0:
			Intent b = new Intent(v.getContext(), SearchFilm.class);
	    	v.getContext().startActivity(b);
	    break;
        case 1:
        	Intent a = new Intent(v.getContext(), AddFilm.class);
        	v.getContext().startActivity(a);
        break;
        case 2:
        	Intent c = new Intent(v.getContext(), DeleteFilm.class);
        	v.getContext().startActivity(c);
        break;
        case 3:
            Intent d = new Intent(v.getContext(), TopFilm.class);
            v.getContext().startActivity(d);
            break;
        case 4:
            Intent e = new Intent(v.getContext(), Films2See.class);
            v.getContext().startActivity(e);
            break;
        case 5:
        	Toast.makeText(v.getContext(),R.string.not_implemented, Toast.LENGTH_SHORT).show();
        	break;
        	
        case 6:
        	Toast.makeText(v.getContext(), R.string.not_implemented, Toast.LENGTH_SHORT).show();
        	break;
        	
        case 7:
        	Toast.makeText(v.getContext(), R.string.not_implemented, Toast.LENGTH_SHORT).show();
        	break;

    }
		
	}
}
