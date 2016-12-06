package com.turrusoft.miscontactos.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.turrusoft.miscontactos.db.ConstructorContactos;
import com.turrusoft.miscontactos.pojo.Contacto;
import com.turrusoft.miscontactos.DetalleContacto;
import com.turrusoft.miscontactos.R;

import java.util.ArrayList;

/**
 * Created by CFE on 07/11/2016.
 */

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {

    ArrayList<Contacto> contactos;
    Activity activity;

    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos=contactos;
        this.activity=activity;
    }

    //Infla el layout y lo pasa al viewholder para que obtenga los views
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false);
        return new ContactoViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final ContactoViewHolder contactoViewHolder, int position) {
        final Contacto contacto=contactos.get(position);
        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());
        contactoViewHolder.tvTelefonoCV.setText(contacto.getTelefono());
        contactoViewHolder.tvLikes.setText(String.valueOf(contacto.getLikes())+" Likes");
        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, contacto.getNombre(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleContacto.class);
                intent.putExtra("nombre",contacto.getNombre());
                intent.putExtra("telefono", contacto.getTelefono());
                intent.putExtra("email", contacto.getEmail());
                activity.startActivity(intent);
            }
        });
        contactoViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste Like a "+contacto.getNombre(),Toast.LENGTH_SHORT).show();
                ConstructorContactos constructorContactos = new ConstructorContactos(activity);
                constructorContactos.darLikeContacto(contacto);
                contactoViewHolder.tvLikes.setText(Integer.toString(constructorContactos.obtenerLikesContacto(contacto))+ " Likes");
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos en la lista de contactos
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNombreCV, tvTelefonoCV;
        private ImageButton btnLike;
        private TextView tvLikes;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            imgFoto         =(ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV      =(TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV    =(TextView) itemView.findViewById(R.id.tvTelefonoCV);
            btnLike         =(ImageButton)itemView.findViewById(R.id.btnLike);
            tvLikes         =(TextView) itemView.findViewById(R.id.tvLikes);
        }
    }
}
