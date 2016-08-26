package com.facci.proyectoslpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stefany on 26/08/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final String BASE_NOMBRE = "CNE_SLPL";
    public static final String TABLA_NOMBRE = "VOTANTES_SLPL";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO";
    public static final String COL_4 = "RECINTO ELECTORAL";
    public static final String COL_5 = "AÑO NACIMIENTO";


    public DBHelper(Context context) {
        super(context, BASE_NOMBRE , null, 1);
        SQLiteDatabase base = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase base) {
        base.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s TEXT, %s TEXT,%s INTEGER)",TABLA_NOMBRE,COL_2,COL_3,COL_4,COL_5));


    }

    @Override
    public void onUpgrade(SQLiteDatabase base, int i, int i1) {
        base.execSQL(String.format("DROP TABLE IF EXISTS %s",TABLA_NOMBRE));
        onCreate(base);

    }
    public boolean ingresar(String nombre, String apellido, String recintoelctoral,int añonacimiento){
        SQLiteDatabase base = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoelctoral);
        contentValues.put(COL_5,añonacimiento);
        long resultado = base.insert(TABLA_NOMBRE,null,contentValues);

        if(resultado == -1)
            return false;
        else
            return true;

    }

    public Cursor selectBuscarTodos(){
        SQLiteDatabase base = this.getWritableDatabase();
        Cursor res = base.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return  res;
    }


    public boolean modificar(String id,String nombre,String apellido,String recintoelectoral,int añonacimiento){

        SQLiteDatabase base = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoelectoral);
        contentValues.put(COL_5,añonacimiento);


        base.update(TABLA_NOMBRE,contentValues,"id = ?",new String[]{id});


        return true;
    }

    public Integer eliminar(String id){


        SQLiteDatabase base = this.getWritableDatabase();


        return base.delete(TABLA_NOMBRE,"id = ?",new String[]{id});

    }

}
