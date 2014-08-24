/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.edu.uts.aip.detentiontracker;

import java.io.*;
import javax.enterprise.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class DetentionController implements Serializable {

    private Detention detention = new Detention();

    public Detention getDetention() {
        return detention;
    }
    
       /**
     * Load the details of a detention from the database.
     * @param index the unique database id of the detention to retrieve
     */
    public void loadDetention(int index) {
        detention = DetentionListDatabase.read(index);
    }
    
    /**
     * Save the current detention as a new record in the database.
     * @return a redirect to view the whole waiting list
     */
    public String createDetention() {
        DetentionListDatabase.create(detention);
        return "view?faces-redirect=true";
    }
    
    /**
     * Update the record in the database whose database id matches that of the current group.
     * @return a redirect to view the whole waiting list
     */
    public String editDetention() {
        DetentionListDatabase.update(detention);
        return "view?faces-redirect=true";
    }
    
    /**
     * Delete the record from the database that matches the current detention's database id.
     * @return a redirect to view the whole waiting list
     */
    public String removeDetention(int detention) {
        DetentionListDatabase.delete(detention);
        return "view?faces-redirect=true";
    }
    
    public String removeDetention() {
        DetentionListDatabase.delete(detention.getId());
        return "view?faces-redirect=true";
    }    
}
