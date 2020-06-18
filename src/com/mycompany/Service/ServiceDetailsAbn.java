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
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entity.AbonnementAd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ayoub
 */
public class ServiceDetailsAbn {
         public ArrayList<AbonnementAd> abn;
    
    public static ServiceDetailsAbn instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceDetailsAbn() {
         req = new ConnectionRequest();
    }

    public static ServiceDetailsAbn getInstance() {
        if (instance == null) {
            instance = new ServiceDetailsAbn();
        }
        return instance;
    }
    
  
      public ArrayList<AbonnementAd> Detail(String json) {

        ArrayList<AbonnementAd> listDetails = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                AbonnementAd p = new AbonnementAd();
                
                float id = Float.parseFloat(obj.get("id").toString());
               float prx = Float.parseFloat(obj.get("prix").toString());
                p.setId((int) id);
                p.setDescription(obj.get("description").toString());
                p.setType(obj.get("type").toString());
                p.setPrix(prx);
                listDetails.add(p);

            }

        } catch (IOException ex) {
        }

        return listDetails;

    }



AbonnementAd det1 = new AbonnementAd();
  public AbonnementAd Search(int id) {
        req.setUrl("http://localhost/Jardin-dev/web/app_dev.php/api/kaaba?id=" + id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                System.out.println("qkfsqjdlkqsjdlqsjd" + id);
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Abonneme = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Abonneme.get("root");
                        System.out.println(list);
                    for (Map<String, Object> obj : list) {
                        AbonnementAd p = new AbonnementAd();

                        float id = Float.parseFloat(obj.get("id").toString());
                        float prx = Float.parseFloat(obj.get("prix").toString());
                        det1.setId((int) id);
                        det1.setDescription(obj.get("description").toString());
                        det1.setType(obj.get("type").toString());
                        det1.setPrix(prx);
                }

                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return det1;

    }
  
 
}
