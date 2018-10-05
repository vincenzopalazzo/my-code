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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author  https://github.com/vincenzopalazzo
 */
public class PannelloCopyrightController implements Initializable {

    @FXML
    private ImageView spazioIconaPrivacy;
    @FXML
    private JFXTextArea spazioTesto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spazioIconaPrivacy.setImage(new Image(Constanti.ICONA_INFO_PRIVACY));
        spazioTesto.setStyle("-fx-text-fill: #E1E1E1; ");
    }    
    
}
