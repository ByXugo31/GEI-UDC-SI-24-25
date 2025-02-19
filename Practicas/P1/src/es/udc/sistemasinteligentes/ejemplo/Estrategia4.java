package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;
import es.udc.sistemasinteligentes.Nodo.Nodo;
import es.udc.sistemasinteligentes.Nodo.NodoHijo;
import es.udc.sistemasinteligentes.Nodo.NodoInicial;

import java.util.ArrayList;
import java.util.List;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }


    private boolean nodoExplorado(Nodo n, ArrayList<Nodo> explorados){
        for (Nodo e: explorados)
            if (e.estadosIguales(n)) return true;
        return false;
    }

    private List<Nodo> reconstruyeSol(Nodo n){
        ArrayList<Nodo> sol = new ArrayList<>();

        while (n.getPadre() != null){
            sol.add(n);
            n  = n.getPadre();
        }
        sol.add(n);
        return sol.reversed();
    }

    @Override
    public List<Nodo> soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> explorados = new ArrayList<>();
        Nodo actual = new NodoInicial(p.getEstadoInicial());
        explorados.add(actual);
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + p.getEstadoInicial());

        while (!p.esMeta(actual.getEstado())){
            System.out.println((i++) + " - " + actual.getEstado() + " no es meta");
            Accion[] accionesDisponibles = p.acciones(actual.getEstado());
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Nodo nodo = new NodoHijo(p.result(actual.getEstado(), acc), acc, actual);
                System.out.println((i++) + " - RESULT(" + actual.getEstado() + ","+ acc + ")=" + nodo.getEstado());
                if (!nodoExplorado(nodo, explorados)) {
                    actual = nodo;
                    System.out.println((i++) + " - " + nodo.getEstado() + " NO explorado");
                    explorados.add(nodo);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + actual.getEstado());
                    break;
                }
                else
                    System.out.println((i++) + " - " + nodo.getEstado() + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + actual);
        return reconstruyeSol(actual);
    }
}
