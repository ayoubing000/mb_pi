/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
import entite.contrat;
import java.io.IOException;
import java.util.ArrayList;
import service.ServiceC;
import service.UserService;
import utils.Statics;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form{
Form Login ;
Resources theme ;
    
    public ListTasksForm(Form previous) {
     
       super(BoxLayout.y());
       setTitle("Liste des contrats");
    theme = UIManager.initFirstTheme("/theme");
       
       
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        

       
                        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());


                   ServiceC spp=new ServiceC();
                ArrayList<contrat>pl=spp.getListall();
                                   Component[] listingsToAdd = new Component[pl.size()];

                
            
                     for(int iter = 0 ; iter < spp.getListall().size() ; iter++) {
                         final contrat p=pl.get(iter);
                       
                         MultiButton mb = new MultiButton();
                            mb.setTextLine1("type:"+ pl.get(iter).getType());
                       
            DateFormat ddd = new SimpleDateFormat("yyyy-MM-dd");
            mb.setTextLine2("date signature:"+ddd.format(pl.get(iter).getDate_signature()));
            DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            mb.setTextLine3("date debut:"+d.format(pl.get(iter).getDate_debut()));
            DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
            mb.setTextLine4("date fin:"+dd.format(pl.get(iter).getDate_fin()));
            Label nb= new Label("employe_id:"+Integer.toString(p.getEmploy_id()));
                                    MultiButton m = new MultiButton();
                            m.setTextLine1("");
DateFormat x = new SimpleDateFormat("yyyy-MM-dd");
            m.setTextLine3("date resiliation:"+x.format(pl.get(iter).getDate_resiliation()));
            
            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("loading.png").scaled(250, 250), false);
            
                                 String stringQr = Statics.BASE_URL + "/mobile/" + p.getId();
String urlQR = "https://chart.googleapis.com/chart?cht=qr&chl=" + stringQr + "&choe=UTF-8&chs=500x500";

URLImage imgQR = URLImage.createToStorage(enc, p.getType()+ "Qr", urlQR);
       ImageViewer imageQR = new ImageViewer(imgQR);
       
                         addAll(mb,m,nb,imageQR);
                          mb.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent ev) {
                                  System.out.println("id:"+p.getId());
                                           new  ListTasksForm(previous).show();
                                 
                                    }
                         });
                       
                     

  
}}
    
    
}  
    

                 

















    
    

