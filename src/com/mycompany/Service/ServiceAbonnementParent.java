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
import com.mycompany.Utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ayoub
 */
public class ServiceAbonnementParent {
    public ArrayList<Abonnement> abn;
    
    public static ServiceAbonnementPrt instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAbonnementParent() {
         req = new ConnectionRequest();
    }

    public static ServiceAbonnementPrt getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnementPrt();
        }
        return instance;
    }
    
         public ArrayList<Abonnement> parseAbn(String jsonText){
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            abn=new ArrayList<>();
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Abonnement t = new Abonnement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               // t.setTotal(((int)Float.parseFloat(obj.get("total").toString())));
                t.setDescription(obj.get("description").toString());
                t.setDate_fin(obj.get("dateFin").toString());
                 t.setType(obj.get("type").toString());
                abn.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return abn;
    }
    
    public ArrayList<Abonnement> getAllAbn(){
        String url = Static.BASE_URL+"/parent";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abn = parseAbn(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return abn;
    }
    
    public void payer(int idabn) {
           //ConnectionRequest req = new ConnectionRequest();
      String url = Static.BASE_URL + "/payer?id="+idabn;
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
        });
        NetworkManager.getInstance().addToQueue(req);
    }
    
    
}
