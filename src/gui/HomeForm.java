/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import entite.contrat;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import service.ServiceC;

/**
 *
 * @author bhk
 */
public class HomeForm extends SideMenuBaseForm{
Form current;
    
    Form f;


    public HomeForm(Resources res) {
        
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        TextField tfStatus= new TextField("", "");
        Button btnValider = new Button("rechercher");
         Button SignIn = new Button("rechercher");
         
         Toolbar tb = getToolbar();
        tb.setTitleCentered(false);        

       

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
       
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton)
                     
                );
        
        FloatingActionButton fab = FloatingActionButton.createBadge("home");
       
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp));
                setupSideMenu(res);

        
        Button btnListTasks = new Button("List des contrats");
        Button btnListEmploye = new Button("List des employees");
Button capture = new Button("capture");
    Button voix = new Button("Enregistrement vocale");
    ShareButton sb = new ShareButton();
    Form hiii = new Form("ShareButton");
     Form hii = new Form("Rounder", new BorderLayout());  
     Form hi = new Form("Capture", BoxLayout.y());
     Form h = new Form("Layout Animations", new BoxLayout(BoxLayout.Y_AXIS));
Button fall = new Button("Fall");
 Button btnAddTask = new Button("ajouter employee");
        
        btnAddTask.addActionListener(e-> new AddTaskForm(current).show());

        btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        btnListEmploye.addActionListener(e-> new ListEmployee(current).show());
        addAll(btnListTasks,btnListEmploye,btnAddTask);
      capture.addActionListener(e-> hii.show());
       voix.addActionListener(e-> hi.show());
     sb.addActionListener(e-> hiii.show());
      fall.addActionListener(e-> hiii.show());
     Toolbar.setGlobalToolbar(true);
Label picture = new Label("", "Container");
hii.add(BorderLayout.CENTER, picture);
hii.getUnselectedStyle().setBgColor(0xFF8000);
hii.getUnselectedStyle().setBgTransparency(255);
Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
Image camera = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);

      hii.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());

hii.getToolbar().addCommandToRightBar("", camera, (ev) -> {
    try {
        int width = Display.getInstance().getDisplayWidth();
        Image capturedImage = Image.createImage(Capture.capturePhoto(width, -1));
        Image roundMask = Image.createImage(width, capturedImage.getHeight(), 0xFEBFBF);
        Graphics gr = roundMask.getGraphics();
        gr.setColor(0xFEBFBF);
        gr.fillArc(0, 0, width, width, 0, 360);
        Object mask = roundMask.createMask();
        capturedImage = capturedImage.applyMask(mask);
        picture.setIcon(capturedImage);
        hii.revalidate();
    } catch(IOException err) {
        Log.e(err);
    }
});   

hi.setToolbar(new Toolbar());
Style a = UIManager.getInstance().getComponentStyle("Title");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MIC, a);

FileSystemStorage fs = FileSystemStorage.getInstance();
String recordingsDir = fs.getAppHomePath() + "recordings/";
fs.mkdir(recordingsDir);
try {
    for(String file : fs.listFiles(recordingsDir)) {
        MultiButton mb = new MultiButton(file.substring(file.lastIndexOf("/") + 1));
        mb.addActionListener((e) -> {
            try {
                Media m = MediaManager.createMedia(recordingsDir + file, false);
                m.play();
            } catch(IOException err) {
                Log.e(err);
            }
        });
        hi.add(mb);
    }
      hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());

    hi.getToolbar().addCommandToRightBar("", icon, (ev) -> {
        try {
            String file = Capture.captureAudio();
            if(file != null) {
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd-kk-mm");
                String fileName =sd.format(new Date());
                String filePath = recordingsDir + fileName;
                Util.copy(fs.openInputStream(file), fs.openOutputStream(filePath));
                MultiButton mb = new MultiButton(fileName);
                mb.addActionListener((e) -> {
                    try {
                        Media m = MediaManager.createMedia(filePath, false);
                        m.play();
                    } catch(IOException err) {
                        Log.e(err);
                    }
                });
                hi.add(mb);
                hi.revalidate();
            }
        } catch(IOException err) {
            Log.e(err);
        }
    });
} catch(IOException err) {
    Log.e(err);
}
 

sb.setText("Share Screenshot");
hiii.add(sb);

Image screenshot = Image.createImage(1125, 255);
hiii.revalidate();
hiii.setVisible(true);
hiii.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
sb.setImageToShare(imageFile, "logo.png");

fall.addActionListener((e) -> {
    for(int iter = 0 ; iter < 10 ; iter++) {
        Label b = new Label ("Label " + iter);
        b.setWidth(fall.getWidth());
        b.setHeight(fall.getHeight());
        b.setY(-fall.getHeight());
        h.add(b);
    }
    h.getContentPane().animateLayout(20000);
});
h.add(fall);

 
         SignIn.addActionListener((evt) ->{
              Label textfromServer = new Label();
              
  
 ConnectionRequest connexion = new  ConnectionRequest ("http://localhost/Jardin/web/app_dev.php/api/rech?type="+tfStatus.getText());
     connexion.addResponseListener((x)->{
         
                                    ServiceC spp=new ServiceC();
                ArrayList<contrat>pl=spp.getAllTasks();
                                   Component[] listingsToAdd = new Component[pl.size()];

         
                     for(int iter = 0 ; iter < spp.getAllTasks().size() ; iter++) {
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
DateFormat xa = new SimpleDateFormat("yyyy-MM-dd");
            m.setTextLine3("date resiliation:"+xa.format(pl.get(iter).getDate_resiliation()));
                         addAll(mb,m,nb);
                         
                         
                       
                     

  
}
       
            
     
        });
        NetworkManager.getInstance().addToQueue(connexion);
             
         });
                           
    }
    
    
    
    
      
        
       

    @Override
    protected void showOtherForm(Resources res) {
    }

   
}
