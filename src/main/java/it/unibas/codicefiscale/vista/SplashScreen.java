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
import it.unibas.codicefiscale.GestoreApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class SplashScreen {

    private static final Logger LOGGER = LoggerFactory.getLogger(SplashScreen.class);

    private Stage stage;
    private Parent splash;

    public SplashScreen() {
        stage = new Stage();
    }

    public void init(){
        load();
    }


    public void kill() {
        LOGGER.debug("Sto nascondendo lo spalsh screen");
        stage.hide();
    }
    
    public void create(){
        stage.show();
    }

    public void load() {
        FXMLLoader load = new FXMLLoader();
        try {
            splash = load.load(System.class.getResourceAsStream(Constanti.PANNELLO_SPLASH_SCREEN));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Si e' verificato un errrore del tipo: " + e.getLocalizedMessage());
        }
        splash.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
        Scene sceneTrasparent = new Scene(splash, Color.TRANSPARENT);
        stage.setScene(sceneTrasparent);
        stage.initStyle(StageStyle.TRANSPARENT);
        create();
        GestoreApp.getIstance().getEventMedietor().runEvent(Constanti.CONFIGURA_EVENTO);
    }






}
