/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DetentionListDatabase implements Serializable {

    
    private static final String JNDI_NAME = "jdbc/detentiontracker";
    private static final String SELECT_DETENTION =
            "select DetentionID, FirstName, LastName, YearGroup, DetentionType, Department, Reason " +
            "from detentions ";
    private static final String ALL_DETENTIONS = SELECT_DETENTION;

    private int nextUsedIDNum = generateDBUniqueID();

    public int getNextUsedIDNum() {
        nextUsedIDNum++;
        return nextUsedIDNum;
    }
    
    // Helper to generate unique identifiers
    private static int idGenerator = new DetentionListDatabase().generateDBUniqueID();
    
    private static synchronized int generateUniqueId() {
        idGenerator++;
        return idGenerator;
    }
    
    private int generateDBUniqueID()
    {
        return findHighestIDNumber(findAllDetentions());      
    }
    
    public static void createDetention(Detention detention){
        detention.setId(generateUniqueId());
        String insertStatement = "INSERT INTO detentions " +
                "(DetentionID, FirstName, LastName, YearGroup, DetentionType, Department, Reason) " +
                "VALUES ( "+detention.getId()+", '"+ detention.getFName() +"', '"+ detention.getLName() +
                "', "+ detention.getYear() +", '"+ detention.getType()+"', '"+detention.getDept()+
                "', '"+detention.getReason()+"' )";
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(insertStatement);

        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }       
    }  
    
    
    public Detention readDetention(int index) {

        Detention tmpDet = new Detention();
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SELECT_DETENTION + "WHERE DetentionID = "+ index)) {

                while(rs.next()){
                 tmpDet = createRowDTO(rs);
                }
            }
        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }
        return tmpDet;
    }
   
    
    public static void updateDetention(Detention detention){
        String updateStatement = "UPDATE detentions " +
                "SET FirstName = '"+ detention.getFName() +
                "', LastName = '"+ detention.getLName() + 
                "', YearGroup = "+ detention.getYear() +
                ", DetentionType = '"+ detention.getType() +
                "', Department = '"+detention.getDept() +
                "', Reason = '"+detention.getReason()+
                "' WHERE DetentionID = "+detention.getId();
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(updateStatement);

            System.out.println("Success");
        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }       
    }

    public static void removeDetention(int index){
        String deleteStatement = "DELETE FROM detentions " +
                "WHERE DetentionID = "+ index;
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(deleteStatement);

        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }       
    }
    
    
     private Detention createRowDTO(ResultSet rs) throws SQLException {
        Detention result = new Detention();
        result.setId(rs.getInt("DetentionID"));
        result.setFName(rs.getString("FirstName"));
        result.setLName(rs.getString("LastName"));
        result.setYear(rs.getInt("YearGroup"));
        result.setType(rs.getString("DetentionType"));
        result.setDept(rs.getString("Department"));
        result.setReason(rs.getString("Reason"));
        return result;
    }

    /**
     * Retrieves all accounts from the database as a complete list of Data Transfer Objects.
     * @return a list containing every row of the database
     * @throws DataStoreException if an exception occurred while communicating with the database.
     */
    public ArrayList<Detention> findAllDetentions(){ // throws DataStoreException 
        ArrayList<Detention> results = new ArrayList<>();
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(ALL_DETENTIONS)) {

                while (rs.next()) {
                    results.add(createRowDTO(rs));
                }
            }
        } catch (NamingException | SQLException e) {
            
        }
        return results;
    }
    
    private int findHighestIDNumber(ArrayList<Detention> detentions)
    {
        int idMax = 0;
        
        for(Detention det : detentions)
        {
            if(idMax <= det.getId())
            {
                idMax = det.getId();
            }
        }
        System.out.println(idMax);
        
        return idMax;
        
    }
}