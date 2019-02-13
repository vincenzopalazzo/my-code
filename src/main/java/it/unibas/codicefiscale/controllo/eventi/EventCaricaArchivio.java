package it.unibas.codicefiscale.controllo.eventi;

import it.unibas.codicefiscale.Constanti;
import it.unibas.codicefiscale.GestoreApp;
import it.unibas.codicefiscale.modello.Archivio;
import it.unibas.codicefiscale.persistenza.DAOException;
import it.unibas.codicefiscale.persistenza.IDAOArchivio;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Generated;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class EventCaricaArchivio extends EventProgressBar {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventCaricaArchivio.class);

    @Override
    protected Void call() throws DAOException {
        Archivio archivio = GestoreApp.getIstance().getArchivio();
        try {
            archivio = GestoreApp.getIstance().getDaoArchivio().carica(Constanti.NOMEFILE);
        } catch (DAOException e) {
            LOGGER.error("Errore del tipo: " + e.getLocalizedMessage());
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    protected synchronized void succeeded() {
        boolean splashScreenAbilitato = GestoreApp.getIstance().getSetting() != null &&
                GestoreApp.getIstance().getSetting().isMostrareSplashScreen();

        if (GestoreApp.getIstance().getFrameFXController() != null) {
            LOGGER.debug("FrameController ok");
            if (splashScreenAbilitato) {
                LOGGER.debug("Spalsch screen ok");
                GestoreApp.getIstance().getFrameFXController().settComboProvincia();
            }
            double valore = (double) GestoreApp.getIstance().getModello().getBean(Constanti.VALORE_PROGRESS_BAR) + 0.5;
            GestoreApp.getIstance().getModello().putBean(Constanti.VALORE_PROGRESS_BAR, valore);
            updateProgress(valore, 0); //Guarda il codice sorgente per capire le ultime tre istruzioni
            return;
        }
        LOGGER.debug("FrameController null");
        GestoreApp.getIstance().run();
    }


    @Override
    protected void failed() {
        LOGGER.error("Fallimento caricamento");
        //TODO gestire il caso di fallimento di caricamento thread
    }
}
