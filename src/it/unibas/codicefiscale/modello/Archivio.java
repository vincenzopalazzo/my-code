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
package it.unibas.codicefiscale.modello;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author https://github.com/vincenzopalazzo
 */
public class Archivio{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Archivio.class);
    
    private List<Persona> codiciGenerati = new ArrayList<Persona>();
    private List<Comune> comuni = new ArrayList<Comune>();

    public List<Comune> getComuni() {
        return comuni;
    }
    
    public void addComune(Comune comune){
        comuni.add(comune);
    }

    public List<Persona> getCodiciGenerati() {
        return codiciGenerati;
    }
    
    public void addPersona(Persona persona){
        codiciGenerati.add(persona);
    }
    
    public String searchSubistring(String stringa, int indice){
	int dimenzioneStringa = stringa.length();
	LOGGER.debug("Sto analizzando la stringa: " + stringa + " Con dimenzione: " + dimenzioneStringa);
	for(Comune comune : comuni){
	    LOGGER.debug("Dimenzione Stringa: " + dimenzioneStringa);
	    LOGGER.debug("Indice: " + indice);
	    LOGGER.debug("Stringa con cui confronto: " + comune.getNome().substring(0, dimenzioneStringa));
	    if(stringa.equalsIgnoreCase(comune.getNome().substring(0, dimenzioneStringa - indice))){
		LOGGER.debug("Potrebbe essere : " + comune.getNome());
		return comune.getNome();
	    }
	}
	LOGGER.debug("Ricerca fallita");
	indice++;
	if(stringa.substring(0, dimenzioneStringa - indice).isEmpty()){
	    return null;
	}
//	LOGGER.debug("Ora analizzo: " + stringa.substring(0, indice - 1));
//	LOGGER.debug("Con indice: " + indice);
	return searchSubistring(stringa.substring(0, dimenzioneStringa - 1), indice);
    }
    
    public String serarcString(String immissione){
	for(Comune comune : comuni){
	    if(immissione.trim().equalsIgnoreCase(comune.getNome())){
		return comune.getNome();
	    }
	}
	return null;
    }
    
    public Comune getComune(String nome, String provincia){
	LOGGER.debug("Provincia prelevata: " + provincia);
	LOGGER.debug("Nome comune: " + nome);
	for(Comune comune : comuni){
	    if(comune.getNome().trim().equalsIgnoreCase(nome.trim())){
		if(comune.getSiglaProvincia().trim().equalsIgnoreCase(provincia.trim())){
		    return comune;
		}
	    }
	}
	return null;
    }

    public boolean isPresent(String citta) {
        for(Comune comune : comuni){
            LOGGER.debug("Comune che sto analizzando: " + comune.getNome() + " comune de voglio trovare: " + citta);
            if(comune.getNome().trim().equalsIgnoreCase(citta.trim())){
                return true;
            }
        }
        return false;
    }

    public Comune serachComune(String testoInserito) {
        for(Comune comune : comuni){
            if(comune.getNome().trim().equalsIgnoreCase(testoInserito.trim())){
                return comune;
            }
        }
        return null;
    }
}
