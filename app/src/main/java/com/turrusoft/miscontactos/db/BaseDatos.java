package com.turrusoft.miscontactos.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.turrusoft.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by CFE on 23/11/2016.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_CONTACTS + "("
                + ConstantesBaseDatos.TABLE_CONTACTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE + " TEXT, "
                + ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO + " TEXT, "
                + ConstantesBaseDatos.TABLE_CONTACTS_EMAIL + " TEXT, "
                + ConstantesBaseDatos.TABLE_CONTACTS_FOTO + " INTEGER "
                + ")";
        String queryCrearTablaLikesContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT + "("
                + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + " INTEGER, "
                + ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES + " INTEGER, "
                + "FOREIGN KEY ("+ ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO +") "
                + "REFERENCES " + ConstantesBaseDatos.TABLE_CONTACTS + "(" + ConstantesBaseDatos.TABLE_CONTACTS_ID + ")"
                + ")";
        db.execSQL(queryCrearTablaContacto);
        db.execSQL(queryCrearTablaLikesContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_CONTACT);
        onCreate(db);
    }

    public ArrayList<Contacto> obtenerTodosLosContactos(){
        ArrayList<Contacto> contactos = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        while (registros.moveToNext()) {
            Contacto contactoactual = new Contacto();
            contactoactual.setId(registros.getInt(0));
            contactoactual.setNombre(registros.getString(1));
            contactoactual.setTelefono(registros.getString(2));
            contactoactual.setEmail(registros.getString(3));
            contactoactual.setFoto(registros.getInt(4));
            contactos.add(contactoactual);
            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES + ") as likes"
                              + " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT + " WHERE "
                              + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + contactoactual.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                contactoactual.setLikes(registrosLikes.getInt(0));
            }else{
                contactoactual.setLikes(0);
            }
        }
        db.close();
        return contactos;
    }

    public void insertarContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_CONTACTS, null,contentValues);
        db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_CONTACT, null, contentValues);
        db.close();
    }

    public int obtenerLikesContacto(Contacto contacto){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES+")"
                + " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT
                + " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO
                + "=" + contacto.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();

        return likes;
    }

}
