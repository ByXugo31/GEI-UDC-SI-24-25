package es.udc.sistemasinteligentes;

import es.udc.sistemasinteligentes.Nodo.Nodo;

import java.util.ArrayList;
import java.util.List;

public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @return Estado meta obtenido
     */
    public abstract List<Nodo> soluciona(ProblemaBusqueda p) throws Exception;
}
