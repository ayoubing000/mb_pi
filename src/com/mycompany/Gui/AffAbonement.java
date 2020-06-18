/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.AbonnementAd;
import com.mycompany.Service.ServiceAbonnementPrt;
import com.mycompany.Service.ServiceDetailsAbn;
import java.util.ArrayList;

/**
 *
 * @author ayoub
 */
public class AffAbonement extends  SideMenuBaseForm {
    
    
    public AffAbonement(Resources res)
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
                                new Label("Les Abonnements", "Title")
                        )
                )
        );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.setVisible(focusScrolling);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        setupSideMenu(res);
        
                ServiceAbonnementPrt sp=new ServiceAbonnementPrt();
                ArrayList<AbonnementAd>pl=sp.getAllAbn();

                   Component[] listingsToAdd = new Component[pl.size()];
                     for(int iter = 0 ; iter < sp.getAllAbn().size() ; iter++) {
                         final AbonnementAd p=pl.get(iter);
                          MultiButton mb = new MultiButton();
                          String prix = "Prix : "+String.valueOf(pl.get(iter).getPrix());
                          mb.setTextLine1("Description : "+pl.get(iter).getDescription());
                          mb.setTextLine2("Type : "+pl.get(iter).getType()); 
                         mb.setTextLine4(prix);                         
                         
                         add(mb);
                     
               
         mb.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent ev) {
                                           new  DetailsAbnAcheter(res,p.getId()).show();
                                 
                                    }
                         });
    }}

    @Override
    protected void showOtherForm(Resources res) {
    }
   
    
}
