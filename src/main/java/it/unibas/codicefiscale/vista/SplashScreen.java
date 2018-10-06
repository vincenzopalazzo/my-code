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

import com.jfoenix.concurrency.JFXUtilities;
import it.unibas.codicefiscale.Applicazione;
import it.unibas.codicefiscale.Constanti;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

/**
 * 
 * @author https://github.com/vincenzopalazzo
 */
public class SplashScreen{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SplashScreen.class);
    
    private Stage stage = new Stage();
    
    public void init(){
	load();
	startThread();
    }
    
    public void kill(){
	stage.hide();
    }
    
    public void load(){
	FXMLLoader load = new FXMLLoader();
	Parent splash = new Pane();
	try {
	    splash = load.load(new FileInputStream(Constanti.PANNELLO_SPLASH_SCREEN));
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("Si e' verificato un errrore del tipo: " + e.getLocalizedMessage());
	}
        splash.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
	stage = new Stage();
	stage.setScene(new Scene(splash, Color.TRANSPARENT));
	stage.initStyle(StageStyle.TRANSPARENT);
	stage.show();
    }
    
    private void startThread(){
	Runnable close = new Runnable() {
	    @Override
	    public void run() {
		stage.hide();
		Applicazione.getIstance().start(new Stage());
	    }
	};
	Thread run = new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    LOGGER.debug("iniza attesa");
		    Thread.sleep(3000); // leep di 5 secondi
		    JFXUtilities.runInFX(close);
		    LOGGER.debug("fine attesa");
		} catch (InterruptedException ex) {
		    LOGGER.error("Si e' verificato un errore con la gestione del thread, errore del tipo: " + ex.getLocalizedMessage());
		}
	    }
	});
	run.start();
    }

}
