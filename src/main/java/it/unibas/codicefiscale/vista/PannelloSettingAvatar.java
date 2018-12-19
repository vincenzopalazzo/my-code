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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class PannelloSettingAvatar {

    private static final Logger LOGGER = LoggerFactory.getLogger(PannelloSettingAvatar.class);

    private Stage stageSettingAvatar;
    private Parent pannello;

    public PannelloSettingAvatar() {
        FXMLLoader load = new FXMLLoader();
        stageSettingAvatar = new Stage();
        try {
            pannello = load.load(System.class.getResourceAsStream(Constanti.PANNELLO_SETTING_AVATAR));
            //TODO manca qualcosa per settare il pannello a modale.
            stageSettingAvatar.initStyle(StageStyle.TRANSPARENT);
            stageSettingAvatar.initModality(Modality.APPLICATION_MODAL);
            stageSettingAvatar.setScene(new Scene(pannello));
            stageSettingAvatar.setResizable(false);
        } catch (IOException ex) {
            LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    public Stage getStageSetting() {
        return stageSettingAvatar;
    }

}
