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
import it.unibas.codicefiscale.GestoreApp;
import it.unibas.codicefiscale.controllo.eventi.EventCaricaArchivio;
import it.unibas.codicefiscale.controllo.eventi.EventConfigura;
import it.unibas.codicefiscale.controllo.eventi.EventProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author https://github.com/vincenzopalazzo
 */
public class SpashScreenController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpashScreenController.class);

    @FXML
    private ImageView splashScreenImage;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label labelSplash;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO cambia il caricamebto degli eventi all'interno del medietor, prova ad inizializzare le proprietà setto nelle medietor
        GestoreApp.getIstance().getEventMedietor().addEvent(Constanti.CONFIGURA_EVENTO, new EventConfigura());
        GestoreApp.getIstance().getEventMedietor().addEvent(Constanti.CARICA_ARCHIVIO_EVENTO, new EventCaricaArchivio());

        GestoreApp.getIstance().getModello().putBean(Constanti.PROGRESS_BAR, progressBar);
        GestoreApp.getIstance().getModello().putBean(Constanti.VALORE_PROGRESS_BAR, new Double(0));
        LOGGER.debug("Inizializzazione spalsh screen");
        splashScreenImage.setImage(new Image(Constanti.SPLASH_SCREEN));
        labelSplash.setTextFill(Color.web("#42FFFF"));
        GestoreApp.getIstance().getEventMedietor().runEvent(Constanti.CONFIGURA_EVENTO);
    }
}
