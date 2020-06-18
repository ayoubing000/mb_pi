/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author TR3x
 */
public class User {
      private int id;
    private String username;
   private String email;
private String roles;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public User() {

    }

    public User(String username, int id,String email) {
        this.id = id;
        this.username = username;
        this.email=email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String value) {
        
        this.username = value;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", roles=" + roles + '}';
    }

   

}
