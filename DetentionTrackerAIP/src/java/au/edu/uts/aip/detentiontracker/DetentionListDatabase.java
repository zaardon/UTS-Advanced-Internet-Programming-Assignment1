/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.util.*;

public class DetentionListDatabase implements Serializable {

    // Helper to generate unique identifiers
    private static int idGenerator;
    private static synchronized int generateUniqueId() {
        idGenerator++;
        return idGenerator;
    }
    
    private static LinkedHashMap<Integer, Detention> detentions = new LinkedHashMap<>();
    
    public static Collection<Detention> findAll() {
        return detentions.values();
    }
    
    public static void create(Detention detention) {
        detention.setId(generateUniqueId());
        detentions.put(detention.getId(), detention);
    }
    
    public static Detention read(int index) {
        return detentions.get(index);
    }
    
    public static void update(Detention group) {
        detentions.put(group.getId(), group);
    }
    
    public static void delete(int index) {
        detentions.remove(index);
    }
    
}