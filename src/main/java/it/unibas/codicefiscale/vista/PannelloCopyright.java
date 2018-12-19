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
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 * 
 * @author https://github.com/vincenzopalazzo
 */
public class PannelloCopyright {
    
    private Stage stage;
    
    public void init(){
	FXMLLoader load = new FXMLLoader();
	AnchorPane dialog = new AnchorPane();
	try {
	    dialog = load.load(System.class.getResourceAsStream(Constanti.PANNELLO_COPYRIGHT));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	stage = new Stage();
	Scene scene = new Scene(dialog);
	stage.setScene(scene);
    }
    
    public void visualizza(){
	//Lo stage diventa modale quando  lo stage padre è nello stato di shoAndWait
	stage.initModality(Modality.APPLICATION_MODAL);
	stage.showAndWait();
    }

}
