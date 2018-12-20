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

/**
 *
 * @author https://github.com/vincenzopalazzo, stefanofoti
 */
public class Constanti {

    public static final String[] PARI = {"0=0", "1=1", "2=2", "3=3", "4=4", "5=5", "6=6", "7=7", "8=8", "9=9", "A=0", "B=1",
        "C=2", "D=3", "E=4", "F=5", "G=6", "H=7", "I=8", "J=9", "K=10", "L=11", "M=12", "N=13",
        "O=14", "P=15", "Q=16", "R=17", "S=18", "T=19", "U=20", "V=21", "W=22", "X=23", "Y=24", "Z=25"};

    public static final String[] DISPARI = {"0=1", "1=0", "2=5", "3=7", "4=9", "5=13", "6=15", "7=17", "8=19", "9=21", "A=1", "B=0",
        "C=5", "D=7", "E=9", "F=13", "G=15", "H=17", "I=19", "J=21", "K=2", "L=4", "M=18", "N=20",
        "O=11", "P=3", "Q=6", "R=8", "S=12", "T=14", "U=16", "V=10", "W=22", "X=25", "Y=24", "Z=23"};
    
    public static final String[] CARATTERE_CONTROLLO = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    
     public static final String[] CONSONANTI = {
        "B", "C", "D", "F", "G", "H", "L", "M", "N", "P", "Q",
        "R", "S", "T", "V", "Z", "J", "K", "W", "X", "Y"
    };
    public static final String CONSONANTI__STRING = "B C D F G H L M N P Q R S T V Z J K W X Y";
    public static final String VOCALI__STRING = "A E I O U";
    
    public static final String[] CONSTANTI_ALFABETO = { 
            "A", "B", "C", "D", "E", 
            "H", "L", "M", "P", "R", 
            "S", "T"
    };

    public static final String[] VOLCALI_ALFABETO = {"A", "E", "I", "O", "U"};

    //FILE DI CONFIGURAZIONE VISTE
    private static final String PERCORSO_PRINCIPALE_FORM = "/form/";
    public static final String PANNELLO_COPYRIGHT = PERCORSO_PRINCIPALE_FORM + "pannelloCopyright.fxml";
    public static final String PANNELLO_SETTING = PERCORSO_PRINCIPALE_FORM + "settingPanel.fxml";
    public static final String PANNELLO_SETTING_AVATAR = PERCORSO_PRINCIPALE_FORM + "settingAvatarPanel.fxml";
    public static final String PANNELLO_SPLASH_SCREEN = PERCORSO_PRINCIPALE_FORM + "spashScreen.fxml";
    public static final String PANNELLO_VISTA_PRINCIPALE = PERCORSO_PRINCIPALE_FORM + "FrameFX.fxml";
    public static final String PANNELLO_INFO_AUTORI = PERCORSO_PRINCIPALE_FORM + "InfoAutori.fxml";
    public static final String PANNELLO_FEED = PERCORSO_PRINCIPALE_FORM + "Pannellofeed.fxml";
    public static final String PANNELLO_LICENSE = PERCORSO_PRINCIPALE_FORM + "pannelloLicense.fxml";

    
    //ICONE
    private static final String PERCORSO_PRINCIPALE_ICON = "/icon/";
    public static final String ICONA_COPYRIGHT = PERCORSO_PRINCIPALE_ICON + "2000px-Copyright.svg.png";
    public static final String SPLASH_SCREEN = PERCORSO_PRINCIPALE_ICON + "splashscreeMyCode.png";
    public static final String ICONA_INDIETRO_OFF = PERCORSO_PRINCIPALE_ICON + "left-arrow-off.png";
    public static final String ICONA_INDIETRO_ON = PERCORSO_PRINCIPALE_ICON + "left-arrow-on.png";
    public static final String ICONA_AVANTI = PERCORSO_PRINCIPALE_ICON + "next.png";
    public static final String ANTEPRIMA_INTERFACCIA_DESKTOP = PERCORSO_PRINCIPALE_ICON + "grafica_desktop.png";
    public static final String ANTEPRIMA_INTERFACCIA_SMART = PERCORSO_PRINCIPALE_ICON + "grafica_smart.png";
    public static final String ICON_FEED_NO = PERCORSO_PRINCIPALE_ICON + "feed_no.png";
    public static final String ICON_FEED_YES = PERCORSO_PRINCIPALE_ICON + "feed_yes.png";
    public static final String ICON_FUNZIONE_NON_IMPLEMENTATA = PERCORSO_PRINCIPALE_ICON + "code-programming.png";
    public static final String ICON_SCEGLI_AVATAR = PERCORSO_PRINCIPALE_ICON + "user.png";
    public static final String ICON_AVATAR_UNO = PERCORSO_PRINCIPALE_ICON + "rafael-nadal.png";
    public static final String ICON_AVATAR_DUE = PERCORSO_PRINCIPALE_ICON + "cristiano-ronaldo.png";
    public static final String ICON_AVATAR_TRE = PERCORSO_PRINCIPALE_ICON + "michael-schumacher.png";
    public static final String ICON_AVATAR_QUATTRO = PERCORSO_PRINCIPALE_ICON + "mike-tyson.png";
    public static final String ICON_AVATAR_CINQUE = PERCORSO_PRINCIPALE_ICON + "roger-federer.png";
    public static final String ICON_AVATAR_SEI = PERCORSO_PRINCIPALE_ICON + "roger-federer.png";
    public static final String ICON_AVATAR_SETTE = PERCORSO_PRINCIPALE_ICON + "roger-federer.png";
    public static final String ICON_AVATAR_OTTO = PERCORSO_PRINCIPALE_ICON + "roger-federer.png";
    public static final String AVATAR_VINZ = PERCORSO_PRINCIPALE_ICON + "iconVinz.png";
    public static final String ICONA_SALVA_CODICE = PERCORSO_PRINCIPALE_ICON + "save.png";
    public static final String ICONA_INFO_AUTORI = PERCORSO_PRINCIPALE_ICON + "team.png";
    public static final String ICONA_INFO_PRIVACY = PERCORSO_PRINCIPALE_ICON + "privacy.png";
    public static final String ICONA_DONAZIONE = PERCORSO_PRINCIPALE_ICON + "donazioni.png";
    public static final String ICONA_CONTATTACI = PERCORSO_PRINCIPALE_ICON + "mail.png";
    public static final String ICONA_CONDIVIDI = PERCORSO_PRINCIPALE_ICON + "share.png";
    public static final String ICONA_NOTFICA_ON = PERCORSO_PRINCIPALE_ICON + "notification_on.png";
    public static final String ICONA_NOTFICA_OFF = PERCORSO_PRINCIPALE_ICON + "notification_off.png";
    public static final String ICONA_SETTING = PERCORSO_PRINCIPALE_ICON + "setting.png";
    public static final String ICONA_CONFERMA_NOTIFICA = PERCORSO_PRINCIPALE_ICON + "conferma.png";
    public static final String ICONA_LICENSE = PERCORSO_PRINCIPALE_ICON + "copyright.png";
    
    //CICLO DI VITA APPLIZAZIONE
    public static final String VISUALIZZA_FEED = "VISUALIZZA_FEED";
    //TODO questo deve essere modificato i setting devono essere in maniera separata dall'applicazione oppure 
    //dare la possibilita di scegliere
    private static final String PERCORSO_ESECUZIONE_JAR = System.getProperty("user.dir");
    public static final String PERCORSO_SETTING = PERCORSO_ESECUZIONE_JAR + "/core/dati/setting.json";
    public static final String NOMEFILE = PERCORSO_ESECUZIONE_JAR + "/core/dati/listaComuni.txt";
    public static final String AVATAR_SCELTO = "AVATAR_SCELTO";
    public static final String ABILITA_MODIFICHE = "ABILITA_MODIFICHE";
    public static final String SETTING = "SETTING";
    
    //URL
    public static final String SITO_DONAZIONI = "https://www.gigabox.top/donazioni";
    public static final String FORM_GOOGLE_FEDD = "https://docs.google.com/forms/d/12KTIcRc8-3DiS7mSfZGO28a7mDToLJFkzwMTPZynKBE/edit";

    //Constanti per testo fead
    public static final String TESTO_FEED = "Ciao, spero che il tuo codice fiscale sia stato generato correttamente," +
            " se per te non è un problema vorremmo ricevere dei feed da parte tua sull'uso del nostro software " +
            "usiamo una form di google dove ti poniamo un po di domante se per te va bene puoi cliccare sul bottone" +
            " FORM. Altrimenti puoi non ricevere più questa notifica e se vuoi lasciare il feed sull'applicazione in " +
            "un secondo monento puoi trovare il modo nel pannello di setting";
    
}
