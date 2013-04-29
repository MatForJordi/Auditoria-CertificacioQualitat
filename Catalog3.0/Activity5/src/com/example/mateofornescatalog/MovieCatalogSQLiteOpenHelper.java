/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : MovieCatalogSQLiteOpenHelper.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Clase que permite la implementaci—n de la base de datos
sql.
--------------------------------------------------------------- */
package com.example.mateofornescatalog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieCatalogSQLiteOpenHelper extends SQLiteOpenHelper {
 
    public MovieCatalogSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

    String sqlCreate = "CREATE TABLE Catalog (name TEXT, description TEXT, qualification FLOAT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Catalog");
        db.execSQL(sqlCreate);
    }
} 

