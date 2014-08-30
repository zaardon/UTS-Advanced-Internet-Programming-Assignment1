/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Logger;
import javax.enterprise.context.*;
import javax.faces.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class DetentionListController implements Serializable {
    
    //public Collection<Detention> getDetentions() {
    //    return DetentionListDatabase.findAll();
    //}
    
    public ArrayList<Detention> getAllDetentions() {
            return new DetentionListDatabase().findDBAll();
    }
    
    //public int sizeOfDetentionList(){
    //    return getDetentions().size();
    //}
    
    public int sizeOfDetentionList(){
        return getAllDetentions().size();
    }
    
    /*public void listDetention() throws SQLException {
        String connectionString = "jdbc:derby://localhost:1527/aip";
        String dbUsername = "aip";
        String dbPassword = "aip";
        String query = "select DetentionID, FirstName, LastName, YearGroup, DetentionType, Department, Reason " +
                       "from detentions";
        
       
        Logger log = Logger.getLogger(this.getClass().getName());
        
        try (Connection conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            log.info("The accounts table contains:");
            while (rs.next()) {
                log.info("DetentionID = " + rs.getInt(1) +
                         ", FirstName = " + rs.getString(2) +
                         ", LastName = " + rs.getString(3) +
                         ", YearGroup = " + rs.getInt(4) +
                         ", DetentionType = " + rs.getString(5) +
                        ", Department = " + rs.getString(6) +
                        ", Reason = " + rs.getString(7));
            }
            log.info("End of accounts table.");
        }
    }*/
}
