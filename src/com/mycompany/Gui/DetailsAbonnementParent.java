/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Abonnement;
import com.mycompany.Service.ServiceDetailsAbonnement;

/**
 *
 * @author ayoub
 */
public class DetailsAbonnementParent extends Form{
       Form f;
       

public DetailsAbonnementParent(Resources res,int id) {
       
  super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Detail d'abonnement ", "Title")
                        )
                )
        );

      
      
               
        tb.addCommandToRightBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
             new AbonnmentParent(res).show();       
}    } );
         ServiceDetailsAbonnement s = new ServiceDetailsAbonnement();
          Abonnement sp = new   Abonnement();
        Abonnement p1 = s.Search(id);
        System.out.println(p1);
        add(new Label("Nom d'enfant : ", "LargeTitle"));
        add(new SpanLabel(p1.getNom()));
        add(new Label("Description : ", "LargeTitle"));
        add(new Label(p1.getDescription()));
        add(new Label("Type : ", "LargeTitle"));
        add(new SpanLabel(p1.getType()));
        add(new Label("Date de début : ", "LargeTitle"));
        add(new SpanLabel(p1.getData_debut()));
        add(new Label("Date fin : ", "LargeTitle"));
        add(new SpanLabel(p1.getDate_fin()));
        add(new Label("Statut : ", "LargeTitle"));
        add(new SpanLabel(p1.getStatu()));
        add(new Label("Prix : ", "LargeTitle"));
        Label prixt1 = new Label(String.valueOf(p1.getTotal())+ " DT");
       // add(new SpanLabel(String.valueOf(sp.getTotal())));
        add(prixt1);
        add(new Label("Statut de paiment : ", "LargeTitle"));
        add(new SpanLabel(p1.getStatu_paiment()));
        Button Del = new Button("Supprimer");
         Del.setUIID("LoginButton");
          Button payer = new Button("Payer");
         payer.setUIID("LoginButton");
             Del.addActionListener(new ActionListener() {
                          @Override
                              public void actionPerformed(ActionEvent ev) {
                                  if (Dialog.show("Delete","Are you sure?","Yes","No")) {

                                    ServiceDetailsAbonnement ser = new ServiceDetailsAbonnement();
                                    ser.supprimerAbn(p1.getId());
                                    AbonnmentParent k = new AbonnmentParent(res);
                                    k.show();
                                  }else
                                    Dialog.show("Annuler","Suppression Annuler","OK",null);
                                    }

                             
                         });
             
             payer.addActionListener(new ActionListener() {
                          @Override
                              public void actionPerformed(ActionEvent ev) {
                                    //ServiceDetailsAbonnement ser = new ServiceDetailsAbonnement();
                                    //ser.supprimerAbn(p1.getId());
                                    System.out.println(p1.getStatu_paiment());
                                    String stat = p1.getStatu_paiment();
                                    if(stat.equals("payed"))
                                    {
                                         Dialog.show("Erruer","Vous-avez déja payer cette Abonnement","OK",null);
                                    }else{
                                        PaiementOrder k = new PaiementOrder(res,p1);
                                        k.show();
                                    }
                                   
                                    }
                             
                         });
             
             addAll(Del,payer);
  
         }
}
