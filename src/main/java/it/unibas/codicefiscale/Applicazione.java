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
package it.unibas.codicefiscale;


import com.jfoenix.concurrency.JFXUtilities;
import it.unibas.codicefiscale.controllo.FrameFXController;
import it.unibas.codicefiscale.controllo.SettingPanelController;
import it.unibas.codicefiscale.controllo.SpashScreenController;
import it.unibas.codicefiscale.modello.Archivio;
import it.unibas.codicefiscale.modello.License;
import it.unibas.codicefiscale.modello.Modello;
import it.unibas.codicefiscale.modello.Setting;
import it.unibas.codicefiscale.persistenza.DAOArchivio;
import it.unibas.codicefiscale.persistenza.DAOException;
import it.unibas.codicefiscale.persistenza.DAOGenericoJson;
import it.unibas.codicefiscale.persistenza.IDAOArchivio;
import it.unibas.codicefiscale.vista.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class Applicazione extends Application {

    private GestoreApp gestoreApp = GestoreApp.getIstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        gestoreApp.setPrimaryStage(primaryStage);
        gestoreApp.setApp(this);
        gestoreApp.callMeIntoMain();
    }

    public static void main(String[] args){
        launch(args);
    }
}
