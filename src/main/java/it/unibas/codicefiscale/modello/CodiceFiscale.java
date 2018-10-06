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

import it.unibas.codicefiscale.Constanti;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author https://github.com/vincenzopalazzo, Stefano Foti.
 */
public class CodiceFiscale {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private Archivio archivio;

    public CodiceFiscale(Archivio archivio) {
	this.archivio = archivio;
    }

    public CodiceFiscale() {
    }

    public Archivio getArchivio() {
	return archivio;
    }

    public void setArchivio(Archivio archivio) {
	this.archivio = archivio;
    }

    public String getCodiceFiscale(Persona persona) {
	StringBuilder codiceFiscale = new StringBuilder();
	codiceFiscale.append(calcolaTerna(persona.getCognome(), true));
	codiceFiscale.append(calcolaTerna(persona.getNome(), false));
	String annoCodiceFiscale = estraiAnno(persona.getDataNascita().get(GregorianCalendar.YEAR));
	logger.debug("Anno estratto per il codice fiscale: " + annoCodiceFiscale);
	codiceFiscale.append(annoCodiceFiscale);
	codiceFiscale.append(alfabetoMese(persona.getDataNascita().get(GregorianCalendar.MONTH)));
	codiceFiscale.append(estraiGiorno(persona));
	if (archivio != null && !archivio.getComuni().isEmpty()) {
	    logger.debug("Archivio non nullo quindi  insierisco la sigla comune");
	    codiceFiscale.append(calcolaSiglaComune(persona));
	    logger.debug("Codice fiscale calcolatro senza carattere di controllo: " + codiceFiscale);
	    codiceFiscale.append(carattereControllo(persona, codiceFiscale));
	    logger.debug(("Dovrebbe essere il codice fiscale definitivo: " + codiceFiscale));
	} else {
	    logger.debug("Qualcosa e' andato storto");
	}
	logger.debug("Codice fiscale calcolato: " + codiceFiscale.toString());
	return codiceFiscale.toString();
    }

    private boolean verificaContenuta(String controllo, char carattere) {
	Character stringa = (Character) carattere;
	return controllo.toLowerCase().contains(stringa.toString());
    }

    private String estraiPerTipo(String stringa, String tipoEstrazione) {
	StringBuilder sb = new StringBuilder();
	char[] caratteri = stringa.toLowerCase().toCharArray();
	logger.debug("Dimensione caratteri: " + caratteri.length);
	for (int i = 0; i < caratteri.length; i++) {
	    if (verificaContenuta(tipoEstrazione, caratteri[i])) {
		logger.debug("consonate aggiunta: " + caratteri[i]);
		Character tmp = (Character) caratteri[i];
		sb.append(tmp);
	    }
	}
	return sb.toString();
    }

    public String calcolaTerna(String stringa, boolean isCognome) {
	StringBuilder sb = new StringBuilder();
	stringa = stringa.replace(" ", "");
	logger.debug("DEBUG senza pazi: " + stringa);
	String stringaConsonanti = estraiPerTipo(stringa, Constanti.CONSONANTI__STRING);
	// fine debug
	if (stringaConsonanti.length() >= 4 && !isCognome) {
	    logger.debug("*** Debug stringa consonanti: " + stringaConsonanti);
	    sb.append(stringaConsonanti.charAt(0)).append(stringaConsonanti.substring(2, 4));
	    return sb.toString().toUpperCase();
	}
	//TODO se il nome contiene meno di 3 consonanti, fare test su luca.
	if (stringaConsonanti.length() >= 3) {
	    return stringaConsonanti.substring(0, 3).toUpperCase();
	}
	if(stringaConsonanti.length() < 3){
	    String vocali = estraiPerTipo(stringa, Constanti.VOCALI__STRING);
	    logger.debug("Stringa vocali: " + vocali);
	}
	sb.append(stringaConsonanti);
	int lunghezza = stringaConsonanti.length();
	String stringaVocali = estraiPerTipo(stringa, Constanti.VOCALI__STRING);
	sb.append(stringaVocali).append("XXX");
	return sb.toString().substring(0, 3).toUpperCase();
    }

    private String estraiAnno(int anno) {
	if (anno < 1000) {
	    logger.error("Errore nella formattazione dell'anno");
	    throw new IllegalArgumentException("Errore nella formattazione dell'anno");
	}
	Integer annoInteger = (Integer) anno;
	String annoString = annoInteger.toString();
	logger.debug("anno convertito in anno: " + annoString);
	return annoString.substring(2, 4);
    }

    private String alfabetoMese(int mese) {
	logger.debug("DEBUG mese: " + mese + " dimenzione lista: " + Constanti.CONSTANTI_ALFABETO.length);
	if (mese < 0 || mese > 11) {
	    logger.error("Errore mese non valido");
	    throw new IllegalArgumentException("Mese non corretto");
	}
	for (int i = 0; i < Constanti.CONSTANTI_ALFABETO.length; i++) {
	    if (mese == i) {
		return Constanti.CONSTANTI_ALFABETO[i];
	    }
	}
	return null; // Non dovrebbe mai tornare null.
    }

    private String estraiGiorno(Persona persona) {
	Integer giornoData = persona.getDataNascita().get(GregorianCalendar.DAY_OF_MONTH);
	if (persona.getSesso().equalsIgnoreCase("m")) {
	    if (giornoData < 10) {
		return "0" + giornoData.toString();
	    }
	    return giornoData.toString();
	}
	giornoData += 40;
	return giornoData.toString();
    }

    public String calcolaSiglaComune(Persona persona) {
	if (archivio == null || archivio.getComuni().isEmpty()) {
	    logger.error("Errone, archivio non inizializzato oppure lista comuni vuota");
	    throw new IllegalArgumentException("Errore nell'calcolo della sigla, qualcosa non inizializzato");
	}
	String codice = new String();
	for (Comune comune : archivio.getComuni()) {
	    logger.debug("Comune della persona: " + persona.getLuogoNascita());
	    logger.debug("Comune dell'archivio: " + comune.getNome());
	    if (persona.getLuogoNascita().trim().equalsIgnoreCase(comune.getNome().trim())) {
		logger.debug("sigla prov della persona: " + persona.getLuogoNascita());
		logger.debug("sigla prov dell'archivio: " + comune.getSiglaProvincia());
		if (persona.getSiglaProvincia().trim().equalsIgnoreCase(comune.getSiglaProvincia().trim())) {
		    logger.debug("Codice trovato: " + comune.getCodice());
		    codice = comune.getCodice();
		}
	    }
	}
	if (codice == null) {
	    logger.debug("Qualcosa e' andato storto nel calcolo dell codice, non è stato trovato niente di compatibile");
	    throw new IllegalArgumentException("Qualcosa è andato storto durante il calcolo delc codice comune");
	}
	return codice;
    }

    private String carattereControllo(Persona persona, StringBuilder codiceFiscale) {
	if (persona == null) {
	    logger.error("Persona in input nulla");
	    throw new IllegalArgumentException("Persona in input nulla");
	}
	double sommaPari = calcolaSommaPositiva(codiceFiscale);
	logger.debug("valore della somma pari: " + sommaPari);
	double sommaDispari = calcolaSommaDispari(codiceFiscale);
	logger.debug("Valore della somma dispari: " + sommaDispari);
	double valore = (sommaPari + sommaDispari) / 26;
	logger.debug("Valore prima l'operazione di arrotondamento: " + valore);
	int valoreFinale = (int) valore;
	logger.debug("Solo parte decimale: " + valoreFinale);
	valoreFinale = valoreFinale * 26;
	logger.debug("Moltiplicato per il dividendo " + valoreFinale);
	valoreFinale = (int) (sommaDispari + sommaPari) - valoreFinale;
	logger.debug("il valore calcolato e': " + valoreFinale);
	if (valoreFinale < 0 | valoreFinale > Constanti.CARATTERE_CONTROLLO.length) {
	    //TODO qualcosa non va
	    logger.debug("non esiste nessun carattere di controllo per valore calcolato");
	    throw new IllegalArgumentException("Nessun valore esistente per il valore di controllo");
	}
	logger.debug("Sto tornando il carattere di controllo: " + Constanti.CARATTERE_CONTROLLO[valoreFinale]);
	return Constanti.CARATTERE_CONTROLLO[valoreFinale];
    }

    private int calcolaSommaPositiva(StringBuilder codiceFiscale) {
	if (codiceFiscale == null) {
	    logger.debug("Il codice fiscale in imputi e nullo, non posso calcolare la somma pari");
	    throw new IllegalArgumentException("Errore di inizializzazione");
	}
	int sommaPari = 0;
	char[] caratteriCodiceFiscale = codiceFiscale.toString().toCharArray();
	for (int i = 1; i < codiceFiscale.length(); i = i + 2) {
	    logger.debug("iterazione ciclo pari: " + i);
	    Integer valoreCorr = trovaValoreCorrispontente(true, caratteriCodiceFiscale[i]);
	    if (valoreCorr == null) {
		//TODO dobbiamo fare qualcosa
		logger.error("Questo non doveva succedere, valore corrispontente nullo");
		throw new IllegalArgumentException("Qualcosa e' andato per il verso sbagliato");
	    }
	    sommaPari += (valoreCorr);
	    logger.debug("La somma pari e' fino a questo momento e': " + sommaPari);
	}
	return sommaPari;
    }

    private int calcolaSommaDispari(StringBuilder codiceFiscale) {
	if (codiceFiscale == null) {
	    logger.debug("Il codice fiscale in imputi e nullo, non posso calcolare la somma pari");
	    throw new IllegalArgumentException("Errore di inizializzazione");
	}
	int sommaDispari = 0;
	char[] caratteriCodiceFiscale = codiceFiscale.toString().toCharArray();
	for (int i = 0; i < codiceFiscale.length(); i = i + 2) {
	    logger.debug("iterazione ciclo dispari: " + i);
	    Integer valoreCorr = trovaValoreCorrispontente(false, caratteriCodiceFiscale[i]);
	    if (valoreCorr == null) {
		//TODO dobbiamo fare qualcosa
		logger.error("Questo non doveva succedere, valore corrispontente nullo");
		throw new IllegalArgumentException("Qualcosa e' andato per il verso sbagliato");
	    }
	    sommaDispari += valoreCorr;
	    logger.debug("La somma dispari fino a questo momento e': " + sommaDispari);
	}
	return sommaDispari;
    }

    private Integer trovaValoreCorrispontente(boolean sommaPariODispari, char carattere) {
	logger.debug("carattere del codice fiscale preso in considerazione: " + carattere);
	if (sommaPariODispari) {
//            for (int i = 0; i < Constanti.PARI.length; i++) {
//                String carString = String.valueOf(carattere);
//                if (carString.trim().equalsIgnoreCase(Constanti.PARI[i].trim())) {
//                    return i;
//                }
//            }
	    for (int i = 0; i < Constanti.PARI.length; i++) {
		StringTokenizer token = new StringTokenizer(Constanti.PARI[i], "=");
		logger.debug("numero di token preso dalla stringa: " + token.countTokens());
		String carString = String.valueOf(carattere);
		String carattereDiConfronto = token.nextToken();
		logger.debug("Valore di confronto: " + carattereDiConfronto);
		if (carString.trim().equalsIgnoreCase(carattereDiConfronto.trim())) {
		    String valore = token.nextToken();
		    logger.debug("Vado ad aggiungere: " + valore);
		    return Integer.parseInt(valore);
		}
	    }
	    return null;
	}
	for (int i = 0; i < Constanti.DISPARI.length; i++) {
	    StringTokenizer token = new StringTokenizer(Constanti.DISPARI[i], "=");
	    logger.debug("numero di token preso dalla stringa: " + token.countTokens());
	    String carString = String.valueOf(carattere);
	    String carattereDiConfronto = token.nextToken();
	    logger.debug("Valore di confronto: " + carattereDiConfronto);
	    if (carString.trim().equalsIgnoreCase(carattereDiConfronto.trim())) {
		String valore = token.nextToken();
		logger.debug("Vado ad aggiungere: " + valore);
		return Integer.parseInt(valore);
	    }
	}
	return null;
    }
}
