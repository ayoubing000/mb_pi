/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ons
 */
public class HomeEvenement extends Form {
    
    
    
    public HomeEvenement(Resources res ){
    super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
     setTitle("welcomeeeeeeee");
    Toolbar tb = getToolbar();
       tb.addCommandToSideMenu("liste des evenements", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new listEvenement(res).show();        
}    } );
          tb.addCommandToSideMenu("ajouter des evenements", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new AjouterEvenement(res).show();        
}    } ); 
}
    
    
     
}