package es.udc.sistemasinteligentes.Nodo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class NodoHijo extends Nodo{
    public NodoHijo(Estado estado, Accion accion, Nodo padre){
        setPadre(padre);
        setAccion(accion);
        setEstado(estado);
    }

    @Override
    public String toString(){
        return "{NODO,"  + getEstado() + "," + getAccion() + "}";
    }
}