/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asmith
 */

package au.edu.uts.aip.detentiontracker;

import java.util.*;

public class DetentionList {
   
    private ArrayList<Detention> detentions;
    
    public DetentionList(){
        detentions = new ArrayList<>();
    }
    
    public List<Detention> getDetentions(){
        return detentions;
    }
    
    public void removeTask(Detention detention){
        detentions.remove(detention);
    }
    
    public void addTask(Detention detention){
        detentions.add(detention);
    }
}
