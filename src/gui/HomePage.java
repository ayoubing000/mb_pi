/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import service.UserService;
import java.io.IOException;

/**
 *
 * @author TR3x
 */
public class HomePage {
    Form Home;
    Form pageForum;
    Form login;
    Form hi;
    
        public HomePage(){
         Home = new Form("Accueil", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tbh = Home.getToolbar();
       

        Label lab = new Label("\n    Bienvenue ");

            
        }}

