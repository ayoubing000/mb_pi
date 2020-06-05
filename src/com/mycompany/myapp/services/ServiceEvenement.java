/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ons
 */
public class ServiceEvenement {

    public ArrayList<Evenement> evenements;
    public static ServiceEvenement instance = null;
    private ConnectionRequest req;

    public ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public boolean addEvenement(Evenement e) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDateDebut = dateFormat.format(e.getDate_debut());
        String strDateFin = dateFormat.format(e.getDate_fin());
        int active = e.isActive() ? 1 : 0;

        String url = Statics.BASE_URL + "/Jardin/web/app_dev.php/api/add?titre=" + e.getTitre() + "&image=" + e.getImage() + "&date_debut=" + strDateDebut + "&date_fin=" + strDateFin + "&type=" + e.getType() + "&description=" + e.getDescription() + "&active=" + active + "&nombreDePlace=" + e.getNombreDePlace();
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener((ex) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }

    public boolean addEvenement(Evenement e, String img) {
       

        MultipartRequest request = new MultipartRequest();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDateDebut = dateFormat.format(e.getDate_debut());
        String strDateFin = dateFormat.format(e.getDate_fin());
        int active = e.isActive() ? 1 : 0;
        String url = Statics.BASE_URL + "/Jardin/web/app_dev.php/api/add-event?titre=" + e.getTitre() 
                + "&date_debut=" + strDateDebut 
                + "&date_fin=" + strDateFin
                + "&type=" + e.getType()
                + "&description=" + e.getDescription() 
                + "&active=" + active 
                + "&nombreDePlace=" + e.getNombreDePlace();

        request.setUrl(url);
        
        try {
            request.addData("fileUpload", img, "image/jpeg");
            request.setFilename("fileUpload", "myPicture.jpg");
            request.addArgument(img, url);
        } catch (IOException ex) {
           ex.printStackTrace();
           return false;
        }

        request.addResponseListener((ex) -> {
            String str = new String(request.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return true;
    }

    public ArrayList<Evenement> listAll(String json) {

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
//          Integer nbr = Integer.parseInt(obj.get("nombreDePlace").toString());
                //    e.setNombreDePlace((int) nbr);
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

    ArrayList<Evenement> listall = new ArrayList<>();

    public ArrayList<Evenement> getListall() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/Jardin/web/app_dev.php/api/list");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvenement ser = new ServiceEvenement();
                listall = ser.listAll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }

    public void supprimerEvenement(Evenement e) {

        String url = Statics.BASE_URL + "/Jardin/web/app_dev.php/api/delete/" + e.getId();
        req.setUrl(url);
        req.addResponseListener((event) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
            //Dialog.show("", "Evenement supprimer", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void modifierEvenement(Evenement e) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDateDebut = dateFormat.format(e.getDate_debut());
        String strDateFin = dateFormat.format(e.getDate_fin());
        System.out.println(strDateDebut);
        System.out.println(strDateFin);
        int active = e.isActive() ? 1 : 0;

        String Url = Statics.BASE_URL + "/Jardin/web/app_dev.php/api/modifier?id=" + e.getTitre() + "&image=" + e.getImage() + "&date_debut=" + strDateDebut + "&date_fin=" + strDateFin + "&type=" + e.getType() + "&description=" + e.getDescription() + "&active=" + active + "&nombreDePlace=" + e.getNombreDePlace();

        System.out.println("tt");

        req.addResponseListener((event) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
