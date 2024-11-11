package com.example.tallergrafos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import com.grafo.*;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class grafoController {

    @FXML
    private Button verRuta_Btt;

    @FXML
    private ComboBox<String> origen_ComboBox;

    @FXML
    private ComboBox<String> destino_ComboBox;

    @FXML
    private Label origen_lbl;

    @FXML
    private Label destino_lbl;

    @FXML
    private TextField resultado_txt;

    private Grafo grafo;
    @FXML
    private void initialize() {
        this.grafo = new Grafo();

        grafo = iniciarGrafoPrueba1();

        // Llenar combox con los nodos de los grafos

        ArrayList<String> listNodos = grafo.obtenerNombresNodos();
        origen_ComboBox.getItems().addAll(listNodos);
        destino_ComboBox.getItems().addAll(listNodos);
    }

    @FXML
    private void verRuta_BttAction(ActionEvent event) {
        // Obtiene el valor seleccionado en los ComboBox de origen y destino
        String origenSeleccionado = origen_ComboBox.getValue();
        String destinoSeleccionado = destino_ComboBox.getValue();



        StringBuilder ruta = this.grafo.imprimirCamino(origenSeleccionado,destinoSeleccionado);

        resultado_txt.setText(ruta.toString());

    }


    @FXML
    private String origen_comboBoxAction(ActionEvent event) {
        String origenSeleccionado = origen_ComboBox.getValue(); // Obtiene el valor seleccionado
        if (origenSeleccionado != null) {
            System.out.println("Origen seleccionado: " + origenSeleccionado);
            // Aquí puedes agregar lógica adicional usando el origen seleccionado
        }
        return origenSeleccionado;
    }

    @FXML
    private String destino_comboBoxAction(ActionEvent event) {
        String destinoSeleccionado = destino_ComboBox.getValue(); // Obtiene el valor seleccionado
        if (destinoSeleccionado != null) {
            System.out.println("Destino seleccionado: " + destinoSeleccionado);
            // Aquí puedes agregar lógica adicional usando el destino seleccionado
        }
        return destinoSeleccionado;
    }

    public Grafo iniciarGrafoPrueba1(){
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

        return grafo;
    }

}
