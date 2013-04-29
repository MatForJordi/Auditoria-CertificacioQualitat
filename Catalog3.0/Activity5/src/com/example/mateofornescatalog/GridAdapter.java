/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : GridAdapter.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Clase que permite usar el patr—n adapter con la grid view.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter{
	
	private Context mContext;
	private ArrayList<String> mItems;
	public GridAdapter(Context c, ArrayList<String> items) {
	mContext = c;
	mItems = items;
	}
	@Override
	public int getCount() {
	return mItems.size();
	}
	@Override
	public Object getItem(int position) {
	return mItems.get(position);
	}
	@Override
	public long getItemId(int position) {
	return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	View v = convertView;
	if (v == null) {
	LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	v = li.inflate(R.layout.grid_item, null);
	TextView tv = (TextView)v.findViewById(R.id.grid_item_text);

	ImageView iv = (ImageView)v.findViewById(R.id.grid_item_image);
	switch (position) {
	case 0:
	tv.setText(R.string.search);
	iv.setImageResource(R.drawable.search_film);
	break;
	case 1:
    tv.setText(R.string.insert);
	iv.setImageResource(R.drawable.add_film);
	break;
	case 2:
    tv.setText(R.string.delete);	
	iv.setImageResource(R.drawable.remove_film);
	break;
	case 3:
	tv.setText(R.string.top10);	
	iv.setImageResource(R.drawable.top10);
	break;
	case 4:
		tv.setText(R.string.outstanding);	
		iv.setImageResource(R.drawable.tosee);
		break;
	case 5:
		tv.setText(R.string.update);	
		iv.setImageResource(R.drawable.update);
		break;
	case 6:
		tv.setText(R.string.save);	
		iv.setImageResource(R.drawable.news);
		break;
	case 7:
		tv.setText(R.string.charge);	
		iv.setImageResource(R.drawable.sync);
		break;
	}
	}
	return v;
	}
	}	

