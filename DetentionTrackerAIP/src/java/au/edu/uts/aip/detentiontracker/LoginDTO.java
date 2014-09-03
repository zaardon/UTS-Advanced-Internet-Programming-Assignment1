/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.edu.uts.aip.detentiontracker;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Alex
 */
public class LoginDTO {
    
    private String username;
    private String password;
    
    @Pattern(regexp="[a-zA-Z_0-9]*")
    @Size(min = 1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Pattern(regexp="[A-Za-z 0-9-]*")
    @Size(min = 1)
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    
}
