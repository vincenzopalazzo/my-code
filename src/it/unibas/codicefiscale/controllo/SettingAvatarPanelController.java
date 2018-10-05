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

import it.unibas.codicefiscale.Applicazione;
import it.unibas.codicefiscale.Constanti;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author  https://github.com/vincenzopalazzo
 */

//TODO finire di implementare gli avatr
public class SettingAvatarPanelController implements Initializable {
    
     private static final Logger LOGGER = LoggerFactory.getLogger(SettingAvatarPanelController.class);

    @FXML
    private AnchorPane pannelloSceltaAvatar;
    @FXML
    private ImageView spazioAvatarUno;
    @FXML
    private ImageView spazioAvatarDue;
    @FXML
    private ImageView spazioAvatarTre;
    @FXML
    private ImageView spazioAvatarQuattro;
    @FXML
    private ImageView spazioAvatarCinque;
    @FXML
    private ImageView spazioAvatarSei;
    @FXML
    private ImageView spazioAvatarSette;
    @FXML
    private ImageView spazioAvatarOtto;
    @FXML
    private ImageView spazioBottoneIndietro;
    @FXML
    private ImageView spazioBottoneAvanti;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spazioAvatarUno.setImage(new Image(Constanti.ICON_AVATAR_UNO));
        spazioBottoneIndietro.setImage(new Image(Constanti.ICONA_INDIETRO_OFF));
        spazioAvatarDue.setImage(new Image(Constanti.ICON_AVATAR_DUE));
        spazioAvatarTre.setImage(new Image(Constanti.ICON_AVATAR_TRE));
        spazioAvatarQuattro.setImage(new Image(Constanti.ICON_AVATAR_QUATTRO));
        spazioAvatarCinque.setImage(new Image(Constanti.ICON_AVATAR_CINQUE));
        spazioAvatarSei.setImage(new Image(Constanti.ICON_AVATAR_SEI));
        spazioAvatarSette.setImage(new Image(Constanti.ICON_AVATAR_SETTE));
        spazioAvatarOtto.setImage(new Image(Constanti.ICON_AVATAR_OTTO));
        spazioBottoneAvanti.setImage(new Image(Constanti.ICONA_AVANTI));
    }

    @FXML
    private void chiudiPannelloSettingAvatar(MouseEvent event) {
        Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
    }

    @FXML
    private void segliAvatarUno(MouseEvent event) {
        if (spazioAvatarUno.getImage() != null) {
            LOGGER.debug("carico un avatar");
            Applicazione.getIstance().getModello().putBean(Constanti.AVATAR_SCELTO, Constanti.ICON_AVATAR_UNO);
            Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
        }
    }

    @FXML
    private void segliAvatarDue(MouseEvent event) {
        if (spazioAvatarDue.getImage() != null) {
             LOGGER.debug("carico un avatar");
            Applicazione.getIstance().getModello().putBean(Constanti.AVATAR_SCELTO, Constanti.ICON_AVATAR_DUE);
            Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
        }
    }

    @FXML
    private void segliAvatarTre(MouseEvent event) {
        if (spazioAvatarTre.getImage() != null) {
            LOGGER.debug("carico un avatar");
            Applicazione.getIstance().getModello().putBean(Constanti.AVATAR_SCELTO, Constanti.ICON_AVATAR_TRE);
            Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
        }
    }

    @FXML
    private void segliAvatarQuattro(MouseEvent event) {
        if (spazioAvatarQuattro.getImage() != null) {
             LOGGER.debug("carico un avatar");
            Applicazione.getIstance().getModello().putBean(Constanti.AVATAR_SCELTO, Constanti.ICON_AVATAR_QUATTRO);
            Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
        }
    }

    @FXML
    private void segliAvatarCinque(MouseEvent event) {
        if (spazioAvatarCinque.getImage() != null) {
             LOGGER.debug("carico un avatar");
            Applicazione.getIstance().getModello().putBean(Constanti.AVATAR_SCELTO, Constanti.ICON_AVATAR_CINQUE);
            Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
        }
    }

    @FXML
    private void segliAvatarSei(MouseEvent event) {
        if (spazioAvatarSei.getImage() != null) {
             LOGGER.debug("carico un avatar");
            Applicazione.getIstance().getModello().putBean(Constanti.AVATAR_SCELTO, Constanti.ICON_AVATAR_SEI);
            Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
        }
    }

    @FXML
    private void segliAvatarSette(MouseEvent event) {
        if (spazioAvatarSette.getImage() != null) {
             LOGGER.debug("carico un avatar");
            Applicazione.getIstance().getModello().putBean(Constanti.AVATAR_SCELTO, Constanti.ICON_AVATAR_SETTE);
            Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
        }
    }

    @FXML
    private void segliAvatarOtto(MouseEvent event) {
        if (spazioAvatarOtto.getImage() != null) {
             LOGGER.debug("carico un avatar");
            Applicazione.getIstance().getModello().putBean(Constanti.AVATAR_SCELTO, Constanti.ICON_AVATAR_OTTO);
            Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().close();
        }
    }

}
