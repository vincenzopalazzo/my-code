package it.unibas.codicefiscale.controllo.eventi;

import it.unibas.codicefiscale.Constanti;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class EventMedietor {

    private Map<String, Object> eventi = new HashMap<>();

    public EventMedietor() {
        eventi.put(Constanti.CARICA_ARCHIVIO_EVENTO, new EventCaricaArchivio());
        eventi.put(Constanti.CONFIGURA_EVENTO, new EventConfigura());
    }
    
    

    public void addEvent(String key, Object evento){
        if (evento != null){
            eventi.put(key, evento);
        }
    }

    public synchronized void runEvent(String key){
        if(!eventi.containsKey(key)){
            throw new IllegalArgumentException("Key non contenuta all'interno della cache " + key);
        }
        Task task = (Task) eventi.get(key);
        Platform.runLater(task);
    }
}
