package com.turrusoft.miscontactos.fragment;

import com.turrusoft.miscontactos.adapter.ContactoAdaptador;
import com.turrusoft.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by CFE on 22/11/2016.
 */

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);

    public void inicializarAdaptadorRV(ContactoAdaptador adaptador);

}
