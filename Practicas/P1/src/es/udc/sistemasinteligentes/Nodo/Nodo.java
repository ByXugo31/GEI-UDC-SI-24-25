package es.udc.sistemasinteligentes.Nodo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public abstract class Nodo {

    private Estado e;
    private Accion a;
    private Nodo p;

    public Nodo(){
        this.e = null;
        this.a = null;
        this.p = null;
    }


    public Estado getEstado() {return e;}

    public Accion getAccion() {return a;}

    public Nodo getPadre() {return p;}

    public void setEstado(Estado e) {this.e = e;}

    public void setAccion(Accion a) {this.a = a;}

    public void setPadre(Nodo p) {this.p = p;}

    public boolean estadosIguales(Nodo n){
        return n.getEstado().toString().equals(e.toString());
    }

    public boolean accionesIguales(Nodo n){
        return n.getAccion().toString().equals(e.toString());
    }


}
