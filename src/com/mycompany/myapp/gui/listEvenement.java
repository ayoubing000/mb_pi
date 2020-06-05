/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ons
 */
public class listEvenement extends Form {
     Form f;

    public listEvenement(Resources res) {
  
       
        
        
     super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
          tb.addCommandToRightBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new ProfileForm(res).show();        
}    } );
        Image profilePic = res.getImage("onspic.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

     //   Button menuButton = new Button("");
      //  menuButton.setUIID("Title");
      //  FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        // menuButton.addActionListener(e -> getToolbar().openSideMenu());
       

        Container titleCmp = BoxLayout.encloseY(
              //  FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(" Mes Evenements", "Title")
                        )
                )
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.setVisible(focusScrolling);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                   ServiceEvenement sp=new ServiceEvenement();
                ArrayList<Evenement>pl=sp.getListall();
           //  setupSideMenu();
     EncodedImage enc=null;
                try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
            
        }
    
                   Component[] listingsToAdd = new Component[pl.size()];
                     for(int iter = 0 ; iter < sp.getListall().size() ; iter++) {
                         final Evenement p=pl.get(iter);
                         String url=Statics.BASE_URL +"/jardin/web/uploads/events_photos/"+p.getImage();
                       
                         MultiButton mb = new MultiButton();
                                                   mb.setIcon(URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL));

                            
            mb.setTextLine1(pl.get(iter).getTitre());
            
            DateFormat ddd = new SimpleDateFormat("yyyy-MM-dd");
            mb.setTextLine2(ddd.format(pl.get(iter).getDate_debut()));
            mb.setUIID("text");

                         add(mb);
                      
                          mb.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent ev) {
                                  System.out.println(p.getId());
                                           new  detailsEvenement(res,p.getId()).show();
                                 
                                    }
                         });
                       
                     }
                 
                     
                     
                     
                     /*    tb.addCommandToSideMenu("liste des evenements", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new listEvenementImage().show();        
}    } );
          tb.addCommandToSideMenu("ajouter des evenements", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new AjouterEvenement().show();        
}    } ); 
    */
          
          
          
          
            
}

   /* @Override
    protected void showOtherForm(Resources res) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } */

   
   

   
}
