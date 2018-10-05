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
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author https://github.com/vincenzopalazzo
 */

public class PannelloFeed {

    private static final Logger LOGGER = LoggerFactory.getLogger(PannelloFeed.class);

    private Stage stage = new Stage();

    public void init() {
        FXMLLoader load = new FXMLLoader();
        Parent root = new AnchorPane();
        try {
            root = load.load(ClassLoader.getSystemResourceAsStream(Constanti.PANNELLO_FEED));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            this.stage = stage;
        } catch (IOException e) {
            LOGGER.error("Si e' verificato un errore del tipo: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }

}
