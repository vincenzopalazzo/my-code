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

import animatefx.animation.Flash;
import it.unibas.codicefiscale.Constanti;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class PannelloSetting {

    private static final Logger LOGGER = LoggerFactory.getLogger(PannelloSetting.class);

    private Stage stageSetting;
    private Parent pannello;
    private Flash animation;

    public PannelloSetting() {
        FXMLLoader load = new FXMLLoader();
        stageSetting = new Stage();
        try {
            pannello = load.load(ClassLoader.getSystemResourceAsStream(Constanti.PANNELLO_SETTING));
            //TODO manca qualcosa per settare il pannello a modale.
            stageSetting.initStyle(StageStyle.TRANSPARENT);
            stageSetting.initModality(Modality.APPLICATION_MODAL);
            stageSetting.setScene(new Scene(pannello));
            stageSetting.setResizable(false);
            animation = new Flash(pannello);
        } catch (IOException ex) {
            LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    public Flash getPulse() {
        return animation;
    }

    public Stage getStageSetting() {
        return stageSetting;
    }

}
