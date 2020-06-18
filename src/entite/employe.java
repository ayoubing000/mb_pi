/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Date;
import java.util.Objects;


/**
 *
 * @author souhaib
 */
public class employe {
    private int id;
    private int cin;
    private String  cv;
    private String  email;
    private String  username;
    private String  password;
    private String  diplomes;
    private int contrats_id;
    private int emplois_id;
   

    public employe(int id, int cin, String cv, String email, String username , String password, String diplomes,int contrats_id,int emplois_id) {
        this.id = id;
        this.cin = cin;
        this.cv = cv;
        this.email = email;
         this.username = username;
        this.password = password;
        this.diplomes = diplomes;
        this.contrats_id=contrats_id;
        this.emplois_id=emplois_id;
    }

    public employe() {
    }

  

    public employe(int cin, String cv, String email, String username, String password, String diplomes) {
        this.cin = cin;
        this.cv = cv;
        this.email = email;
        this.username = username;
        this.password = password;
        this.diplomes = diplomes;
    }

    public employe(String username,int cin, String email, int emplois_id,   String password, String cv, String diplomes) {
        this.username = username;
        this.email = email;
        this.cin = cin;
         this.password = password;
        this.cv = cv;
        this.diplomes = diplomes;
        this.emplois_id = emplois_id;
    }

   

   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getCv() {
        return cv;
    }

    public int getContrats_id() {
        return contrats_id;
    }

    public void setContrats_id(int contrats_id) {
        this.contrats_id = contrats_id;
    }

    public int getEmplois_id() {
        return emplois_id;
    }

    public void setEmplois_id(int emplois_id) {
        this.emplois_id = emplois_id;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(String diplomes) {
        this.diplomes = diplomes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + this.cin;
        hash = 29 * hash + Objects.hashCode(this.cv);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.diplomes);
        hash = 29 * hash + this.contrats_id;
        hash = 29 * hash + this.emplois_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final employe other = (employe) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.cin != other.cin) {
            return false;
        }
        if (this.contrats_id != other.contrats_id) {
            return false;
        }
        if (this.emplois_id != other.emplois_id) {
            return false;
        }
        if (!Objects.equals(this.cv, other.cv)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.diplomes, other.diplomes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enseignant{" + "id=" + id + ", cin=" + cin + ", cv=" + cv + ", email=" + email +", usename=" + username + ", password=" + password + ", diplomes=" + diplomes + ", contrats_id=" + contrats_id + ", emplois_id=" + emplois_id + '}';
    }



  

}
