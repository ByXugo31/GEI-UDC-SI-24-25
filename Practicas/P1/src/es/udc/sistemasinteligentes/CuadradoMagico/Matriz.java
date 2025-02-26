package es.udc.sistemasinteligentes.CuadradoMagico;

import java.util.Random;

public class Matriz {

    /// ATRIBUTOS ///

    private int filas;
    private int columnas;
    private int[][] matriz;
    private int numElementos;

    /// CONSTRUCTORES ///

    public Matriz(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        this.numElementos = 0;
        inicializarMatriz();
    }

    public Matriz(int matriz [][]){
        this.matriz = matriz;
        this.filas = matriz.length;
        this.columnas = columnasMtrz();
        this.numElementos = contarElementos();
    }

    public Matriz(int filas, int columnas, boolean generar){
        this.filas = filas;
        this.columnas = columnas;
        inicializarMatriz();
        generarMatrizAleatoria();
        this.numElementos = contarElementos();
    }

    /// GETTERS Y SETTERS ///

    public int getFilas(){return this.filas;}
    public int getColumnas(){return this.columnas;}
    public int[][] getMatriz(){return this.matriz;}

    public void setMatriz(int[][] matriz){
        this.matriz = matriz;
        this.filas = matriz.length;
        this.columnas = columnasMtrz();
        this.numElementos = contarElementos();
    }

    /// METODOS PRIVADOS ///

    private int columnasMtrz() {
        if (matriz.length > 0) return matriz[0].length;
        else return 0;
    }

    private boolean posicionLibre(int x, int y){return matriz[x][y] == -1;}

    private boolean exists(int element){
         for (int[] columna : matriz)
             for (int elemento : columna)
                 if(elemento == element) return true;
         return false;
    }

    private int contarElementos(){
        int num = 0;
        for (int[] columna : matriz)
            for (int elemento : columna)
                if(elemento != -1) num++;
        return num;
    }

    private int[] posicionVacia(){
        int[] posicion = null;
        posicion[0] = 0;
        posicion[1] = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (posicionLibre(i,j)) {
                    posicion[0] = i;
                    posicion[1] = j;
                    return posicion;
                }
            }
        }
        return posicion;
    }

    private boolean validNum(int num){return num > 0 && num <= filas*columnas;}

    private void inicializarMatriz(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = -1;
            }
        }
    }

    private boolean matrizLlena(){return numElementos == filas * columnas;}

    private int generarAleatorio(){
        Random random = new Random();
        int num = random.nextInt(filas * columnas + 2) - 1;
        if(num == 0 || exists(num)) return generarAleatorio();
        return num;
    }

    private int[] generarPosiciones(){
        Random random = new Random();
        int [] pos = null;
        pos[0] = random.nextInt(filas * columnas) - 1;
        pos[1] = random.nextInt(filas * columnas) - 1;
        if(!posicionLibre(pos[0],pos[1])) generarPosiciones();
        return pos;
    }

    private void llenarAleatorio(){
        int element = generarAleatorio();
        int [] posiciones = generarPosiciones();
        setOnPosition(posiciones[0],posiciones[1],element);
    }

    private void generarMatrizAleatoria(){
        Random random = new Random();
        int iteraciones = random.nextInt(filas * columnas) - 1;
        for (int i = 0; i < iteraciones; i++) llenarAleatorio();
    }
    /// METODOS PUBLICOS ///

    public int[] setOnFirstFree(int element){
        if (!validNum(element)) throw new IllegalArgumentException("EL NUMERO NO ES VALIDO");
        if (matrizLlena()) throw new ArrayIndexOutOfBoundsException("MATRIZ LLENA");
        if (exists(element)) throw new IllegalStateException("EL ELEMENTO YA EXISTE");
        int[] coordenadaVacia = posicionVacia();
        matriz[coordenadaVacia[0]][coordenadaVacia[1]] = element;
        return coordenadaVacia;
    }

    public int[] setOnPosition(int x, int y, int element){
        if (!validNum(element)) throw new IllegalArgumentException("EL NUMERO NO ES VALIDO");
        if (!posicionLibre(x,y)) throw new ArrayStoreException("POSICION OCUPADA");
        if (matrizLlena()) throw new ArrayIndexOutOfBoundsException("MATRIZ LLENA");
        if (exists(element)) throw new IllegalStateException("EL ELEMENTO YA EXISTE");
        int[] coord = null;
        coord[0] = x;
        coord[1] = y;
        matriz[x][y] = element;
        return coord;
    }

}
