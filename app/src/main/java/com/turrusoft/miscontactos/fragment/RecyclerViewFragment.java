package com.turrusoft.miscontactos.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turrusoft.miscontactos.R;
import com.turrusoft.miscontactos.adapter.ContactoAdaptador;
import com.turrusoft.miscontactos.pojo.Contacto;
import com.turrusoft.miscontactos.presentador.IRecyclerViewFragmentPresenter;
import com.turrusoft.miscontactos.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by CFE on 16/11/2016.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{
    ArrayList<Contacto> contactos;
    private RecyclerView rvContactos;
    private IRecyclerViewFragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

        rvContactos = (RecyclerView) v.findViewById(R.id.rvContactos);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }
    /*
    public void inicializarListaContactos() {
        contactos=new ArrayList<Contacto>();
        contactos.add(new Contacto(R.drawable.paletas_8,"Román Turrubiates","4423539105","roman.turrubiates@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.hongo,"Anahí Salgado","5555776655","anahi@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.rayito,"Juan Camaney","4421234567","jcamaney@hotmail.com", likes));
        contactos.add(new Contacto(R.drawable.banana,"Pedro Sánchez","5555889900","pedrito@gmail.com", likes));
    }
    */



    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvContactos.setLayoutManager(llm);
    }

    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(ContactoAdaptador adaptador) {
        rvContactos.setAdapter(adaptador);

    }
}
