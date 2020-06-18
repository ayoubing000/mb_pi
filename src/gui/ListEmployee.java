/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.plaf.Style;
import entite.contrat;
import entite.employe;
import java.util.ArrayList;
import service.ServiceE;

/**
 *
 * @author souhaib
 */
public class ListEmployee extends Form{
 public ListEmployee(Form previous) {
   
    
       super(BoxLayout.y());
       setTitle("Liste des employees");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
      
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());


       
                   ServiceE spp=new ServiceE();
                ArrayList<employe>pl=spp.getListalls();
           //  setupSideMenu();
     
            
        
    
                   Component[] listingsToAdd = new Component[pl.size()];
                     for(int iter = 0 ; iter < spp.getListalls().size() ; iter++) {
                         final employe p=pl.get(iter);
                       
                         MultiButton mb = new MultiButton();
                            mb.setTextLine1("email:"+ pl.get(iter).getEmail());
                            mb.setTextLine2("cv:"+ pl.get(iter).getCv());
                            mb.setTextLine3("diplomes:"+ pl.get(iter).getDiplomes());
                            mb.setTextLine4("password:"+ pl.get(iter).getPassword());

           
            Label nb= new Label("cin:"+Integer.toString(p.getCin()));
            Label nb1= new Label("emploi_id:"+Integer.toString(p.getEmplois_id()));

                             
                         addAll(mb,nb,nb1);
                          mb.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent ev) {
                                  System.out.println("id:"+p.getId());
                                           new  ListEmployee(previous).show();
                                 
                                    }
                         });
                       
                     }}}