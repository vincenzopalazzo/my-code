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

import com.jfoenix.controls.JFXToggleButton;
import it.unibas.codicefiscale.Applicazione;
import it.unibas.codicefiscale.Constanti;
import it.unibas.codicefiscale.modello.Setting;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */

//TODO Controlla il toggle button di notifica scura che ogni tanto fa i capricci
public class SettingPanelController implements Initializable {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingPanelController.class);
    
    @FXML
    private ImageView spazioeTastoIndietro;
    @FXML
    private Hyperlink linkPannelloFeed;
    @FXML
    private ImageView spazioEsitoFeed;
    @FXML
    private JFXToggleButton bottoneAbilitaInterfaccia;
    @FXML
    private ImageView spazioneAnteprimaInterfaccia;
    @FXML
    private ImageView anteprimaVista;
    @FXML
    private JFXToggleButton abilitaMosterTheme;
    @FXML
    private JFXToggleButton abilitaAdaptaTheme;
    @FXML
    private ImageView iconMosterTheme;
    @FXML
    private ImageView iconAdaptaTheme;
    @FXML
    private AnchorPane pannelloSceltaTema;
    @FXML
    private Label labelComingSoonUno;
    @FXML
    private Label labelComingSoonDue;
    @FXML
    private ImageView spazioAvatar;
    @FXML
    private JFXToggleButton attivaSplashScreen;
    @FXML
    private JFXToggleButton attivaNotificaScura;
    @FXML
    private JFXToggleButton bottoneAbilitaNotifiche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spazioeTastoIndietro.setImage(new Image(Constanti.ICONA_INDIETRO_OFF));
        //TODO test visualizzazione imagine
        cambiaModalita(null);
        //TODO ste icona feed
        spazioEsitoFeed.setImage(new Image(Constanti.ICON_FEED_YES));
        inizialiizzaAnteprima(null);
        //TODO implementare funzione cambio tema, icon test
        iconAdaptaTheme.setImage(new Image(Constanti.ICON_FUNZIONE_NON_IMPLEMENTATA));
        iconMosterTheme.setImage(new Image(Constanti.ICON_FUNZIONE_NON_IMPLEMENTATA));
        
        spazioAvatar.setImage(new Image(Applicazione.getIstance().getSetting().getAvatarScelto()));
        //TODO capire bene questa cosa dei setting dei colori, ricorda che devi gestire anche il cambio tema.
        attivaSplashScreen.setSelected(Applicazione.getIstance().getSetting().isMostrareSplashScreen());
        attivaNotificaScura.setSelected(Applicazione.getIstance().getSetting().isNotificaScura());
        labelComingSoonDue.setVisible(false);
        labelComingSoonUno.setVisible(false);
        bottoneAbilitaInterfaccia.setSelected(Applicazione.getIstance().getSetting().isInterfacciaSmart());
        bottoneAbilitaNotifiche.setSelected(Applicazione.getIstance().getSetting().isNotificheAbilitate());
        abilitaNotifiche();
        //inizializzazione icone per pannello cambio avatar
    }
    
    /**
     * Serve solo per la vista classica desktop per il menu item
     * @param event 
     */
    @FXML
    private void chiudiSetting(MouseEvent event) {
        //TODO chiedi se vuole applicare i cambiamenti, e prima verifica se ci sono cambiamenti.
        Applicazione.getIstance().getModello().putBean(Constanti.ABILITA_MODIFICHE, false);
        spazioeTastoIndietro.setImage(new Image(Constanti.ICONA_INDIETRO_ON));
        Applicazione.getIstance().getPannelloSetting().getStageSetting().close();
        spazioeTastoIndietro.setImage(new Image(Constanti.ICONA_INDIETRO_OFF));
        LOGGER.debug("Ripristino setting precedenti");
        Setting precedenti = (Setting) Applicazione.getIstance().getModello().getBean(Constanti.SETTING);
        Applicazione.getIstance().setSetting(precedenti);
        rinizializzaSetting(precedenti);
    }
    
    @FXML
    private void visualizzaAnteprima(MouseEvent event) {
        nascondiComponentiPerAnteprima();
        anteprimaVista.setVisible(true);
        anteprimaVista.setImage(spazioneAnteprimaInterfaccia.getImage());
    }
    
    @FXML
    private void inizialiizzaAnteprima(MouseEvent event) {
        anteprimaVista.setVisible(false);
        anteprimaVista.setImage(null);
        visualizzaComponentiPerAnteprima();
    }
    
    private void nascondiComponentiPerAnteprima() {
        pannelloSceltaTema.setVisible(false);
        attivaNotificaScura.setVisible(false);
        attivaSplashScreen.setVisible(false);
        bottoneAbilitaNotifiche.setVisible(false);
    }
    
    private void visualizzaComponentiPerAnteprima() {
        pannelloSceltaTema.setVisible(true);
        attivaNotificaScura.setVisible(true);
        attivaSplashScreen.setVisible(true);
        bottoneAbilitaNotifiche.setVisible(true);
    }
    
    @FXML
    private void cambiaModalita(MouseEvent event) {
        //TODO aggiunti effetto apertua immagine.
        if (bottoneAbilitaInterfaccia.isSelected()) {
            spazioneAnteprimaInterfaccia.setImage(new Image(Constanti.ANTEPRIMA_INTERFACCIA_SMART));
        } else {
            spazioneAnteprimaInterfaccia.setImage(new Image(Constanti.ANTEPRIMA_INTERFACCIA_DESKTOP));
        }
    }
    
    @FXML
    private void infoStatusMoster(MouseEvent event) {
        labelComingSoonUno.setVisible(true);
    }
    
    @FXML
    private void infoStatusAdapta(MouseEvent event) {
        labelComingSoonDue.setVisible(true);
    }
    
    @FXML
    private void exitStatusMoster(MouseEvent event) {
        labelComingSoonUno.setVisible(false);
    }
    
    @FXML
    private void exiteStatusDue(MouseEvent event) {
        labelComingSoonDue.setVisible(false);
    }
    
    @FXML
    private void abilitaPannelloSceltaAvatar(MouseEvent event) {
        Applicazione.getIstance().getPannelloSettingAvatar().getStageSetting().showAndWait();
        //Image avatar = (Image) Applicazione.getIstance().getModello().getBean(Constanti.AVATAR_SCELTO);
        String percorso = (String) Applicazione.getIstance().getModello().getBean(Constanti.AVATAR_SCELTO);
        if(percorso == null){
            LOGGER.debug("Nessun avatar");
            //TODO gestire meglio questo caso
            return;
        }
        Image avatar = new Image(percorso);
        Applicazione.getIstance().getSetting().setAvatarScelto((String) Applicazione.getIstance().getModello().getBean(Constanti.AVATAR_SCELTO));
        spazioAvatar.setImage(avatar);
    }
    
    public ImageView getSpazioAvatar() {
        return spazioAvatar;
    }

    @FXML
    private void disabilitaSplash(MouseEvent event) {
        Applicazione.getIstance().getSetting().setMostrareSplashScreen(attivaSplashScreen.isSelected());
    }

    @FXML
    private void abilitaNotificaScura(MouseEvent event) {
        //TODO iniserire la possibilita' di rimuovere del tutto la notifica
//        if(!bottoneAbilitaNotifiche.isSelected()){
//            return;
//        }
        Applicazione.getIstance().getSetting().setNotificaScura(attivaNotificaScura.isSelected());
    }
    
    @FXML
    private void abilitaCambiamenti(MouseEvent event) {
        Applicazione.getIstance().getSetting().setInterfacciaSmart(bottoneAbilitaInterfaccia.isSelected());
        Applicazione.getIstance().getModello().putBean(Constanti.ABILITA_MODIFICHE, true);
        Applicazione.getIstance().getPannelloSetting().getStageSetting().close();
    }
    
    private void abilitaNotifiche(){
        if(bottoneAbilitaNotifiche.isSelected()){
            attivaNotificaScura.setVisible(true);
            return;
        }
        attivaNotificaScura.setVisible(false);
    }

    @FXML
    private void statoBottoniNotifiche(MouseEvent event) {
        Applicazione.getIstance().getSetting().setNotificheAbilitate(bottoneAbilitaNotifiche.isSelected());
        abilitaNotifiche();
    }

    //TODO bug, non si annullano le operazioni. l'abilitazione dell'interfaccia invece funziona
    private void rinizializzaSetting(Setting precedenti) {
        bottoneAbilitaInterfaccia.setSelected(precedenti.isInterfacciaSmart());
        LOGGER.debug("Interfaccia smart abilitata: " + precedenti.isInterfacciaSmart());
        bottoneAbilitaNotifiche.setSelected(precedenti.isNotificheAbilitate());
        LOGGER.debug("notifiche abilitate abilitata: " + precedenti.isNotificheAbilitate());
        abilitaNotifiche();
        attivaNotificaScura.setSelected(precedenti.isNotificaScura());
        LOGGER.debug("Notofica scura abilitata: " + precedenti.isNotificaScura());
        attivaSplashScreen.setSelected(precedenti.isMostrareSplashScreen());
        LOGGER.debug("SplashScreen abilitata: " + precedenti.isMostrareSplashScreen());
    }
    
}
