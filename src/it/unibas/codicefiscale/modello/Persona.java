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

import java.util.GregorianCalendar;

/**
 * 
 * @author https://github.com/vincenzopalazzo
 */

public class Persona {
    
    private String nome;
    private String cognome;
    private String sesso;
    private String luogoNascita;
    private String siglaProvincia;
    private GregorianCalendar dataNascita;

    public Persona(String nome, String cognome, String sesso, String luogoNascita, String siglaProvincia) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.luogoNascita = luogoNascita;
        this.siglaProvincia = siglaProvincia;
        dataNascita = new GregorianCalendar();
        dataNascita.setLenient(false);
    }

    public Persona() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    public void setSiglaProvincia(String siglaProvincia) {
        this.siglaProvincia = siglaProvincia;
    }
    
    public void setDataNascita(int anno, int giorno, int mese){
        dataNascita.set(anno, mese, giorno);
    }

    public void setDataNascita(GregorianCalendar dataNascita) {
	this.dataNascita = dataNascita;
    }
    
    public GregorianCalendar getDataNascita(){
        return this.dataNascita;
    }

    @Override
    public String toString() {
        return "Ancora da sviluppare.";
    }  
}
