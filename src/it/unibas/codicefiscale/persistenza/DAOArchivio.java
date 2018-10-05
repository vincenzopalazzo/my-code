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
package it.unibas.codicefiscale.persistenza;

import it.unibas.codicefiscale.modello.Archivio;
import it.unibas.codicefiscale.modello.Comune;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class DAOArchivio implements IDAOArchivio{

    private static final Logger logger = LoggerFactory.getLogger(DAOArchivio.class);

    public Archivio carica(String nomeFile) throws DAOException {
        BufferedReader in = null;
        Archivio archivio = new Archivio();
        try {
            FileReader file = new FileReader(nomeFile);
            in = new BufferedReader(file);
            archivio = caricaDaFile(in);
        } catch (Exception e) {
            throw new DAOException("Si e' verificato un errore di tipo " + e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    logger.error("Errore non atteso, errore irreversibile sulla chiusura dell flusso  in.");
                }
            }
        }
        return archivio;
    }

    private Archivio caricaDaFile(BufferedReader input) throws IOException{
        Archivio archivio = new Archivio();
        String stringa;
        while ((stringa = input.readLine()) != null) {
            StringTokenizer toke = new StringTokenizer(stringa ,"_");
            Comune comune = new Comune();
            String appoggioToken = toke.nextToken();
            logger.debug("Sto settando il nome del comune che e': " + appoggioToken);
            comune.setNome(appoggioToken);
            appoggioToken = toke.nextToken();
            logger.debug("Sto settando la sigla del comune che e': " + appoggioToken);
            comune.setSiglaProvincia(appoggioToken);
            appoggioToken = toke.nextToken();
            logger.debug("Sto settando il codice del comune che e': " + appoggioToken);
            comune.setCodice(appoggioToken);
            logger.debug("Aggiungo il seguente comune: " + comune);
            archivio.addComune(comune);
            logger.debug("dimenzione lista comune: " + archivio.getComuni().size());
        }
	Collections.sort(archivio.getComuni());
        logger.debug("Dovrei aver finito. OK");
        return archivio;
    }
}
