/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import entite.contrat;
import entite.employe;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author souhaib
 */
public class ServiceC {
    
    public ArrayList<contrat> tasks;
     public static ServiceC instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServiceC() {
         req = new ConnectionRequest();
    }
        public static ServiceC getInstance() {
        if (instance == null) {
            instance = new ServiceC();
        }
        return instance;
    }
        public ArrayList<contrat> listAll(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               contrat t = new contrat();
               float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                Map<String, Object> date1 = (Map<String, Object>) obj.get("dateSignature");
                float time1=Float.parseFloat(date1.get("timestamp").toString());
                t.setDate_signature(new Date((long)time1*1000));
                Map<String, Object> date = (Map<String, Object>) obj.get("dateDebut");
                float time=Float.parseFloat(date.get("timestamp").toString());
                t.setDate_debut(new Date((long)time*1000));
              
                 Map<String, Object> date2 = (Map<String, Object>) obj.get("dateFin");
                float time2=Float.parseFloat(date2.get("timestamp").toString());
                t.setDate_fin(new Date((long)time2*1000));
                  Map<String, Object> date3 = (Map<String, Object>) obj.get("dateResiliation");
                float time3=Float.parseFloat(date3.get("timestamp").toString());
                t.setDate_resiliation(new Date((long)time3*1000));
                 
                t.setType(obj.get("type").toString());
                
                        Map<String, Object> listRecupevent = null;
                        listRecupevent = (Map<String, Object>) obj.get("employ");
                      
                     float ide = Float.parseFloat(listRecupevent.get("id").toString());
                        t.setEmploy_id((int) ide);
                
            
                tasks.add(t);

            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
          
     ArrayList<contrat> listall = new ArrayList<>();
    public ArrayList<contrat> getListall(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL +"/all");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceC ser = new ServiceC();
                listall = ser.listAll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }
       public ArrayList<contrat> getrech(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL +"/rech");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceC ser = new ServiceC();
                listall = ser.listAll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }
    public ArrayList<contrat> getAllTasks(){
        String url = Statics.BASE_URL+"/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = listAll(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
      
     public ArrayList<contrat> getContrat(contrat p){
       
         
         String url = Statics.BASE_URL+"/mobile/"+ p.getId();      
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
       
     

}
