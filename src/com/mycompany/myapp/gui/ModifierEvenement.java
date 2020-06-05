/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;

/**
 *
 * @author ons
 */
public class ModifierEvenement extends Form{
    
    
     public ModifierEvenement(Resources res, Evenement p) {
          super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
              Toolbar tb = getToolbar();
        tb.addCommandToRightBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new HomeEvenement(res).show();        
}    } );
         Container welcome = FlowLayout.encloseCenter(
                new Label("Modifier Votre  Conférence !!!!! ", "WelcomeWhite")
        //   new Label("Cité de la Culture", "WelcomeBlue")
        );
    
          setTitle("AJOUTER UN EVENEMENT");
        setLayout(BoxLayout.y());
      
         TextField titre = new TextField("", "titre", 20, TextField.BASELINE);
         TextField image = new TextField("", "image", 20, TextField.EMAILADDR);
         Picker dateDeb = new Picker();
         dateDeb.setType(Display.PICKER_TYPE_DATE);
         image.getAllStyles().setMargin(LEFT, 0);
          
       
         Picker dateFin = new Picker();
         dateFin.setType(Display.PICKER_TYPE_DATE);
         
         TextField description = new TextField("", "description", 20, TextField.EMAILADDR) ;
         TextField type = new TextField("", "type", 20, TextField.EMAILADDR);
           TextField active= new TextField("", "active: 0 - 1");
         TextField nbrp = new TextField("", "nombreDePlace", 20, TextField.EMAILADDR);
     Button AjouterImg = new Button("Image");
        AjouterImg.setUIID("LoginButton");
        
        
        Button Ajouter = new Button("Modifier");
        Ajouter.setUIID("LoginButton");
                titre.setText(p.getTitre());

        nbrp.setText(Integer.toString((int) p.getNombreDePlace()));
        Ajouter.addActionListener(e -> {

            try {

                System.out.println("yes");
                ServiceEvenement ser = new ServiceEvenement();
//                Conference t = new Conference();
                p.setTitre(titre.getText());

                p.setNombreDePlace(Integer.valueOf(nbrp.getText()));

                System.out.println(titre);
                System.out.println(nbrp.getText());

                ser.modifierEvenement(p);
                Dialog.show("Modification effectuée avec succes", "succes", "OK", null);
                listEvenement k = new listEvenement(res);
                k.show();
                //   ProfileForm k = new ProfileForm(res);
                // k.show();
            } catch (Exception ex) {
            }
        });

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                BorderLayout.center(titre),
                BorderLayout.center(nbrp),
                Ajouter
        );
//        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);

    }
     
     
    /* setTitle("modifier Evenement");
      TextField titre = new TextField("", "titre", 20, TextField.BASELINE);
       titre.setText(p1.getTitre());
         TextField image = new TextField("", "image", 20, TextField.EMAILADDR);
          image.setText(p1.getImage());
         
         image.getAllStyles().setMargin(LEFT, 0);
          
         Picker date = new Picker();
        DateFormat ddd = new SimpleDateFormat("yyyy-MM-dd");

        date.setText(ddd.format(p1.getDate_debut()));
       
         
         
         
         
         
         Picker dateFin = new Picker();
         dateFin.setType(Display.PICKER_TYPE_DATE);
         
         TextField description = new TextField("", "description", 20, TextField.EMAILADDR) ;
           description.setText(p1.getDescription());
         TextField type = new TextField("", "type", 20, TextField.EMAILADDR);
           type.setText(p1.getType());
         TextField nbrp = new TextField("", "nombreDePlace", 20, TextField.EMAILADDR);
         nbrp.setText(Integer.toString(p1.getNombreDePlace()));
     
     
     
      Button Ajouter = new Button("Modifier");
       /* Ajouter.setUIID("LoginButton");
        Ajouter.addActionListener(e -> {


            try {
                ServiceEvenement event =new ServiceEvenement();
                Evenement t = new Evenement();
             
                t.setNombreDePlace(Integer.parseInt(nbrp.getText()));
                t.setTitre(titre.getText());
                t.setDescription(description.getText());
                
               
                t.setDate_debut(date.getDate());
                t.setDate_fin(dateFin.getDate());



                event.modifierEvenement(t);
                   Dialog.show("modification avec succes", "succes" , "OK", null);


                listEvenementImage k = new listEvenementImage();
                k.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("helloooo  " + ex);

            }

        });
     */
     
     
     }
    

