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
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class PannelloLicense extends Stage{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PannelloLicense.class);
    
    private Scene scene;

    public PannelloLicense() {
        FXMLLoader loader = new FXMLLoader();
        Parent parent;
        try {
            parent = loader.load(new FileInputStream(Constanti.PANNELLO_LICENSE));
            scene = new Scene(parent);
            this.setScene(scene);
        } catch (Exception e) {
            LOGGER.error("Errore nel caricamento del file: " + Constanti.PANNELLO_LICENSE);
            e.printStackTrace();
        }
        
    }
    
    
    
}
