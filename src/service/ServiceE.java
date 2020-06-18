/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entite.contrat;
import entite.employe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author souhaib
 */
public class ServiceE {
        public ArrayList<employe> tasks;
     public static ServiceE instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServiceE() {
         req = new ConnectionRequest();
    }
        public static ServiceE getInstance() {
        if (instance == null) {
            instance = new ServiceE();
        }
        return instance;
    }
        public ArrayList<employe> listAlll(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               employe t = new employe();
               float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                 float cin = Float.parseFloat(obj.get("cin").toString());
                t.setCin((int)cin);
             t.setCv(obj.get("cv").toString());
             t.setDiplomes(obj.get("diplomes").toString());
             t.setEmail(obj.get("email").toString());
             t.setPassword(obj.get("password").toString());
              
               //Map<String, Object> listRecupevent = null;
                       //listRecupevent = (Map<String, Object>) obj.get("contrats");
                       //   float  ide = Float.parseFloat(listRecupevent.get("id").toString());
                     //   t.setContrats_id((int) ide); 
                        
                          Map<String, Object> listRecupeven = null;
                        listRecupeven = (Map<String, Object>) obj.get("emplois");
                           float  idee = Float.parseFloat(listRecupeven.get("id").toString());
                        t.setEmplois_id((int) idee);   
                
            
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
          ArrayList<employe> listall = new ArrayList<>();
    public ArrayList<employe> getListalls(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL +"/employe");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceE ser = new ServiceE();
                listall = ser.listAlll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }
    
    public ArrayList<employe> getAllTasks(){
        String url = Statics.BASE_URL+"/employe";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = listAlll(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
      public boolean addTask(employe t) {
        String url = Statics.BASE_URL + "/ajout/" + t.getUsername()+"/"+  t.getCin()+"/" + t.getEmail()+"/"+ t.getEmplois_id()+"/"+  t.getPassword()+"/"+ t.getCv()+"/"+ t.getDiplomes();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
