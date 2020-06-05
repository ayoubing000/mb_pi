/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.ShareButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author ons
 */
public class AjouterEvenement extends Form {
       
      
    String path ;
    Button btnimage;
    
    Image img  =null;
    public AjouterEvenement(Resources res) {
       
           super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
              Toolbar tb = getToolbar();
            //  setUIID("lolo");
        tb.addCommandToRightBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new ProfileForm(res).show();        
}    } );
    
          setTitle("AJOUTER UN EVENEMENT");
        setLayout(BoxLayout.y());
      
         TextField titre = new TextField("", "titre", 20, TextField.BASELINE);
          titre.setUIID("FloatingActionButton");
         TextField image = new TextField("", "image", 20, TextField.EMAILADDR);
          image.setUIID("FloatingActionButton");
         Picker dateDeb = new Picker();
          dateDeb.setUIID("FloatingActionButton");
         dateDeb.setType(Display.PICKER_TYPE_DATE);
         image.getAllStyles().setMargin(LEFT, 0);
          
       
         Picker dateFin = new Picker();
          dateFin.setUIID("FloatingActionButton");
         dateFin.setType(Display.PICKER_TYPE_DATE);
         
         TextField description = new TextField("", "description", 20, TextField.EMAILADDR) ;
          description.setUIID("FloatingActionButton");
         TextField type = new TextField("", "type", 20, TextField.EMAILADDR);
          type.setUIID("FloatingActionButton");
         
           TextField active= new TextField("", "active: 0 - 1");
            active.setUIID("FloatingActionButton");
         TextField nbrp = new TextField("", "nombreDePlace", 20, TextField.EMAILADDR);
         nbrp.setUIID("FloatingActionButton");
      
         Button AjouterImg = new Button("ajouter image ");
         AjouterImg.setUIID("LoginButton");
         //ajouter image
        AjouterImg.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path);
                        image.setText(path);
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
        
        
  
        
       Button btnValider = new Button("Add Evenement");
       btnValider.setUIID("LoginButton");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((titre.getText().length()==0)||(image.getText().length()==0)||(type.getText().length()==0)||(description.getText().length()==0)||(nbrp.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        boolean check = Boolean.parseBoolean(active.getText());

                       Evenement e = new Evenement(titre.getText(),  image.getText(),dateDeb.getDate(),dateFin.getDate(), type.getText(), description.getText(),check,Integer.parseInt(nbrp.getText()) );
                        if( ServiceEvenement.getInstance().addEvenement(e, image.getText()))
                            Dialog.show("Success","Evenement créé",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        
        
        addAll(titre,image,AjouterImg,dateDeb,dateFin,type,description,active,nbrp,btnValider);
//       hi.show();
    }
    
   
}