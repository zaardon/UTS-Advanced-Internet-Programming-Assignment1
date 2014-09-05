/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DetentionDAO implements Serializable {

    
    private static final String JNDI_NAME = "jdbc/detentiontracker";
    private static final String SELECT_DETENTIONS =
            "select DetentionID, FirstName, LastName, YearGroup, DetentionType, Department, Reason " +
            "from detentions ";
    private static final String BY_ID = "WHERE DetentionID = ?";
    private static final String DELETE_DETENTION = "DELETE FROM detentions ";
    private static final String UPDATE_DETENTION = "UPDATE detentions SET FirstName = ?, LastName = ?, " +
                "YearGroup = ?, DetentionType = ?, Department = ?, Reason = ?"; 

    private static final String INSERT_NEW_DETENTION = "INSERT INTO detentions "+
            "(DetentionID, FirstName, LastName, YearGroup, DetentionType, Department, Reason)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private int nextUsedIDNum = generateDBUniqueID();

    public int getNextUsedIDNum() {
        nextUsedIDNum++;
        return nextUsedIDNum;
    }
    
    // Helper to generate unique identifiers
    private static int idGenerator = new DetentionDAO().generateDBUniqueID();
    
    private static synchronized int generateUniqueId() {
        idGenerator++;
        return idGenerator;
    }
    
    private int generateDBUniqueID()
    {
        return findHighestIDNumber(findAllDetentions());      
    }
    
    public static void createDetention(DetentionDTO detention){
        detention.setId(generateUniqueId());
        try{
        DataSource ds = InitialContext.doLookup(JNDI_NAME);
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_NEW_DETENTION);
        ps.setInt(1, detention.getId());
        ps.setString(2, detention.getFName());
        ps.setString(3, detention.getLName());
        ps.setInt(4, detention.getYear());
        ps.setString(5, detention.getType());
        ps.setString(6, detention.getDept());
        ps.setString(7, detention.getReason());
        ps.executeUpdate();
        
        }
        catch(SQLException | NamingException e){
            System.out.println(e);
        }
    }     
    
    public DetentionDTO readDetention(int detentionID) {
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(SELECT_DETENTIONS + BY_ID)) {

                ps.setInt(1, detentionID);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // detention found
                        return createDetentionDTO(rs);
                    } 
                }
            }
        } catch (NamingException | SQLException e) {
            
        }
        return null;
    }
   
    
    public ArrayList<DetentionDTO> findAllDetentions(){ // throws DataStoreException 
        ArrayList<DetentionDTO> results = new ArrayList<>();
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SELECT_DETENTIONS)) {

                while (rs.next()) {
                    results.add(createDetentionDTO(rs));
                }
            }
        } catch (NamingException | SQLException e) {
            
        }
        return results;
    }
    
    public static void updateDetention(DetentionDTO detention){                
        try{
        DataSource ds = InitialContext.doLookup(JNDI_NAME);
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_DETENTION + BY_ID);
        
        ps.setString(1, detention.getFName());
        ps.setString(2, detention.getLName());
        ps.setInt(3, detention.getYear());
        ps.setString(4, detention.getType());
        ps.setString(5, detention.getDept());
        ps.setString(6, detention.getReason());
        ps.setInt(7, detention.getId());
        ps.executeUpdate();
        
        }
        catch(SQLException | NamingException e){
            System.out.println(e);
        }
    }

    public static void removeDetention(int detentionID){      
        try{
        DataSource ds = InitialContext.doLookup(JNDI_NAME);
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement(DELETE_DETENTION + BY_ID);
        
        ps.setInt(1, detentionID);
        ps.executeUpdate();
        
        }
        catch(SQLException | NamingException e){
            System.out.println(e);
        }
    }
       
     private DetentionDTO createDetentionDTO(ResultSet rs) throws SQLException {
        DetentionDTO result = new DetentionDTO();
        result.setId(rs.getInt("DetentionID"));
        result.setFName(rs.getString("FirstName"));
        result.setLName(rs.getString("LastName"));
        result.setYear(rs.getInt("YearGroup"));
        result.setType(rs.getString("DetentionType"));
        result.setDept(rs.getString("Department"));
        result.setReason(rs.getString("Reason"));
        return result;
    }

    
    private int findHighestIDNumber(ArrayList<DetentionDTO> detentions)
    {
        int idMax = 0;       
        for(DetentionDTO det : detentions)
        {
            if(idMax <= det.getId())
            {
                idMax = det.getId();
            }
        }
        return idMax;   
    }
}