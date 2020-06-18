/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entity.Abonnement;
import com.mycompany.Gui.AbonnmentParent;
import com.mycompany.Utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author ayoub
 */
public class ServiceDetailsAbonnement {
     public ArrayList<Abonnement> abn;
    
    public static ServiceAbonnementParent instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceDetailsAbonnement() {
         req = new ConnectionRequest();
    }

    public static ServiceAbonnementParent getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnementParent();
        }
        return instance;
    }
    
  
      public ArrayList<Abonnement> Detail(String json) {

        ArrayList<Abonnement> listDetails = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                Abonnement p = new Abonnement();
                
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setStatu(obj.get("statu").toString());
                p.setDescription(obj.get("description").toString());
                p.setType(obj.get("type").toString());
                p.setData_debut(obj.get("dataDebut").toString());
                p.setDate_fin(obj.get("dateFin").toString());
                p.setStatu_paiment(obj.get("statu_paiment").toString());
                p.setTotal(Float.parseFloat(obj.get("total").toString()));
                p.setNom(obj.get("nom").toString());
                
                listDetails.add(p);

            }

        } catch (IOException ex) {
        }

        return listDetails;

    }



Abonnement det = new Abonnement();

  public Abonnement Search(int id) {
        req.setUrl("http://localhost/Jardin-dev/web/app_dev.php/api/parent/details/"+id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceDetailsAbonnement sp = new ServiceDetailsAbonnement();
                det = sp.Detail(new String(req.getResponseData())).get(0);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return det;
    }
 
 
 
    public void supprimerAbn(int id) {
        String url = Static.BASE_URL+"/parent/delete/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);

         
        });
       
        NetworkManager.getInstance().addToQueue(req);
        }
                
}

