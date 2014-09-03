/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginDAO implements Serializable {
   
    private static final String JNDI_NAME = "jdbc/detentiontracker";
    private static final String SEARCH_ALL_USERS = "select * from logins ";
        
        
    public static void createUser(LoginDTO login) throws NoSuchAlgorithmException {
        String insertStatement = "INSERT INTO logins " +
                "(UserName, Password) " +
                "VALUES ( '"+login.getUsername()+"', '"+EncryptionUtility.hash256(login.getPassword())+"')";
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(insertStatement);

        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }       
    }          

    public static boolean userExists(String username){
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SEARCH_ALL_USERS)) {

                while (rs.next()) {
                    if(rs.getString("UserName").equals(username)){
                        return true;
                    }
                }
            }
        } catch (NamingException | SQLException e) {
            //throw new DataStoreException(e);
        }
        return false;
    }
}