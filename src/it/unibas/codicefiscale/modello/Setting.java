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

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class Setting {
  
    private boolean mostrarePannelloFeed = true;
    private boolean mostrareSplashScreen = true;
    private boolean notificaScura = true;
    private String avatarScelto = Constanti.ICON_SCEGLI_AVATAR;
    private boolean interfacciaSmart = true;
    private boolean notificheAbilitate = true;

    public boolean isNotificheAbilitate() {
        return notificheAbilitate;
    }

    public void setNotificheAbilitate(boolean notificheAbilitate) {
        this.notificheAbilitate = notificheAbilitate;
    }
    
    //TODO gestire il cambio di tema.

    public boolean isInterfacciaSmart() {
        return interfacciaSmart;
    }

    public void setInterfacciaSmart(boolean interfacciaSmart) {
        this.interfacciaSmart = interfacciaSmart;
    }
    
    

    public String getAvatarScelto() {
        return avatarScelto;
    }

    public void setAvatarScelto(String avatarScelto) {
        this.avatarScelto = avatarScelto;
    }

    public boolean isMostrareSplashScreen() {
        return mostrareSplashScreen;
    }

    public void setMostrareSplashScreen(boolean mostrareSplashScreen) {
        this.mostrareSplashScreen = mostrareSplashScreen;
    }

    public boolean isNotificaScura() {
        return notificaScura;
    }

    public void setNotificaScura(boolean notificaScura) {
        this.notificaScura = notificaScura;
    }

    
    public boolean isMostrarePannelloFeed() {
        return mostrarePannelloFeed;
    }

    public void setMostrarePannelloFeed(boolean mostrarePannelloFeed) {
        this.mostrarePannelloFeed = mostrarePannelloFeed;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Setting setting = new Setting();
        setting.setAvatarScelto(this.avatarScelto);
        setting.setInterfacciaSmart(this.interfacciaSmart);
        setting.setMostrarePannelloFeed(this.mostrarePannelloFeed);
        setting.setMostrareSplashScreen(this.mostrareSplashScreen);
        setting.setNotificaScura(this.notificaScura);
        setting.setNotificheAbilitate(this.notificheAbilitate);
        return setting;
    }
    
    
    
    
}
