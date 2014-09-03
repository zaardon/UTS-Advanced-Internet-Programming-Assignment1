/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.util.ArrayList;
import javax.enterprise.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class DetentionController implements Serializable {

    private DetentionDTO detention = new DetentionDTO();

    public DetentionDTO getDetention() {
        return detention;
    }
    
       /**
     * Load the details of a detention from the database.
     * @param index the unique database id of the detention to retrieve
     */
    public void loadDetention(int index) {
        detention = new DetentionDAO().readDetention(index);
    }
     
    /**
     * Save the current detention as a new record in the database.
     * @return a redirect to view the whole waiting list
     */
    public String createDetention() {
        DetentionDAO.createDetention(detention);
        return "view?faces-redirect=true";
    }
    
    /**
     * Update the record in the database whose database id matches that of the current group.
     * @return a redirect to view the whole waiting list
     */
    public String editDetention() {
        DetentionDAO.updateDetention(detention);
        return "view?faces-redirect=true";
    }
    
    /**
     * Delete the record from the database that matches the current detention's database id.
     * @return a redirect to view the whole waiting list
     */
    public String removeDetention(int index) {
        DetentionDAO.removeDetention(index);
        return "view?faces-redirect=true";
    }
    
    public ArrayList<DetentionDTO> getAllDetentions() {
            return new DetentionDAO().findAllDetentions();
    }
        
    public int sizeOfDetentionList(){
        System.out.println(getAllDetentions().size());
        return getAllDetentions().size();
    } 
}
