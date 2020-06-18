/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entity.Abonnement;
import com.mycompany.Service.ServiceAbonnementParent;
import java.util.ArrayList;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ayoub
 */
public class AbonnmentParent extends SideMenuBaseForm{
    public AbonnmentParent(Resources res)
    {     super(BoxLayout.y());
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
                                new Label("Mes Abonnements", "Title")
                        )
                )
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.setVisible(focusScrolling);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        setupSideMenu(res);
      //  setUIID("LoginForm");        
                ServiceAbonnementParent sp=new ServiceAbonnementParent();
                ArrayList<Abonnement>pl=sp.getAllAbn();
      
                   Component[] listingsToAdd = new Component[pl.size()];
                     for(int iter = 0 ; iter < sp.getAllAbn().size() ; iter++) {
                         final Abonnement p=pl.get(iter);
                          MultiButton mb = new MultiButton();
                          mb.setTextLine1("Description :  "+pl.get(iter).getDescription());
                          mb.setTextLine2("Type :  "+pl.get(iter).getType()); 
                          mb.setTextLine3("Valable jusqu'à :  "+pl.get(iter).getDate_fin());  
                          mb.setTextLine4("______________________________________________________");
                          add(mb);
                         mb.addActionListener(new ActionListener() {
                          @Override
                              public void actionPerformed(ActionEvent ev) {
                                    new  DetailsAbonnementParent(res,p.getId()).show();
                                 
                                    }

                             
                         });
                        
                     }
       
    }
  
    @Override
    protected void showOtherForm(Resources res) {
    }
    
}
