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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author https://github.com/vincenzopalazzo, unibas
 */

public class DAOGenericoJson {

    private static String TAG = "DAOGenerico";
    private String datePatternFormat = "dd-MM-yyyy HH:mm:ss";

    /* ******************************************
     *               Conversione
     * ****************************************** */
    public String toJson(Object oggetto){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new AdapterDate());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(oggetto);
    }

    /* ******************************************
     *               Caricamento
     * ****************************************** */
    public Object carica(InputStream inputStream, Class t) throws DAOException {
        Object oggetto = null;
        Reader flusso = null;
        try {
            flusso = new InputStreamReader(inputStream);
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new AdapterDate());
            Gson gson = builder.create();
            oggetto = gson.fromJson(flusso, t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            try {
                if (flusso != null) {
                    flusso.close();
                }
            } catch (java.io.IOException ioe) {
            }
        }
        return oggetto;
    }

    /* ******************************************
     *               Salvataggio
     * ****************************************** */
    public void salva(Object oggetto, String percorso) throws DAOException {
        PrintWriter flusso = null;
        try {
            flusso = new java.io.PrintWriter(percorso);
            String stringaJson = toJson(oggetto);
            flusso.print(stringaJson);
        } catch (Exception ioe) {
            throw new DAOException(ioe);
        } finally {
            if (flusso != null) {
                flusso.close();
            }
        }

    }

    private class AdapterDate implements JsonSerializer<Date>, JsonDeserializer<Date> {

        public JsonElement serialize(Date date, Type tipo, JsonSerializationContext context) {
            DateFormat dateFormat = new SimpleDateFormat(datePatternFormat);
            return new JsonPrimitive(dateFormat.format(date.getTime()));
        }

        public Date deserialize(JsonElement json, Type tipo, JsonDeserializationContext context) throws JsonParseException {
            try {
                String stringaData = json.getAsString();
                DateFormat dateFormat = new SimpleDateFormat(datePatternFormat);
                Date dataRegistrazione = dateFormat.parse(stringaData);
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(dataRegistrazione);
                return calendar.getTime();
            } catch (ParseException ex) {
                throw new JsonParseException(ex);
            }
        }
    }

    public String getDatePatternFormat() {
        return datePatternFormat;
    }

    public void setDatePatternFormat(String datePatternFormat) {
        this.datePatternFormat = datePatternFormat;
    }
}
