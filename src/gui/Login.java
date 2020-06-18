/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

import service.UserService;

/**
 *
 * @author TR3x
 */
public class Login {
    Form f;
    Form current;
    Form homepage;
    Form pageForum;
    SpanLabel lb;
    TextField unf;
    TextField pf;
    Button valider;
    Button inscription;
    Image logo;
    
    public Login(){
        f = new Form("login",new BoxLayout(BoxLayout.Y_AXIS));
        unf = new TextField("", "Username");
        pf = new TextField("", "Password");
        pf.setConstraint(TextArea.PASSWORD);
        valider = new Button("Se connecter");
        
        
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService us = new UserService();
                if(UserService.recupererUser(unf.getText(),pf.getText())== 0){
                    HomePage affForm = new HomePage();
                   
                 // valider.addActionListener(e-> new HomeForm().show());
                //   Message m = new Message("salut");
                   
                //  Display.getInstance().sendMessage(new String[] {"souhaibabc@gmail.com"}, "Subject of message", m);
                  //  try {
                    //    Display.getInstance().sendSMS("+21625783739", "sou ya patron ");
                    //} catch (IOException ex) {
                   // }
                }
              else{
                    Dialog dlg = new Dialog("Erreurs!");
                    dlg.setLayout(new BorderLayout());
                    dlg.setDialogType(Dialog.TYPE_WARNING);
                    dlg.add(BorderLayout.CENTER,new SpanLabel("Veuillez vérifier votre username ou mot de passe.", "DialogBody"));
                    int h = Display.getInstance().getDisplayHeight();
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.show();
                }
            }
        });
        int mm = Display.getInstance().convertToPixels(3);
        logo = null;
        try {
            logo = Image.createImage("/logo.png");
        } catch (IOException ex) {   
        }
        logo.scale(mm*20, mm*22);
        f.add(logo);  
        f.show();
      f.add(unf);
        f.add(pf);
        f.add(valider);
       
        f.show();  
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
