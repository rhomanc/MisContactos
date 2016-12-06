package com.turrusoft.miscontactos.db;

import android.content.ContentValues;
import android.content.Context;

import com.turrusoft.miscontactos.R;
import com.turrusoft.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by CFE on 23/11/2016.
 */

public class ConstructorContactos {

    private static final int LIKE = 1 ;
    private Context context;
    public ConstructorContactos(Context context) {
        this.context=context;
    }

    public ArrayList<Contacto> obtenerDatos(){
        /*ArrayList<Contacto> contactos=new ArrayList<Contacto>();
        contactos.add(new Contacto(R.drawable.paletas_8,"Román Turrubiates","4423539105","roman.turrubiates@gmail.com", 5));
        contactos.add(new Contacto(R.drawable.hongo,"Anahí Salgado","5555776655","anahi@gmail.com", 3));
        contactos.add(new Contacto(R.drawable.rayito,"Juan Camaney","4421234567","jcamaney@hotmail.com", 8));
        contactos.add(new Contacto(R.drawable.banana,"Pedro Sánchez","5555889900","pedrito@gmail.com", 9));
        return contactos;*/
        BaseDatos db = new BaseDatos(context);
        insertarTresContactos(db);
        return db.obtenerTodosLosContactos();
    }

    public void insertarTresContactos(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE,"Román Turrubiates");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO,"4423539105");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL,"roman.turrubiates@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO,R.drawable.paletas_8);
        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE,"Anahí Salgado");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO,"5555776655");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL,"anahi@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO,R.drawable.hongo);
        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE,"Juan Camaney");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO,"4421234567");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL,"jcamaney@hotmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO,R.drawable.rayito);
        db.insertarContacto(contentValues);
    }

    public void darLikeContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO, contacto.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);
    }

    public int obtenerLikesContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesContacto(contacto);
    }
}
