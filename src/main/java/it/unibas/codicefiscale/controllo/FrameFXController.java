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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import it.unibas.codicefiscale.Applicazione;
import it.unibas.codicefiscale.Constanti;
import it.unibas.codicefiscale.GestoreApp;
import it.unibas.codicefiscale.modello.*;
import it.unibas.codicefiscale.persistenza.DAOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import org.controlsfx.control.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gigabox.components.JFXToast;

import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class FrameFXController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrameFXController.class);

    @FXML
    private JFXTextField textCodice;
    @FXML
    private JFXButton bottoneCalcola;
    @FXML
    private JFXTextField textNome;
    @FXML
    private JFXTextField textCognome;
    @FXML
    private JFXTextField textCitta;
    @FXML
    private JFXDatePicker spinnerDate;
    @FXML
    private JFXComboBox<String> comboSesso;
    @FXML
    private JFXComboBox<String> comboProvincia;
    @FXML
    private BorderPane frame;
    @FXML
    private ImageView spazioNotifica;
    @FXML
    private MenuBar barraMenu;
    @FXML
    private ImageView spazioSetting;

    private Image immagineNotificheZero = new Image(Constanti.ICONA_NOTFICA_OFF);
    private Image immagineNotificheUno = new Image(Constanti.ICONA_NOTFICA_ON);
    @FXML
    private MenuItem menuItemSetting;
    private ProgressIndicator inidcatoreDiProgressione;
    private AnchorPane pannelloCaricamento;
    @FXML
    private Pane pannelloBottoniSmart;
    @FXML
    private ImageView spazioPosizioneUno; //TODO Refatcoring del resto delle diciarazioni;
    @FXML
    private ImageView spazioPosizioneDue;
    @FXML
    private ImageView spazioPosizioneTre;
    @FXML
    private ImageView spazioPosizioneQuattro;
    @FXML
    private ImageView spazioPosizioneCinque;
    @FXML
    private ImageView spazioPosizioneSei;
    @FXML
    private ImageView spazioLicense;

    public Pane getPannelloBottoniSmart() {
        return pannelloBottoniSmart;
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inizializzaPanello();
        popolaConboSesso();
        settComboProvincia();
        textCodice.setEditable(false);
        spazioNotifica.setImage(immagineNotificheZero);
        spazioSetting.setImage(new Image(Constanti.ICONA_SETTING));
        spazioLicense.setImage(new Image(Constanti.ICONA_LICENSE));
        inizializzaToolTip();
        riaggiornaInterfacciaGrafica();
    }

    private void inizializzaToolTip() {
        bottoneCalcola.setTooltip(new Tooltip("Calcola il codice fiscale con i dati iniseriti"));
        textCitta.setTooltip(new Tooltip("Inserisci la citta di nascita"));
        textCognome.setTooltip(new Tooltip("Inserisci il tuo cognome nubile"));
        textNome.setTooltip(new Tooltip("Inserisci il tuo nome di nascita"));
        textCodice.setTooltip(new Tooltip("Qui verra' restituito il tuo codice fiscale"));
        //Inizializzazione pannello sulle icone
        Tooltip.install(spazioSetting, new Tooltip("Qui puoi accedere alle impostazioni"));
        if (spazioNotifica.getImage().equals(immagineNotificheUno)) {
            Tooltip.install(spazioNotifica, new Tooltip("Hai una notifica"));
        } else {
            Tooltip.install(spazioNotifica, new Tooltip("Non hai nessuna notifica"));
        }
        Tooltip.install(spazioPosizioneUno, new Tooltip("Salva codice fiscale (funziona non ancora implementata)"));
        Tooltip.install(spazioPosizioneDue, new Tooltip("Info sviluppatori"));
        Tooltip.install(spazioPosizioneTre, new Tooltip("Con noi sei al sicuro"));
        Tooltip.install(spazioPosizioneQuattro, new Tooltip("Donazioni"));
        Tooltip.install(spazioPosizioneCinque, new Tooltip("Contattaci"));
        Tooltip.install(spazioPosizioneSei, new Tooltip("Condividi con i tuoi amici (funziona non ancora implementata)"));
    }

    private void inizializzaPanello() {
        Image immagine = new Image(Constanti.ICONA_SALVA_CODICE);
        spazioPosizioneUno.setImage(immagine);
        spazioPosizioneDue.setImage(new Image(Constanti.ICONA_INFO_AUTORI));
        spazioPosizioneTre.setImage(new Image(Constanti.ICONA_INFO_PRIVACY));
        spazioPosizioneQuattro.setImage(new Image(Constanti.ICONA_DONAZIONE));
        spazioPosizioneCinque.setImage(new Image(Constanti.ICONA_CONTATTACI));
        spazioPosizioneSei.setImage(new Image(Constanti.ICONA_CONDIVIDI));
        if (GestoreApp.getIstance().getSetting().isInterfacciaSmart()) {
            pannelloBottoniSmart.setVisible(true);
            return;
        }
        pannelloBottoniSmart.setVisible(false);
    }

    private void riaggiornaInterfacciaGrafica() {
        Setting setting = GestoreApp.getIstance().getSetting();
        if (setting != null) {
            LOGGER.debug("Setting non nullo");
            if (!setting.isInterfacciaSmart()) {
                LOGGER.debug("grafica standard");
                visualizzaMenuBar();
                spazioNotifica.setVisible(false);
                spazioSetting.setVisible(false);
                pannelloBottoniSmart.setVisible(false);
            } else {
                LOGGER.debug("grafica avanzata");
                disattivaMenuBar();
                spazioNotifica.setVisible(true);
                spazioSetting.setVisible(true);
                pannelloBottoniSmart.setVisible(true);
            }
        }
    }

    public void rendiVisibilePannelloCaricamento() {
        pannelloCaricamento.setVisible(true);
        inidcatoreDiProgressione.setVisible(true);
    }

    public void nascondiPannelloCaricamento() {
        pannelloCaricamento.setVisible(false);
        inidcatoreDiProgressione.setVisible(false);
    }

    public void popolaConboSesso() {
        comboSesso.getItems().add("M");
        comboSesso.getItems().add("F");
    }

    public void settComboProvincia() {
        Archivio archivio = GestoreApp.getIstance().getArchivio();
        if (archivio == null) {
            return;
        }
        Set<String> sigle = new HashSet<>();
        for (Comune comune : archivio.getComuni()) {
            sigle.add(comune.getSiglaProvincia());
        }
        List<String> listaProv = new ArrayList<>(sigle);
        Collections.sort(listaProv);
        for (String strinaString : listaProv) {
            comboProvincia.getItems().add(strinaString);
        }
    }

    @FXML
    private void calcolaCodiceF(ActionEvent event) {
        String nome = textNome.getText();
        String errori = new String();
        if (nome.trim().isEmpty()) {
            errori += "Devi inserire qualcosa nel campo nome.";
            textNome.setUnFocusColor(Color.RED);
        }
        if (controllaNumeroInStringa(nome)) {
            errori += "\nAh ok quindi il tuo nome contiene un numero??";
            textNome.setUnFocusColor(Color.RED);
        }
        String cognome = textCognome.getText();
        if (cognome.trim().isEmpty()) {
            errori += "\n Devi inserire qualcosa nel campo cognome";
            textCognome.setUnFocusColor(Color.RED);
        }
        if (controllaNumeroInStringa(cognome)) {
            errori += "\nAh ok quindi il tuo cognome contiene un numero??";
            textCognome.setUnFocusColor(Color.RED);
        }
        String citta = textCitta.getText();
        if (citta.trim().isEmpty()) {
            errori += "\n Devi inserire qualcosa nel campo Citta natale";
            textCitta.setUnFocusColor(Color.RED);
        }
        if (controllaNumeroInStringa(citta)) {
            errori += "\nAh ok quindi la tua citta contiene un numero??";
            textCitta.setUnFocusColor(Color.RED);

        }
        if (!GestoreApp.getIstance().getArchivio().isPresent(citta)) {
            errori += "\nDevi iniserire una citta valida mi dispiace";
            textCitta.setUnFocusColor(Color.RED);
        }
        String sesso = comboSesso.getValue();
        if (sesso == null) {
            errori += "\n Devi inserire qualcosa nel campo sesso";
        }
        LOGGER.debug("Sesso letto: " + sesso);
        String siglaProvincia = comboProvincia.getValue();
        if (siglaProvincia == null) {
            errori += "\n Devi inserire qualcosa nel campo sigla provincia";
        }
        LOGGER.debug("Sigla provincia letta: " + siglaProvincia);
        LocalDate data = spinnerDate.getValue();
        GregorianCalendar dataAttuale = new GregorianCalendar();
        GregorianCalendar dataNascita = new GregorianCalendar();
        if (data == null) {
            errori += "\n Devi inserire qualcosa nel campo data di nascita";
        } else {
            LOGGER.debug("Data letta dalla spinner: " + data.format(DateTimeFormatter.ISO_LOCAL_DATE));
            dataAttuale = new GregorianCalendar();
            dataNascita = new GregorianCalendar(data.getYear(), data.getMonthValue() - 1, data.getDayOfMonth());
            LOGGER.debug("Data salvata: " + DateFormat.getDateInstance(DateFormat.SHORT).format(dataNascita.getTime()));
        }
        if (dataNascita.after(dataAttuale)) {
            errori += "\nSi ok dimmi una cosa, come fai a prevedere il futuro? "
                    + "\n Correggi la data";
        }
        if (!errori.trim().isEmpty()) {
            LOGGER.error("Si e' verificato un errore del tipo: \n" + errori);
            GestoreApp.getIstance().getVistaPrincipale().visualizzaMessaggio(errori, true);
            return;
        }
        resettaColori();
        Persona persona = new Persona(nome.toLowerCase(), cognome.toLowerCase(), 
                sesso.toLowerCase(), citta.toLowerCase(), siglaProvincia.toLowerCase());
        persona.setDataNascita(dataNascita);
        CodiceFiscale codiceFiscale = new CodiceFiscale(GestoreApp.getIstance().getArchivio());
        String codiceFiscaleString = codiceFiscale.getCodiceFiscale(persona);
        LOGGER.debug("Codice Fiscale calcolato: " + codiceFiscaleString);
        textCodice.setText(codiceFiscaleString.toUpperCase());
        try {
            if (GestoreApp.getIstance().getModello().getBean(Constanti.VISUALIZZA_FEED) == null ||
                    (boolean) GestoreApp.getIstance().getModello().getBean(Constanti.VISUALIZZA_FEED)) {
                JFXToast.makeText(GestoreApp.getIstance().getPrimaryStage(), "Hai una nuova notifica!");
            }
            if (GestoreApp.getIstance().getSetting().isNotificheAbilitate()) {
                creaNotificaGenerato();
            }
        } catch (Exception e) {
            LOGGER.error("Si e' verificato un errore del tipo: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void esciApp(ActionEvent event) {
        try {
            //TODO chiedere se vuole uscire davvero dall'applicazione.
            GestoreApp.getIstance().getdAOGenericoJson().salva((Setting) GestoreApp.getIstance().getSetting(), Constanti.PERCORSO_SETTING);
        } catch (DAOException ex) {
            LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        System.exit(0);
    }

    @FXML
    private void visualizzaInfo(ActionEvent event) {
        try {
            GestoreApp.getIstance().getInfoAutori().init();
            GestoreApp.getIstance().getInfoAutori().visualizza();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void visualizzaPrivacy(ActionEvent event) {
        //TODO stavi capendo come funzionava il pannello di risposta
        GestoreApp.getIstance().getPannelloCopyright().init();
        GestoreApp.getIstance().getPannelloCopyright().visualizza();
    }

    @FXML
    private void apriNotifica(MouseEvent event) {
        //TODO generalizzare questo metodo per rispondere a qualcosa di più generico
        if (spazioNotifica.getImage().equals(immagineNotificheZero)) {
            LOGGER.debug("Ok nessuna notifica");
            JFXToast.makeText(GestoreApp.getIstance().getPrimaryStage(), "Non ci sono notifiche");
            //TODO settare azioni periodiche
            // dare un occhiata a http://www.baeldung.com/java-executor-service-tutorial
            return;
        }
        GestoreApp.getIstance().getStagePannelloFeed().initModality(Modality.APPLICATION_MODAL);
        spazioNotifica.setImage(immagineNotificheZero);
        GestoreApp.getIstance().getStagePannelloFeed().showAndWait();
        GestoreApp.getIstance().getStagePannelloFeed().initOwner(GestoreApp.getIstance().getPrimaryStage());

    }

    private void creaNotificaGenerato() {
        Notifications notifica = Notifications.create();
        if (GestoreApp.getIstance().getSetting().isNotificaScura()) {
            notifica.darkStyle();
        }
        notifica.title("Codice fiscale")
                .text("codice fiscale generato")
                .graphic(new ImageView(new Image(Constanti.ICONA_CONFERMA_NOTIFICA)))
                .position(Pos.BOTTOM_RIGHT);
        notifica.show();
        Boolean visualizza = (Boolean) GestoreApp.getIstance().getModello().getBean(Constanti.VISUALIZZA_FEED);
        if (visualizza == null || visualizza == true) {
            spazioNotifica.setImage(immagineNotificheUno);
        }
    }

    private boolean controllaNumeroInStringa(String nome) {
        String[] numeri = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < numeri.length; i++) {
            if (nome.contains(numeri[i])) {
                return true;
            }
        }
        return false;
    }

    private void resettaColori() {
        textCitta.setUnFocusColor(Color.BLUE);
        textCognome.setUnFocusColor(Color.BLUE);
        textNome.setUnFocusColor(Color.BLUE);
    }

    public void disattivaMenuBar() {
        barraMenu.setVisible(false);
    }

    public void visualizzaMenuBar() {
        barraMenu.setVisible(true);
    }

    //TODO prova ed effettuare test piu completi.
    @FXML
    private void ricercaCodiceProvincia(KeyEvent event) {
        LOGGER.debug("Ok proviamo ad indovinare la provincia");
        String testoInserito = textCitta.getText() + event.getText();
        LOGGER.debug("Testo iniserito attualmente nel edit tex: " + testoInserito);
        Comune comune = GestoreApp.getIstance().getArchivio().serachComune(testoInserito);
        if (comune != null) {
            List<String> set = comboProvincia.getItems();
            for (int i = 0; i < set.size(); i++) {
                if (set.get(i).trim().equalsIgnoreCase(comune.getSiglaProvincia().trim())) {
                    LOGGER.debug("Trovata la provincia della citta inserita");
                    comboProvincia.valueProperty().setValue(comune.getSiglaProvincia().toUpperCase());
                }
            }
        }
    }

    @FXML
    private void apriSetting(MouseEvent event) {
        try {
            //TODO sfruttare l'animazione per visualizzare i setting reali dopo l'animazione e magari prima si visualizzano solo i setting non reali.
            GestoreApp.getIstance().getModello().putBean(Constanti.SETTING, GestoreApp.getIstance().getSetting().clone());
        } catch (CloneNotSupportedException ex) {
            throw new IllegalArgumentException("Metodo clone non valido:  " + ex.getLocalizedMessage());
        }
        GestoreApp.getIstance().getPannelloSetting().getStageSetting().showAndWait();
        boolean abilitaModifiche = (boolean) GestoreApp.getIstance().getModello().getBean(Constanti.ABILITA_MODIFICHE);
        if (abilitaModifiche) {
            LOGGER.debug("Abilitazione nuovi setting");
            riaggiornaInterfacciaGrafica();
            return;
        }
        LOGGER.debug("Ripristino setting precedenti");
        Setting precedenti = (Setting) GestoreApp.getIstance().getModello().getBean(Constanti.SETTING);
        GestoreApp.getIstance().setSetting(precedenti);
    }

    @FXML
    private void apriSettingItem(ActionEvent event) {
        this.apriSetting(null);
    }

    @FXML
    private void apriInfoAutori(MouseEvent event) {
        visualizzaInfo(null);
    }

    @FXML
    private void apriprivacy(MouseEvent event) {
        visualizzaPrivacy(null);
    }

    @FXML
    private void apriWebDonazioni(MouseEvent event) {
        HostServicesDelegate hostServices = HostServicesDelegate.getInstance(GestoreApp.getIstance().getApp());
        hostServices.showDocument(Constanti.SITO_DONAZIONI);
    }

    @FXML
    private void apriPannelloLicense(MouseEvent event) {
        GestoreApp.getIstance().getPannelloLicense().show();
    }

    @FXML
    private void segnalaQualcosa(MouseEvent event){
        JFXToast.makeText(GestoreApp.getIstance().getPrimaryStage(), "Funzione ancora non implementata");
    }
    @FXML
    private void condividiAmici(MouseEvent event){
        JFXToast.makeText(GestoreApp.getIstance().getPrimaryStage(), "Funzione ancora non implementata");
    }
    @FXML
    private void salvaCodiceGenerato(MouseEvent event){
        JFXToast.makeText(GestoreApp.getIstance().getPrimaryStage(), "Funzione ancora non implementata");
    }
}
