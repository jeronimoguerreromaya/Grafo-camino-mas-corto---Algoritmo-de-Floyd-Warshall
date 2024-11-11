package com.grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Grafo {
    private Map<Nodo, List<Arista>> adyacencias;
    private List<Nodo> nodos;
    private int idCounter;

    public Grafo() {
        adyacencias = new HashMap<>();
        nodos = new ArrayList<>();
        idCounter = 0;
    }

    public void agregarNodo(String nombreNodo) {
        Nodo nodo = new Nodo(idCounter++,nombreNodo);
        if (!adyacencias.containsKey(nodo)) {
            adyacencias.put(nodo, new ArrayList<>());
            nodos.add(nodo);
        }
    }

    public void agregarArista(String origen, String destino, int peso) {
        Nodo nodoOrigen = buscarNodo(origen);
        Nodo nodoDestino = buscarNodo(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            Arista arista = new Arista(nodoOrigen, nodoDestino, peso);
            adyacencias.get(nodoOrigen).add(arista);
        } else {
            System.out.println("Uno o ambos nodos no existen: " + origen + ", " + destino);
        }
    }

    private Nodo buscarNodo(String nombre) {
        for (Nodo nodo : adyacencias.keySet()) {
            if (nodo.getNombre().equals(nombre)) {
                return nodo;
            }
        }
        return null;
    }
    public void imprimirGrafo() {
        for (Nodo nodo : adyacencias.keySet()) {
            System.out.println("Nodo " + nodo + " tiene conexiones:");
            for (Arista arista : adyacencias.get(nodo)) {
                System.out.println("  " + arista);
            }
        }
    }


    private int[][]  floydWarshall() {
        int V = nodos.size();
        int[][] dist = new int[V][V];
        int[][] path = new int[V][V];

        // Inicializar la matriz de distancias y caminos
        for (int i = 0; i < V; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            //Arrays.fill(path[i], -1);
            dist[i][i] = 0;
            path[i][i] = 0;
        }

        // Llenar la matriz de distancias inicial con las aristas del grafo
        for (int i = 0; i < V; i++) {
            Nodo nodoOrigen = nodos.get(i);
            for (Arista arista : adyacencias.get(nodoOrigen)) {
                int j = nodos.indexOf(arista.getDestino());
                dist[i][j] = arista.getPeso();
                path[i][j] = 0;
            }
        }

        // Algoritmo de Floyd-Warshall
        for (int k = 0 ; k < V; k++) {

            System.out.println("\nIteraccion "+ (k+1) + "\n");
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k+1;
                    }
                }
            }
            System.out.println("\nMatriz distancia\n");
            imprimirMatriz(dist);
            System.out.println("\nMatriz camino\n");
            imprimirMatriz(path);
        }
       return path;
    }
    private int obtenerIdPorNombre(String nombreNodo) {
        for (Nodo nodo : nodos) {
            System.out.println(nodo.getNombre()+" "+nombreNodo);
            if (nodo.getNombre().equals(nombreNodo)) {
                return nodo.getId();
            }
        }
        return -1;
    }

    public StringBuilder imprimirCamino(String origen, String destino){
       //Buscar la  id por nombres de los nodos
        int origenId = obtenerIdPorNombre(origen);
        int destinoId = obtenerIdPorNombre(destino);
        //MC
        int[][] path = floydWarshall();

       //Guarda el id de los nodos
        ArrayList<Integer> recorrido = new ArrayList<>();

        StringBuilder ruta = imprimirCaminoRecursivo(path,origenId,destinoId,recorrido);
        return ruta;
    }
    //Buscar ruta mas corta
    private StringBuilder imprimirCaminoRecursivo(int[][] path, int origen, int destino, ArrayList<Integer> recorrido) {
        if (path[origen][destino] == 0) {
            recorrido.add(origen);
            recorrido.add(destino);

            System.out.println("Ruta optima :");

            StringBuilder ruta = imprimirRutaPorIds(recorrido);
            return ruta;
        }
        int intermedio = path[origen][destino];

        recorrido.add(origen);

        return imprimirCaminoRecursivo(path, intermedio-1, destino,recorrido);

    }
    //Metodo imprime  los nodos de un arrayList
    private StringBuilder imprimirRutaPorIds(ArrayList<Integer> recorrido) {
        StringBuilder ruta = new StringBuilder();
        for (int i = 0; i < recorrido.size(); i++) {
            Nodo nodo = nodos.get(recorrido.get(i));
            if (i > 0) {
                ruta.append(" -> ");
            }
            ruta.append(nodo.getNombre());
        }
        System.out.println(ruta.toString());
        return  ruta;
    }

    //Imprimir matriz
    private void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == Integer.MAX_VALUE / 2) {
                    System.out.print("INF ");
                } else {
                    System.out.print(matriz[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }
    public ArrayList<String> obtenerNombresNodos() {
        ArrayList<String> nombres = new ArrayList<>();
        for (Nodo nodo : nodos) {
            nombres.add(nodo.getNombre());
        }
        return nombres;
    }

    // Método auxiliar para obtener la lista de nodos (si lo necesitas en otras partes de tu código)
    public List<Nodo> getNodos() {
        return nodos;
    }
    public int getIdCounter() {
        return idCounter;
    }


    public static class Arista {
        private Nodo origen;
        private Nodo destino;
        private int peso;

        public Arista(Nodo origen, Nodo destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        public Nodo getOrigen() {
            return origen;
        }

        public Nodo getDestino() {
            return destino;
        }

        public int getPeso() {
            return peso;
        }

        @Override
        public String toString() {
            return origen + " -> " + destino + " (" + peso + ")";
        }
    }

    public static class Nodo implements Comparable<Nodo> {
        private int id;
        private String nombre;

        public Nodo(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public String toString() {
            return nombre + " (ID: " + id + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Nodo nodo = (Nodo) obj;
            return id == nodo.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public int compareTo(Nodo otro) {
            // Ordenar por ID
            return Integer.compare(this.id, otro.id);
        }
    }
}

