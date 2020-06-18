/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Abonnement;
import com.mycompany.Entity.AbonnementAd;
import com.mycompany.Entity.Enfant;
import com.mycompany.Entity.User;
import com.mycompany.Service.ServiceDetailsAbn;
import com.mycompany.Service.ServiceEnfant;
import com.mycompany.Service.Servicenewparent;
import java.util.ArrayList;

/**
 *
 * @author ayoub
 */
public class DetailsAbnAcheter extends SideMenuBaseForm {
    
    Form f;
    public DetailsAbnAcheter(Resources res,int id)
    {    super(BoxLayout.y());
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
                                new Label("Achat", "Title")
                        )
                )
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.setVisible(focusScrolling);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        setupSideMenu(res);
        
        ServiceDetailsAbn s = new  ServiceDetailsAbn();
        AbonnementAd sp = new   AbonnementAd();
        AbonnementAd p1 = s.Search(id);
        
        
        add(new Label("Description : ", "LargeTitle"));
        add(new Label(p1.getDescription()));
        add(new Label("Prix / Moins : ", "LargeTitle"));
        Label prixt1 = new Label(String.valueOf(p1.getPrix())+ " DT");
       // add(new SpanLabel(String.valueOf(sp.getTotal())));
        add(prixt1);
        add(new Label("Type : ", "LargeTitle"));
        add(new SpanLabel(p1.getType()));
        add(new Label("Nom d'enfant  : ", "LargeTitle"));
        ServiceEnfant enf = new ServiceEnfant();
        ArrayList<Enfant> aarr=enf.getAllenf();
        ComboBox combo = new ComboBox<>();
        Component[] listingsToAdd = new Component[aarr.size()];
        for(int iter = 0 ; iter < enf.getAllenf().size() ; iter++) {
                   combo.addItem(aarr.get(iter).getNom());

            }
        Label xx  = new Label("Nombre de Moins : ", "LargeTitle");
        ComboBox combox = new ComboBox<>(3,6,12);
          
              
       Button Acheter = new Button("Acheter");
         Acheter.setUIID("LoginButton");
             Acheter.addActionListener(new ActionListener() {
                          @Override
                              public void actionPerformed(ActionEvent ev) {
                                  Abonnement t = new Abonnement(p1.getType(),  p1.getDescription(),p1.getPrix());
                                  Integer moin = Integer.parseInt(combox.getSelectedItem().toString());
                          if( ServiceEnfant.getInstance().confirmabn(t,moin,aarr.get(combo.getSelectedIndex()).getId()))
                           { Dialog.show("Success","Vous pouvez payer online dans l'espace mes abonnement",new Command("OK"));
                            AbonnmentParent k = new AbonnmentParent(res);
                                    k.show();}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));

                                    }
                         });
             addAll(combo,xx,combox,Acheter);
        
  
         }

    @Override
    protected void showOtherForm(Resources res) {
       
    }
    
}
