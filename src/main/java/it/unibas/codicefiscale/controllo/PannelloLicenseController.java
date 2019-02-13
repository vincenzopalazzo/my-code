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
import it.unibas.codicefiscale.Applicazione;
import java.net.URL;
import java.util.ResourceBundle;

import it.unibas.codicefiscale.GestoreApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class PannelloLicenseController implements Initializable {

    @FXML
    private JFXTextArea textLicense;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textLicense.setStyle("-fx-text-inner-color: white;");
        textLicense.setText(GestoreApp.getIstance().getLicense().getLicenze());
    }    
    
}
