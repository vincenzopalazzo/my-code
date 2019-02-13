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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import it.unibas.codicefiscale.Applicazione;
import it.unibas.codicefiscale.Constanti;
import it.unibas.codicefiscale.GestoreApp;
import it.unibas.codicefiscale.modello.Setting;
import it.unibas.codicefiscale.persistenza.DAOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class PannellofeedController implements Initializable {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PannellofeedController.class);

    @FXML
    private JFXTextArea textArea;
    @FXML
    private JFXButton bottoneForm;
    @FXML
    private JFXButton bottoneIgnora;
    @FXML
    private JFXCheckBox checkNonMosytrarePiu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea.setText(Constanti.TESTO_FEED);
        textArea.setStyle("-fx-text-fill: #E1E1E1; ");
        textArea.setBackground(bottoneForm.getBackground());
    }

    @FXML
    private void chiudiScermataFeed(MouseEvent event) {
        event.consume();
        if (checkNonMosytrarePiu.isSelected()) {
            GestoreApp.getIstance().getModello().putBean(Constanti.VISUALIZZA_FEED, false);
            Setting setting = GestoreApp.getIstance().getSetting();
            setting.setMostrarePannelloFeed(false);
            try {
                GestoreApp.getIstance().getdAOGenericoJson().salva(setting, Constanti.PERCORSO_SETTING);
            } catch (DAOException ex) {
                LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
            }
            GestoreApp.getIstance().getPannelloFeed().getStage().hide();
            GestoreApp.getIstance().getPannelloFeed().getStage().close();
            return;
        }
        GestoreApp.getIstance().getModello().putBean(Constanti.VISUALIZZA_FEED, true);
        GestoreApp.getIstance().getPannelloFeed().getStage().close();
    }

    @FXML
    private void visualizzaForm(MouseEvent mouseEvent) {
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(GestoreApp.getIstance().getApp());
        hostServices.showDocument(Constanti.FORM_GOOGLE_FEDD);
        chiudiScermataFeed(mouseEvent);
    }


}
