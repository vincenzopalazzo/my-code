package it.unibas.codicefiscale.controllo.eventi;

import it.unibas.codicefiscale.Constanti;
import it.unibas.codicefiscale.GestoreApp;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class EventProgressBar extends Task<Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProgressBar.class);

    protected static final double VALUE_PROGRESSION_MAX = 1.0;


    @Override
    //TODO rivedere questo metodo
    protected void updateProgress(double workDone, double valueNull) {
        if(workDone < VALUE_PROGRESSION_MAX){
            LOGGER.debug("progresso da setare: " + workDone);
            ProgressBar progressBar = (ProgressBar) GestoreApp.getIstance().getModello().getBean(Constanti.PROGRESS_BAR);
            progressBar.setProgress(workDone);
            double progresso = (double) GestoreApp.getIstance().getModello().getBean(Constanti.VALORE_PROGRESS_BAR);
            progressBar.setProgress(progresso);
            if(progresso == VALUE_PROGRESSION_MAX){
                LOGGER.debug("Progressione terminata");
                GestoreApp.getIstance().getSplashScreen().kill();
                GestoreApp.getIstance().run();
            }
            return;
        }
        LOGGER.debug("Progress bar ancora non completa");
    }
}
