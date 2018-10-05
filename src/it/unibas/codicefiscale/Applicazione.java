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
import it.unibas.codicefiscale.vista.VistaPrincipale;
import it.unibas.codicefiscale.modello.Archivio;
import it.unibas.codicefiscale.modello.Modello;
import it.unibas.codicefiscale.modello.Setting;
import it.unibas.codicefiscale.persistenza.DAOArchivio;
import it.unibas.codicefiscale.persistenza.DAOException;
import it.unibas.codicefiscale.persistenza.DAOGenericoJson;
import it.unibas.codicefiscale.persistenza.IDAOArchivio;
import it.unibas.codicefiscale.vista.InfoAutori;
import it.unibas.codicefiscale.vista.PannelloCopyright;
import it.unibas.codicefiscale.vista.PannelloFeed;
import it.unibas.codicefiscale.vista.PannelloSetting;
import it.unibas.codicefiscale.vista.PannelloSettingAvatar;
import it.unibas.codicefiscale.controllo.SpashScreenController;
import it.unibas.codicefiscale.modello.License;
import it.unibas.codicefiscale.vista.PannelloLicense;
import it.unibas.codicefiscale.vista.SplashScreen;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class Applicazione extends Application {

    private static final Applicazione singleton = new Applicazione();
    private static final Logger LOGGER = LoggerFactory.getLogger(Applicazione.class);

    private VistaPrincipale vistaPrincipale;
    private Archivio archivio;
    private IDAOArchivio daoArchivio = new DAOArchivio();
    private InfoAutori infoAutori;
    private FrameFXController frameFXController;
    private SplashScreen splashScreen;
    private PannelloCopyright pannelloCopyright;
    private SpashScreenController spashScreenController;
    private PannelloFeed pannelloFeed;
    private Modello modello;
    private Setting setting;
    private DAOGenericoJson dAOGenericoJson;
    private PannelloSetting pannelloSetting;
    private PannelloSettingAvatar pannelloSettingAvatar;
    private SettingPanelController settingPanelController;
    private PannelloLicense pannelloLicense;
    private Stage primaryStage = new Stage();
    private License license;
    
    private Applicazione() {
    }

    public void init() {
        dAOGenericoJson = new DAOGenericoJson();
        modello = new Modello();
        archivio = new Archivio();
        try {
            //Carico tutti i setting e verifico che ci siano setting sul disco
            setting = (Setting) dAOGenericoJson.carica(new FileInputStream(Constanti.PERCORSO_SETTING), Setting.class);
            LOGGER.debug("Setting caricato");
            Applicazione.getIstance().getModello().putBean(Constanti.VISUALIZZA_FEED, setting.isMostrarePannelloFeed());
        } catch (FileNotFoundException ex) {
            LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
            if (setting == null) {
                setting = new Setting();
                try {
                    dAOGenericoJson.salva(setting, Constanti.PERCORSO_SETTING);
                    LOGGER.debug("setting salvato");
                } catch (DAOException ex1) {
                    LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
                    ex.printStackTrace();
                }
            }
        } catch (DAOException ex) {
            LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        license = new License();
        
        pannelloLicense = new PannelloLicense();
        pannelloSettingAvatar = new PannelloSettingAvatar();
        pannelloSetting = new PannelloSetting();
        pannelloFeed = new PannelloFeed();
        splashScreen = new SplashScreen();
        pannelloCopyright = new PannelloCopyright();
        infoAutori = new InfoAutori();
        vistaPrincipale = new VistaPrincipale();

        spashScreenController = new SpashScreenController();
        settingPanelController = new SettingPanelController();

        if (singleton.setting != null && singleton.setting.isMostrareSplashScreen()) {
            splashScreen.init();
        } else {
            //TODO qualcosa manca, perche non viene inizializzato l'archivio a quanto pare.
        }
    }

    public License getLicense() {
        return license;
    }

    public PannelloLicense getPannelloLicense() {
        return pannelloLicense;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public SettingPanelController getSettingPanelController() {
        return settingPanelController;
    }

    public PannelloSettingAvatar getPannelloSettingAvatar() {
        return pannelloSettingAvatar;
    }

    public PannelloSetting getPannelloSetting() {
        return pannelloSetting;
    }

    public Setting getSetting() {
        return setting;
    }

    public DAOGenericoJson getdAOGenericoJson() {
        return dAOGenericoJson;
    }

    public PannelloFeed getPannelloFeed() {
        return pannelloFeed;
    }

    public Modello getModello() {
        return modello;
    }

    public Stage getStagePannelloFeed() {
        pannelloFeed.init();
        return pannelloFeed.getStage();
    }

    public SpashScreenController getSpashScreenController() {
        return spashScreenController;
    }

    public PannelloCopyright getPannelloCopyright() {
        return pannelloCopyright;
    }

    public SplashScreen getSplashScreen() {
        return splashScreen;
    }

    public FrameFXController getFrameFXController() {
        return frameFXController;
    }

    public InfoAutori getInfoAutori() {
        return infoAutori;
    }

    public VistaPrincipale getVistaPrincipale() {
        return vistaPrincipale;
    }

    public static Applicazione getIstance() {
        return singleton;
    }

    public Archivio getArchivio() {
        return archivio;
    }

    public IDAOArchivio getDaoArchivio() {
        return daoArchivio;
    }

    public Archivio caricaDaFileArchivio() {
        try {
            archivio = daoArchivio.carica(Constanti.NOMEFILE);
            return archivio;
        } catch (DAOException ex) {
            LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
        }
        return null;
    }

    private void caricaArchivioThread() {
        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                LOGGER.debug("Sto inizializzando l'achivio");
                archivio = caricaDaFileArchivio();
                return null;
            }
            
            @Override
            protected void done() {
                if (frameFXController != null) {
                    if (setting != null && !setting.isMostrareSplashScreen()) {
                        frameFXController.settComboProvincia();
                       // frameFXController.nascondiPannelloCaricamento();
                    }
                }
            }

        };
        swingWorker.execute();
    }

    public static void main(String[] args) {
        JFXUtilities.runInFX(new Runnable() {
            @Override
            public void run() {
                LOGGER.debug("Entro");
                singleton.init();
                singleton.caricaArchivioThread();
                if (!Applicazione.getIstance().getSetting().isMostrareSplashScreen()) {
                    singleton.start(new Stage());
                }
                LOGGER.debug("Esco");
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        vistaPrincipale.load();
        Scene scene = new Scene(vistaPrincipale.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest((e) -> {
            try {
                dAOGenericoJson.salva(setting, Constanti.PERCORSO_SETTING);
            } catch (DAOException ex) {
                LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
                ex.printStackTrace();
            }
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setResizable(false);
        primaryStage.show();
       
    }

}
