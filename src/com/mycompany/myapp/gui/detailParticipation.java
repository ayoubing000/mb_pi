/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MediaPlayer;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceDetailsEvenement;
import com.mycompany.myapp.services.ServiceParticipation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author ons
 */
public class detailParticipation extends Form {

    Form form;
    Form f;
    Form hi;

    public detailParticipation(Resources res, int id) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Toolbar tb = getToolbar();
        setUIID("lolo");
        Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, s);
hi.getToolbar().addCommandToRightBar(new Command("", icon) {
    @Override
    public void actionPerformed(ActionEvent evt) {
        Display.getInstance().openGallery((e) -> {
            if(e != null && e.getSource() != null) {
                String file = (String)e.getSource();
                try {
                    Media video = MediaManager.createMedia(file, true);
                    hi.removeAll();
                    hi.add(BorderLayout.CENTER, new MediaPlayer(video));
                    hi.revalidate();
                } catch(IOException err) {
                    Log.e(err);
                } 
            }
        }, Display.GALLERY_VIDEO);
    }
});
hi.show();

        // super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        //      Toolbar tb = getToolbar();
        tb.addCommandToRightBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new ProfileForm(res).show();        
}    } );

        setTitle("List Evenement");
        setLayout(BoxLayout.y());
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        // FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        // menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Mes evenements", "Title")
                        )
                )
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.setVisible(focusScrolling);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        //setupSideMenu(res);
        

       
        
        
        
        
      EncodedImage enc=null;
                  
                 ServiceDetailsEvenement sp = new ServiceDetailsEvenement();
                 System.out.println(sp.getdet(id));
        Evenement i = new Evenement();
        Evenement evenement = sp.getdet(id);
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
            
        }
                  
        add(new Label(evenement.getTitre(), "Title"));
       
                         String url=Statics.BASE_URL +"/jardin/web/uploads/events_photos/"+evenement.getImage();
                         add(new Label(URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL)));
 
 
               
        
        Button btnparticiper = new Button("participer");
       
        btnparticiper.setUIID("LoginButton");

        btnparticiper.addActionListener(e -> {

            try {
                ServiceParticipation serviceParticiper = new ServiceParticipation();
                serviceParticiper.participer(evenement);
                
            } catch (Exception ex) {
                System.out.println(ex + "no no");
            }

        }
        );
        
                              
        
         Button btnannuler = new Button("Annuler");
        btnannuler.setUIID("LoginButton");

        btnannuler.addActionListener(e -> {

            try {
                ServiceParticipation serviceParticiper = new ServiceParticipation();
             
                serviceParticiper.AnnulerPartcipation(evenement);
              
            } catch (Exception ex) {
                System.out.println(ex + "no no");
            }

        }
        );
        
       
         
          add(btnparticiper);
          add(btnannuler);
        

        ShareButton sb = new ShareButton();
        Form form = new Form("ShareButton");

        sb.addActionListener(e -> form.show());

        sb.setTextToShare(i.getImage());
       
//hiii.add(sb);

        Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
        form.revalidate();
        form.setVisible(true);
        form.paintComponent(screenshot.getGraphics(), true);

        /*  String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch (IOException err) {
            Log.e(err);

        } */

       
        add(sb);
        

        
        
    }
}
