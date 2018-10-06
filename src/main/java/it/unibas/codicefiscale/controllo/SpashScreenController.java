/*
    GNU GENERAL PUBLIC LICENSE
    Version 3, 29 June 2007

    Copyright (c) 2018 Vincenzo Palazzo vincenzopalazzo1996@gmail.com, Stefano Foti

    This file is part of my-code.

    my-code is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    my-code is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with my-code.  If not, see <http://www.gnu.org/licenses/>
 */
package it.unibas.codicefiscale.controllo;

import com.jfoenix.controls.JFXProgressBar;
import it.unibas.codicefiscale.Constanti;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class SpashScreenController implements Initializable {

    @FXML
    private Pane pannelloSfondo;
    @FXML
    private ImageView splashScreenImage;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label labelSplash;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.print("Qualcosa viene inizializzato \n");
        splashScreenImage.setImage(new Image(Constanti.SPLASH_SCREEN));
        progressBar.setProgress(0.5);
        gestisciProgressBar();
        labelSplash.setTextFill(Color.web("#42FFFF"));
    }

    public JFXProgressBar getProgressBar() {
        return progressBar;
    }

    public void gestisciProgressBar() {
        double progresso = 0.5;
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                progressBar.setProgress(progresso + 0.5);
            }
        }, 2000, 2000);
    }


}
