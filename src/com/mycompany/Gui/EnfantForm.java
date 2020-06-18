/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.Gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Enfant;
import com.mycompany.Service.EnfantService;
import java.util.Date;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class EnfantForm extends BaseAdminForm {

    private EnfantService ser = new EnfantService();
    private Image imag;
    private EncodedImage enc;
    
    public EnfantForm(Resources res, Enfant enfant) {
        super("Enfant", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
       
        if(enfant != null)
        {
            
        String url = "http://localhost/cantine/"+enfant.getNomImage();

        try 
        {
           enc = EncodedImage.create("/tennis_club.png");
        } 
        catch (Exception ex) 
        {
            System.err.println(ex);
        }
        
        imag = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);                  
        ImageViewer img = new ImageViewer(imag);
        
        add(img);
        
        }
        
        TextField name = new TextField();
        if(enfant!= null) name.setText(enfant.getNom());
        name.setUIID("TextFieldBlack");
        addStringValue("Nom", name);
        
        TextField prenom = new TextField();
        if(enfant!= null) prenom.setText(enfant.getPrenom());
        prenom.setUIID("TextFieldBlack");
        addStringValue("Prénom", prenom);
        
        TextField age = new TextField();
        if(enfant!= null) age.setText(String.valueOf(enfant.getAge()));
        age.setUIID("TextFieldBlack");
        addStringValue("Age", age);

     
        add(new Label("Date de naissance: "));
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setUIID("TextFieldBlack");
        datePicker.setDate(new Date());
        add(datePicker);
        
        Button confirm = new Button("Confirmer");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
              

                    if(enfant != null)
                    {
                        ser.update(enfant.getId(), name.getText(), prenom.getText(), Integer.valueOf(age.getText()), datePicker.getValue().toString());
                        new EnfantsForm(res).show();
                    }
                    else{
                        ser.add(name.getText(), prenom.getText(), Integer.valueOf(age.getText()), datePicker.getValue().toString());
                        new EnfantsForm(res).show();
                    }
                
     
            }
        });
        Container content = BoxLayout.encloseY(
                confirm
        );
     
        content.setScrollableY(true);
        add(content);
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}