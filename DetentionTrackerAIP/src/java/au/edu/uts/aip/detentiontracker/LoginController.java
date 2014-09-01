/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.*;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private Login login = new Login();

    public Login getLogin(){
        return login;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
    /**
     * Save the current detention as a new record in the database.
     * @return a redirect to view the whole waiting list
     */
    public String createUser()  throws NoSuchAlgorithmException  {
        LoginDAO.createUser(login);
        return "login?faces-redirect=true";
    }
    
    
    public String login()  throws NoSuchAlgorithmException  {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        
        try {
          request.login(username, password);
          return "welcome?faces-redirect=true";
          
        } catch (ServletException e) {
          //showError("Incorrect username or password (or you may not have properly configured aipRealm)");
          e.printStackTrace();
          return null;
        }
    }
}
