package com.wyble.nuevita.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

public class Handler_sqlite  extends SQLiteOpenHelper{

      public Handler_sqlite(Context ctx)
      {
          super(ctx, "procesAgro", null, 1);
      }

    @Override
    public  void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE Convocatorias ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," + "idTabla TEXT, usuario_id TEXT, descripcion TEXT, urlConvocatoria TEXT, descripcionLarga TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version_ant, int version_nue)
    {
        db.execSQL("DROP TABLE IF EXIST Convocatorias");
        onCreate(db);
    }

    public void insertarReg(String idTabla, String usuario, String descripcion, String urlConvocatoria, String descripcionLarga)
    {
        ContentValues valores = new ContentValues();
        valores.put("idtabla", idTabla);
        valores.put("usuario_id", usuario);
        valores.put("descripcion", descripcion);
        valores.put("urlConvocatoria", urlConvocatoria);
        valores.put("descripcionLarga", descripcionLarga);
        this.getWritableDatabase().insert("Convocatorias",null, valores);
    }

    public Cursor findReg(String idTabla)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT idTabla FROM Convocatorias WHERE idTabla =" + idTabla, null);


        return result;
    }

    public void getData(String idTabla)
    {
        SQLiteDatabase db = this.getWritableDatabase();

       // SELECT idTabla FROM Convocatorias WHERE idTabla =" + idTabla, null
    }


    public String leer()
    {
        String result = "";
        String columnas[] = {_ID,"idTabla","usuario_id","descripcion","urlConvocatoria","descripcionLarga"};
        Cursor c = this.getReadableDatabase().query("Convocatorias", columnas, null, null, null, null, null);

        int id, idt, iu, ip, iurl, idl;
        id = c.getColumnIndex(_ID);
        iu = c.getColumnIndex("usuario_id");
        idt = c.getColumnIndex("idTabla");
        ip = c.getColumnIndex("descripcion");
        iurl = c.getColumnIndex("urlConvocatoria");
        idl = c.getColumnIndex("descripcionLarga");

        c.moveToLast();

        result = c.getString(idt)+" "+c.getString(iu)+" "+c.getString(ip)+" "+c.getString(iurl) +" "+c.getString(idl) + "\n";

        return result;
    }

    public void abrir()
    {
        this.getWritableDatabase();
    }

    public void cerrar()
    {
        this.close();
    }
}
