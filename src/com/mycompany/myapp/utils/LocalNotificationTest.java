/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;



import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.AjouterEvenement;
import com.mycompany.myapp.gui.ProfileForm;
import com.mycompany.myapp.gui.listEvenement;

import java.io.IOException;

public class LocalNotificationTest implements LocalNotificationCallback {

    private Form current;
    private Resources theme;
    

    public void init(Object context) {
        try {
            theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
            e.printStackTrace();
     }
   
    }
    int badgeNumber = 0;
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form hi = new Form("  Notification");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        hi.addComponent(new Label("ID"));
        final TextField id = new TextField();
        id.setUIID("FloatingActionButton");
        
        hi.addComponent(id);
        
        hi.addComponent(new Label("Title"));
        final TextField title = new TextField();
         title.setUIID("FloatingActionButton");
        hi.addComponent(title);
        
        hi.addComponent(new Label("Body"));
        final TextField body = new TextField();
         body.setUIID("FloatingActionButton");
        hi.addComponent(body);
        
        
        hi.addComponent(new Label("Interval"));
        final ComboBox interval = new ComboBox(new Object[]{ "None", "Minute", "Hour", "Day", "Week"});
        hi.addComponent(interval);
        
          
       
          
          
        Button b = new Button("Send Notification");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalNotification n = new LocalNotification();
                n.setAlertBody(body.getText());
                n.setAlertTitle(title.getText());
                n.setId(id.getText());
                n.setBadgeNumber(badgeNumber++);

                int repeatType = LocalNotification.REPEAT_NONE;
                String selRepeat = (String)interval.getModel().getItemAt(interval.getModel().getSelectedIndex());
                if ("Minute".equals(selRepeat)) {
                    repeatType = LocalNotification.REPEAT_MINUTE;
                } else if ("Hour".equals(selRepeat)) {
                    repeatType = LocalNotification.REPEAT_HOUR;
                } else if ("Day".equals(selRepeat)) {
                    repeatType = LocalNotification.REPEAT_DAY;
                } else if( "Week".equals(selRepeat)) {
                    repeatType = LocalNotification.REPEAT_WEEK;
                }
                System.out.println("before display notification");
                Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, repeatType);
            }
        });
        hi.addComponent(b);
        
        Button cancel = new Button("Cancel Notification");
            
        cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().cancelLocalNotification(id.getText());
            }
            
        });
        
        
        hi.addComponent(cancel);
        
        Button clearBadgeNumber = new Button("Clear Badge");
        clearBadgeNumber.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().setBadgeNumber(0);
            }
        });
        hi.addComponent(clearBadgeNumber);
            // toolbar stuff
            
                                 Toolbar tb = hi.getToolbar();
Container topBar = BorderLayout.east(new Label());
topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline")); 
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);
tb.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, e -> {

});

/*tb.addMaterialCommandToSideMenu("Ajouter evenement", FontImage.MATERIAL_TRENDING_UP, e -> {
    AjouterEvenement ar = new AjouterEvenement();                 
                ar.show();
});

tb.addMaterialCommandToSideMenu("Mes evenement", FontImage.MATERIAL_LIST, e -> {
                 new ReclamationController().ListeReclamations();

}); */
 tb.addCommandToRightBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new listEvenement(theme).show();        
}    } );
tb.addMaterialCommandToSideMenu("Notify me", FontImage.MATERIAL_SETTINGS, e -> {
                LocalNotificationTest l= new LocalNotificationTest();
                l.start();
                System.out.println("end start notification");
            LocalNotification n = new LocalNotification();
                n.setAlertBody("bodyy");
                n.setAlertTitle("titre");
                n.setId("1");
                n.setBadgeNumber(2);

                int repeatType = LocalNotification.REPEAT_MINUTE;
                Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() +1 * 1000, repeatType);

});
                                    // end toolbar stuff
     
                                    
                                 
        hi.show();
    }
    
    
    

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }

    public void localNotificationReceived(String notificationId) {
        System.out.println("Received local notification "+notificationId+" in callback localNotificationReceived");
    }

}
