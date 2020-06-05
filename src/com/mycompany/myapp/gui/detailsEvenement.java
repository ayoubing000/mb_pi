/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceDetailsEvenement;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
//import com.codename1.share.ShareService;

/**
 *
 * @author ons
 */
public class detailsEvenement extends SideMenuBaseForm {
   
   
    public detailsEvenement(Resources res,int id ){
         super(BoxLayout.y());
       
        
          
    Toolbar tb = getToolbar();
  
    
     setTitle("List Evenement");
       setLayout(BoxLayout.y());
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
      
      
       
        Container titleCmp = BoxLayout.encloseY(
               
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Mes Evenements", "Title")
                        )
                )
        );
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.setVisible(focusScrolling);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        
        
         tb.addCommandToRightBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new listEvenement(res).show();        
}    } );

        
     EncodedImage enc=null;
                  
                 ServiceDetailsEvenement sp = new ServiceDetailsEvenement();
                 System.out.println(sp.getdet(id));
        Evenement i = new Evenement();
        Evenement p = sp.getdet(id);
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
            
        }
                  
                         String url=Statics.BASE_URL +"/jardin/web/uploads/events_photos/"+p.getImage();
                         add(new Label(URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL)));
                        
                     
                 
                  
      Label ii= new Label(p.getTitre(),"Title");
    
          ii.setUIID("titre");
        addAll(ii);                 
                                          
     Label o= new Label("description : ","LargeTitle");
      Label oo= new Label(p.getDescription(),"LargeTitle");
     o.setUIID("text");
          oo.getAllStyles().setFgColor(0x000000);
        addAll(o,oo);                 
                                
                                
     Label tt= new Label("type : ","LargeTitle");
      Label t= new Label(p.getType(),"LargeTitle");
     tt.setUIID("text");
          t.getAllStyles().setFgColor(0x000000);
        addAll(tt,t);
    
    
   
   
 
DateFormat ddd = new SimpleDateFormat("yyyy-MM-dd");

Label f= new Label("date début : ","LargeTitle");
        Label lbd = new Label(ddd.format(p.getDate_debut()),"LargeTitle");
     
        
         lbd.getAllStyles().setFgColor(0x000000);
          f.setUIID("text");
        addAll(f,lbd);
        
        
        
        
          Label ff= new Label("date fin : ","LargeTitle");
         Label lbf = new Label(ddd.format(p.getDate_fin()),"LargeTitle");
       
         lbf.getAllStyles().setFgColor(0x000000);
        ff.setUIID("text");
              addAll(ff,lbf);
               Label b= new Label("nombre de place : ","LargeTitle");
    Label nb= new Label(Integer.toString(p.getNombreDePlace()),"LargeTitle");
     b.setUIID("text");
          nb.getAllStyles().setFgColor(0x000000);
        addAll(b,nb);
              
                 Button btnsup = new Button("supprimer");
        // .add(new Label("Date of departure :" + i.getDate()));
        btnsup.addActionListener((e) -> {
            ServiceEvenement ser = new ServiceEvenement();
            ser.supprimerEvenement(p);
            Dialog.show("avec succès", "Evenement supprimer", "ok", null);
            new  listEvenement(res).show();

        });
       add(btnsup);
       btnsup.setUIID("LoginButton");
       
       
       Button btnmod = new Button("Ajouter");
        btnmod.addActionListener((e) -> {
            new  AjouterEvenement(res).show();
              
        });
        add(btnmod);
 btnmod.setUIID("LoginButton");
                      
                          
Button btnpartciper = new Button("details");
        btnpartciper.addActionListener((e) -> {
            new  detailParticipation(res,p.getId()).show();
              
        });
        add(btnpartciper);
        btnpartciper.setUIID("LoginButton");
                        
        
        
        
        
       
    
        
        
        
        
            titleCmp.setScrollableY(true);
        titleCmp.setScrollVisible(false);
                     }
    
    
    

    @Override
    protected void showOtherForm(Resources res) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
                     
            
    
    
    
    
    
    
    
    }

  

    
    
    
    
    
    
    
    
    
    
    
    
    
  
    
