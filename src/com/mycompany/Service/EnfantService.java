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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entity.Enfant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kinto
 */
public class EnfantService {
    public ArrayList<Enfant> parseEnfantJson(String json) throws ParseException {

        ArrayList<Enfant> listEnfants = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> posts = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) posts.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Enfant e = new Enfant();
                
                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("age").toString());
             
                
                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setPrenom(obj.get("prenom").toString());
                e.setNomImage(obj.get("nomImage").toString());
                e.setDate_naissance(obj.get("dateNaissaince").toString());
                e.setAge((int) age);
                listEnfants.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listEnfants;
    }
    
    ArrayList<Enfant> listEnfants = new ArrayList<>();
    
    public ArrayList<Enfant> getEnfants(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Jardin-dev/web/app_dev.php/api/enfants");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EnfantService ser = new EnfantService();
                try {
                    listEnfants = ser.parseEnfantJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEnfants;
    }
    
    
    
   
    
    public void add(String nom, String prenom,  int age, String date) 
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/Jardin-dev/web/app_dev.php/api/enfants/add/" + nom+"/" + prenom + "/" + age + "/" + date;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
 
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    public void update(int id, String nom, String prenom,  int age, String date) 
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/Jardin-dev/web/app_dev.php/api/enfants/update/" + id + "/"  + nom+"/" + prenom + "/" + age + "/" + date;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
 
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    public void delete(int id) 
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/Jardin-dev/web/app_dev.php/api/enfants/delete/" + id;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
       
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    
   
}
