package it.unibas.codicefiscale.controllo.eventi;

import it.unibas.codicefiscale.Constanti;
import it.unibas.codicefiscale.GestoreApp;
import it.unibas.codicefiscale.persistenza.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class EventCaricaArchivio extends EventProgressBar {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventCaricaArchivio.class);

    @Override
    protected synchronized Void call() throws DAOException {
        try {
            GestoreApp.getIstance().loadArchivio();
        } catch (DAOException e) {
            LOGGER.error("Errore del tipo: " + e.getLocalizedMessage());
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    protected synchronized void succeeded() {
        boolean splashScreenAbilitato = GestoreApp.getIstance().getSetting() != null
                && GestoreApp.getIstance().getSetting().isMostrareSplashScreen();

        if (GestoreApp.getIstance().getFrameFXController() != null) {
            LOGGER.debug("FrameController ok");
            if (splashScreenAbilitato) {
                LOGGER.debug("Spalsch screen ok");
                //GestoreApp.getIstance().getFrameFXController().settComboProvincia();
            }
            if (GestoreApp.getIstance().getSetting().isMostrareSplashScreen()) {
                double valore = (double) GestoreApp.getIstance().getModello().getBean(Constanti.VALORE_PROGRESS_BAR) + 0.5;
                GestoreApp.getIstance().getModello().putBean(Constanti.VALORE_PROGRESS_BAR, valore);
                updateProgress(valore, 0);
            }else{
                GestoreApp.getIstance().run();
            }
            //Guarda il codice sorgente per capire le ultime tre istruzioni
            return;
        }
        LOGGER.debug("FrameController null");
        
    }

    @Override
    protected void failed() {
        LOGGER.error("Fallimento caricamento");
        //TODO gestire il caso di fallimento di caricamento thread
    }
}
