/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.facebook.User;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.processing.Result;
import com.codename1.util.Callback;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.InscriptionEvenement;
import com.mycompany.myapp.utils.Statics;
import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.ir.debug.JSONWriter;

/**
 *
 * @author ons
 */
public class ServiceParticipation {

    public void AnnulerPartcipation(Evenement e) {
         System.out.println("annuler Participation Evenement: "+e.getId());
        String userId = Statics.userId.toString();
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL + "/Jardin/web/app_dev.php/api/annuler-inscription/"
                + userId + "/" + e.getId();
        con.setUrl(Url);
        con.setPost(false);

        con.addResponseListener((k) -> {
            String str = new String(con.getResponseData());
            System.out.print("Response: ");
            System.out.println(str);

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

    }
        
        
  

    public void participer(Evenement e) {
        System.out.println("Participation Evenement: "+e.getId());
        String userId = Statics.userId.toString();
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL + "/Jardin/web/app_dev.php/api/inscrire-evenement/"
                + userId + "/" + e.getId();
        con.setUrl(Url);
        con.setPost(false);

        con.addResponseListener((k) -> {
            String str = new String(con.getResponseData());
            System.out.print("Response: ");
            System.out.println(str);

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}
