package com.mycompany.myapp.gui;

import com.codename1.location.GeofenceListener;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ons
 */

    public class GeofenceListenerImpl implements GeofenceListener {
    @Override
    public void onExit(String id) {
    }

    @Override
    public void onEntered(String id) {
        if(Display.getInstance().isMinimized()) {
            Display.getInstance().callSerially(() -> {
                Dialog.show("Welcome", "Thanks for arriving", "OK", null);
            });
        } else {
            LocalNotification ln = new LocalNotification();
            ln.setId("LnMessage");
            ln.setAlertTitle("Welcome");
            ln.setAlertBody("Thanks for arriving!");
            Display.getInstance().scheduleLocalNotification(ln, 10, LocalNotification.REPEAT_NONE);
        }
    }    
}
