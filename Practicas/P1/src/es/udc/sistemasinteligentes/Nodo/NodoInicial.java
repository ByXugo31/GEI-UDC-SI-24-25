package es.udc.sistemasinteligentes.Nodo;

import es.udc.sistemasinteligentes.Estado;

public class NodoInicial extends Nodo{
    public NodoInicial(Estado estado){
        super();
        setEstado(estado);
    }

    @Override
    public String toString(){
        return "{NODO INICIAL," + getEstado() + "}";
    }
}
