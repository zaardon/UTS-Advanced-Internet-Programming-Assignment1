/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginDAO implements Serializable {
   
    private static final String JNDI_NAME = "jdbc/detentiontracker";
    private static final String ALL_USERS = "select * from logins ";
    private static final String BY_USERNAME = "WHERE UserName = ?";
    private static final String INSERT_NEW_USER = "INSERT INTO logins (UserName, Password)" +
                                "Values (?, ?)";
        
        
    public static void createUser(LoginDTO login) throws NoSuchAlgorithmException {
       try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_NEW_USER);
            ps.setString(1, login.getUsername());
            ps.setString(2, EncryptionUtility.hash256(login.getPassword()));
            ps.executeUpdate();
        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }           
    }    

    public static boolean userExists(String username){
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(ALL_USERS + BY_USERNAME)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // user found
                        return true;
                    } 
                }
            }
        } catch (NamingException | SQLException e) {
            
        }
        return false;
    }
}