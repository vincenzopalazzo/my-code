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
package it.unibas.codicefiscale.vista;

import it.unibas.codicefiscale.Constanti;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class VistaPrincipale {

    private static final Logger LOGGER = LoggerFactory.getLogger(VistaPrincipale.class);

    private Parent root = new BorderPane();

    public Parent getRoot() {
        return root;
    }

    public void load() {
        FXMLLoader load = new FXMLLoader();
        try {
            root = load.load(System.class.getResourceAsStream(Constanti.PANNELLO_VISTA_PRINCIPALE));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //TODO cercare l'equivalente in material themes javafx
    public void visualizzaMessaggio(String messaggio, boolean errore) {
        if (errore) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText(messaggio);
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFO");
        alert.setContentText(messaggio);
        alert.show();
    }

}
