package com.example.tallergrafos;

import com.grafo.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;


public class grafoController {

    @FXML
    Canvas canva;


    public void change(Canvas canva) {
        this.canva = canva;
    }
}
