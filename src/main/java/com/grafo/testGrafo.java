package com.grafo;

import java.util.ArrayList;

public class testGrafo {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        //Crear grafo
        grafo.agregarNodo("punto a");
        grafo.agregarNodo("punto b");
        grafo.agregarNodo("punto c");
        grafo.agregarNodo("punto d");


        grafo.agregarArista("punto a", "punto d", 1);
        grafo.agregarArista("punto b", "punto a", 3);
        grafo.agregarArista("punto b", "punto c", 1);
        grafo.agregarArista("punto c", "punto a", 5);
        grafo.agregarArista("punto c", "punto b", 1);
        grafo.agregarArista("punto d", "punto a", 8);
        grafo.agregarArista("punto d", "punto b", 2);
        grafo.agregarArista("punto d", "punto c", 4);

        ArrayList<String> st = grafo.obtenerNombresNodos();

        for(String s : st){
            System.out.println(s);
        }

    }





}
