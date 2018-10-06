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

import java.util.Objects;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class Comune implements Comparable<Comune>{
    
    private String nome;
    private String siglaProvincia;
    private String codice;

    public Comune(String nome, String siglaProvincia, String codice) {
        this.nome = nome;
        this.siglaProvincia = siglaProvincia;
        this.codice = codice;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSiglaProvincia(String siglaProvincia) {
        this.siglaProvincia = siglaProvincia;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Comune() {
    }

    public String getNome() {
        return nome;
    }

    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    public String getCodice() {
        return codice;
    }

    @Override
    public String toString() {
        StringBuilder stringa = new StringBuilder();
        stringa.append(nome).append(" ").append(siglaProvincia).append(" ").append(codice);
        return stringa.toString();
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 79 * hash + Objects.hashCode(this.nome);
	hash = 79 * hash + Objects.hashCode(this.siglaProvincia);
	return hash;
    }

    @Override
    public boolean equals(Object comune) {
	Comune comune1 = (Comune) comune;
	if(!this.nome.trim().equalsIgnoreCase(comune1.getNome())){
	    return false;
	}
	if(!this.siglaProvincia.trim().equalsIgnoreCase(comune1.getSiglaProvincia())){
	    return false;
	}
	return true;
    }

    @Override
    public int compareTo(Comune o) {
	return this.siglaProvincia.compareTo(o.getSiglaProvincia());
    }
    
    
    
    
    

}
