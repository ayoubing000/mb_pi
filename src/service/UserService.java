/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import entite.User;
import utils.Statics;
import java.io.IOException;
import java.util.Map;
import utils.BCrypt;

/**
 *
 * @author TR3x
 */
public class UserService {
    public static User loggedUser = null;
    
    public static User getLoggedUser(){
        return loggedUser;
    }
    
    public static void setLoggedInUser(User loggedInUser) {
        UserService.loggedUser = loggedInUser;
    }
    
   /* public static String hashPassword(String password_plaintext)
    {
        int workload = 13;
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return hashed_password;
    }*/

    public static boolean checkPassword(String password_plaintext, String stored_hash)
    {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$"))
        {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        return password_verified;
    }
    
    public static int recupererUser(String username, String password){
        int existe = 2;
        Map<String, Object> result;
        User loggedin = new User();
        String url = "http://localhost/Jardin/web/app_dev.php/api/getUser?username=" + username;
        ConnectionRequest request = new ConnectionRequest(url,false);
        NetworkManager.getInstance().addToQueueAndWait(request);
        JSONParser j = new JSONParser();
        String json = new String(request.getResponseData()) + "";
        try{
            result = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if(result.isEmpty() ){
                System.out.println("user not found");
                existe = 1;
            }
            
           
            else{
                int id = (int)Double.parseDouble(result.get("id").toString());
                String db_pass  = (String)result.get("password");
                if(checkPassword(password, db_pass)){
                    loggedin.setId(id);
                    loggedin.setUsername(result.get("username").toString());
                    loggedin.setEmail(result.get("email").toString());
                    loggedUser = loggedin;
                    existe = 0;
            System.out.println("user correct, password correct");

                }
                else
                {
                    System.out.println("user correct, password incorrect");
                    existe = 2;
                } 
                
            }
            
        }
        catch (IOException ex) {
                ex.printStackTrace();
            }
        return existe;
    }
    
}
