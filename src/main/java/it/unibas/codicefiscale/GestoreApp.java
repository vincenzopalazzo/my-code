package it.unibas.codicefiscale;

import it.unibas.codicefiscale.controllo.FrameFXController;
import it.unibas.codicefiscale.controllo.SettingPanelController;
import it.unibas.codicefiscale.controllo.SpashScreenController;
import it.unibas.codicefiscale.controllo.eventi.EventCaricaArchivio;
import it.unibas.codicefiscale.controllo.eventi.EventConfigura;
import it.unibas.codicefiscale.controllo.eventi.EventMedietor;
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
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class GestoreApp {

    private static final GestoreApp SINGLETON = new GestoreApp();
    private static final Logger LOGGER = LoggerFactory.getLogger(GestoreApp.class);

    private VistaPrincipale vistaPrincipale = new VistaPrincipale();
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
    private License license;
    private Stage primaryStage;
    private Application app;
    private EventMedietor eventMedietor;

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private GestoreApp() {
    }

    public License getLicense() {
        return license;
    }

    public PannelloLicense getPannelloLicense() {
        return pannelloLicense;
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

    public static GestoreApp getIstance() {
        return SINGLETON;
    }

    public Archivio getArchivio() {
        return archivio;
    }

    public IDAOArchivio getDaoArchivio() {
        return daoArchivio;
    }

    public EventMedietor getEventMedietor() {
        return eventMedietor;
    }

    public void init() {

        /* Serve per far stampare il contesto all'applicazione
        LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
        StatusPrinter.print(context);*/
        eventMedietor = new EventMedietor();
        dAOGenericoJson = new DAOGenericoJson();
        modello = new Modello();
        archivio = new Archivio();

        initSetting();

        license = new License();
        pannelloLicense = new PannelloLicense();
        pannelloSettingAvatar = new PannelloSettingAvatar();
        pannelloSetting = new PannelloSetting();
        pannelloFeed = new PannelloFeed();
        pannelloCopyright = new PannelloCopyright();
        infoAutori = new InfoAutori();
        vistaPrincipale = new VistaPrincipale();
        vistaPrincipale.load();
        frameFXController = new FrameFXController();
        spashScreenController = new SpashScreenController();
        settingPanelController = new SettingPanelController();
    }

    public void run() {
        Scene scene = new Scene(vistaPrincipale.getRoot());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
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
    
    public void callMeIntoMain(){
        init();
        runSplashScreen();
    }

    private void runSplashScreen() {
        boolean flag = SINGLETON.setting != null && SINGLETON.setting.isMostrareSplashScreen();
        if (flag) {
            LOGGER.debug("Splash screen on");
            splashScreen = new SplashScreen();
            splashScreen.init();
        } else {
            LOGGER.debug("Splash screen off");
            getEventMedietor().runEvent(Constanti.CARICA_ARCHIVIO_EVENTO);
        }
    }

    private void initSetting(){
        try {
            //Carico tutti i setting e verifico che ci siano setting sul disco
            setting = (Setting) GestoreApp.getIstance().getdAOGenericoJson().carica(new FileInputStream(Constanti.PERCORSO_SETTING), Setting.class);
            LOGGER.debug("Setting caricato");
            GestoreApp.getIstance().getModello().putBean(Constanti.VISUALIZZA_FEED, setting.isMostrarePannelloFeed());
            GestoreApp.getIstance().setSetting(setting);
        } catch (FileNotFoundException ex) {
            LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
            if (setting == null) {
                setting = new Setting();
                try {
                    GestoreApp.getIstance().getdAOGenericoJson().salva(setting, Constanti.PERCORSO_SETTING);
                    LOGGER.debug("setting salvato");
                    GestoreApp.getIstance().setSetting(setting);
                } catch (DAOException ex1) {
                    LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
                    ex.printStackTrace();
                }
            }
        } catch (DAOException ex) {
            LOGGER.error("Si e' verificato un errore del tipo: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

}
