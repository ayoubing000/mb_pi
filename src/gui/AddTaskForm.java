/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import service.ServiceC;
import service.ServiceE;
import service.UserService;
import entite.employe;


/**
 *
 * @author bhk
 */
public class AddTaskForm extends Form{

    public AddTaskForm(Form previous) {
        setTitle("ajouter un employee");
        setLayout(BoxLayout.y());

        TextField A = new TextField("","username");
        TextField B= new TextField("", "email");
         TextField C = new TextField("","cin");
        TextField D= new TextField("", "password");
        TextField E= new TextField("", "CV");
        TextField F= new TextField("", "diplomes");
         TextField G = new TextField("","emploi_id");
        TextField H= new TextField("", "contrat_id");
        Button btnValider = new Button("ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((A.getText().length()==0)||(B.getText().length()==0)||(C.getText().length()==0)||(D.getText().length()==0)||(E.getText().length()==0)||(F.getText().length()==0))
                    Dialog.show("Alert", "il y a un chmap vide", new Command("OK"));
                else if 
                         ((C.getText().length()!=8))
                    Dialog.show("Alert", "CIN incorrect ", new Command("OK"));
                else 
                {
                    try {
                        employe c = new employe(A.getText(),Integer.parseInt(C.getText()), B.getText(),Integer.parseInt(G.getText()), D.getText(), E.getText(), F.getText());
                        if( ServiceE.getInstance().addTask(c))
                            Dialog.show("Success","add accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                    
                }
                
                
            
        });
        
        addAll(A,B,C,D,E,F,G,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
