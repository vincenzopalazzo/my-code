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

import com.jfoenix.controls.JFXTextArea;
import it.unibas.codicefiscale.Constanti;

import java.net.URL;
import java.util.ResourceBundle;

import it.unibas.codicefiscale.GestoreApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import top.gigabox.components.JFXToast;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class InfoAutoriController implements Initializable {

    @FXML
    private AnchorPane avatar;
    @FXML
    private ImageView vincenzoAvatar;
    @FXML
    private ImageView stafnoAvatar;
    @FXML
    private AnchorPane corniceInfo;
    @FXML
    private JFXTextArea descrizione;
    @FXML
    private Separator separatore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingIniziale();
        settaImagini();
    }

    private void settaImagini() {
        vincenzoAvatar.setImage(new Image(Constanti.AVATAR_VINZ));
        //stafnoAvatar.setImage(new Image(Constanti.AVATAR_VINZ));
    }

    private void settingIniziale() {
        corniceInfo.setVisible(true);
    }

    @FXML
    private void visualizzaInfoVincenzo(MouseEvent mouseEvent) {
        descrizione.setText("Ciao sono Vincenzo studente dell'universita' della Basilicata\n"
                + "Da circa un anno ho approfondito tutti i concetti riguardanti l'intefacce grafiche"
                + "di Java."
                + "\nSono un contributore del progetto Material-UI-Swing " + "https://github.com/atarw/material-ui-swing"
                + "\n \nE Possibile trovarmi su github al seguente link: " + "https://vincenzopalazzo.github.io");
    }

    @FXML
    private void visualizzaInfoStefano(MouseEvent mouseEvent) {
        JFXToast.makeText(GestoreApp.getIstance().getPrimaryStage(), "Funzione ancora non implementata");
    }
}
