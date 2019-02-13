package it.unibas.codicefiscale.controllo.eventi;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class EventMedietor {

    private Map<String, Object> eventi = new HashMap<>();

    public void addEvent(String key, Object evento){
        if (evento != null){
            eventi.put(key, evento);
        }
    }

    public void runEvent(String key){
        if(!eventi.containsKey(key)){
            throw new IllegalArgumentException("Key non contenuta all'interno della cache");
        }
        Task task = (Task) eventi.get(key);
        Platform.runLater(task);
    }
}
