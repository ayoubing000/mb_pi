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
import com.mycompany.Entity.Enfant;
import com.mycompany.Utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ayoub
 */
public class ServiceEnfant {
    public ArrayList<Enfant> enf;
    
    public static ServiceEnfant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEnfant() {
         req = new ConnectionRequest();
    }

    public static ServiceEnfant getInstance() {
        if (instance == null) {
            instance = new ServiceEnfant();
        }
        return instance;
    }
    
         public ArrayList<Enfant> parseEnf(String jsonText){
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            enf=new ArrayList<>();
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Enfant t = new Enfant();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                enf.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return enf;
    }
         
     public ArrayList<Enfant> getAllenf(){
        String url = Static.BASE_URL+"/enfant";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                enf = parseEnf(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return enf;
    }
     
     public boolean confirmabn(Abonnement u,int moin,int id) {
        String url = Static.BASE_URL + "/check/new?id="+id+"&prix="+u.getTotal()+"&desc="+u.getDescription()+"&type="+u.getType()+"&moins="+moin;
         System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String chaine=new String(req.getResponseData());
                    if(chaine.equalsIgnoreCase("erreur")){
                        Dialog.show("Erreur", "Vous Avez deja un Abonnement pour votre enfant", "OK", null);

                }
                else
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
