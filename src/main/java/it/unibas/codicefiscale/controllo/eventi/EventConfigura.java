package it.unibas.codicefiscale.controllo.eventi;

import it.unibas.codicefiscale.Constanti;
import it.unibas.codicefiscale.GestoreApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class EventConfigura extends EventProgressBar {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventConfigura.class);

    @Override
    protected synchronized Void call() throws Exception {
        //TODO questo metodo dovrebbe verficare le impostazioni e nuove versioni dell'app
        LOGGER.debug("Sto chiamando la funzione call di EventConfigura");
        double valore = (double) GestoreApp.getIstance().getModello().getBean(Constanti.VALORE_PROGRESS_BAR) + 0.5;
        GestoreApp.getIstance().getModello().putBean(Constanti.VALORE_PROGRESS_BAR, valore);
        updateProgress(valore, 0); // il valore max assumera sempre il valore massimo imposto nella supeclasse padre, guarda il codice.
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() { //Non funziona questo incremento, rivedere il codice, ho cancellato un po di cose e non riesco a rimetterlo apposto per ora
            @Override
            public synchronized void run() {
                LOGGER.debug("Chiamo carica archivio in timer thread");
                GestoreApp.getIstance().getEventMedietor().runEvent(Constanti.CARICA_ARCHIVIO_EVENTO);
            }
        }, 5000);
        return null;
    }

}
