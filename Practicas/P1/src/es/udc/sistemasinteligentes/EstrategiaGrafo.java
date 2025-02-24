package es.udc.sistemasinteligentes;

import es.udc.sistemasinteligentes.Nodo.Nodo;
import es.udc.sistemasinteligentes.Nodo.NodoHijo;
import es.udc.sistemasinteligentes.Nodo.NodoInicial;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//AKA BUSQUEDA EN PROFUNDIDAD SEGUN EL ENUNCIADO
//LOS ESTADOS PREVIAMENTE VISITADOS PUEDEN TENER UN CAMINO QUE LLEGUE A META
public class EstrategiaGrafo implements EstrategiaBusqueda {

    public EstrategiaGrafo() {
    }


    private boolean nodoExplorado(Nodo n, ArrayList<Nodo> explorados){
        for (Nodo e: explorados)
            if (e.estadosIguales(n)) return true;
        return false;
    }

    private boolean nodoEnFrontera(Nodo n, Stack<Nodo> explorados) {
        for (Nodo e : explorados)
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

    private ArrayList<Nodo> getSucesores(Nodo n, Accion[] acciones, ProblemaBusqueda p){
        ArrayList <Nodo> hijos = new ArrayList<>();
        for (Accion acc: acciones){
            Nodo nodo = new NodoHijo(p.result(n.getEstado(), acc), acc, n);
            hijos.add(nodo);
        }
        return hijos;
    }


    @Override
    public List<Nodo> soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> explorados = new ArrayList<>();
        Stack<Nodo> frontera = new Stack<>();
        Nodo actual = new NodoInicial(p.getEstadoInicial());
        frontera.add(actual);
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + p.getEstadoInicial());

        while (!p.esMeta(actual.getEstado())){
            if(frontera.empty()) throw new RuntimeException("FRONTERA VACIA");
            actual = frontera.pop();
            explorados.add(actual);
            System.out.println((i++) + " - " + actual.getEstado() + " no es meta");
            Accion[] accionesDisponibles = p.acciones(actual.getEstado());

            for (Nodo hijo : getSucesores(actual,accionesDisponibles,p)){
                if(p.esMeta(hijo.getEstado())) reconstruyeSol(hijo);
                if(!nodoEnFrontera(hijo,frontera) && !nodoExplorado(hijo,explorados))
                    frontera.push(hijo);
            }
        }
        System.out.println((i++) + " - FIN - " + actual);
        return reconstruyeSol(actual);
    }
}