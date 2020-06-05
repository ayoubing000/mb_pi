/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ons
 */
public class ServiceDetailsEvenement {

    public ArrayList<Evenement> evenements;
    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceDetailsEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    /**
     * **************************************************
     */
    Evenement det = new Evenement();

    public ArrayList<Evenement> Detail(String json) {

        ArrayList<Evenement> listEvenement = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des t�ches Json
            for (Map<String, Object> obj : list) {
                //Cr�ation des t�ches et r�cup�ration de leurs donn�es
                Evenement e = new Evenement();

                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setTitre(obj.get("titre").toString());
                //  e.setImage(obj.get("image").toString());
                e.setImage((String) obj.get("image"));
                //e.setDate_debut((Date) obj.get("date_debut"));
                Map<String, Object> date = (Map<String, Object>) obj.get("dateDebut");
                float time = Float.parseFloat(date.get("timestamp").toString());
                e.setDate_debut(new Date((long) time * 1000));
                e.setDate_fin((Date) obj.get("date_fin"));

                e.setType(obj.get("type").toString());
                e.setDescription(obj.get("description").toString());

                //     e.setActive((boolean) obj.get"active").toString());
                float nbr = Float.parseFloat(obj.get("nombreDePlace").toString());
                e.setNombreDePlace((int) nbr);

                listEvenement.add(e);

            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu r�cup�rer une liste des t�ches � partir
        de la base de donn�es � travers un service web
        
         */
        return listEvenement;

    }

    public Evenement getdet(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/Jardin/web/app_dev.php/api/details/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                System.out.println("qkfsqjdlkqsjdlqsjd" + id);
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    det.setId((int) id);
                    det.setTitre(obj.get("titre").toString());
                    det.setImage((String) obj.get("image"));
                    Map<String, Object> date = (Map<String, Object>) obj.get("dateDebut");
                    float time = Float.parseFloat(date.get("timestamp").toString());
                    det.setDate_debut(new Date((long) time * 1000));
                    det.setDate_fin((Date) obj.get("date_fin"));
                    det.setType(obj.get("type").toString());
                    det.setDescription(obj.get("description").toString());
                    det.setNombreDePlace(((Double) obj.get("nombreDePlace")).intValue());

                    //e.setDate_debut((Date) obj.get("date_debut"));
                    System.out.println(det.getNombreDePlace());

                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return det;

    }

}
