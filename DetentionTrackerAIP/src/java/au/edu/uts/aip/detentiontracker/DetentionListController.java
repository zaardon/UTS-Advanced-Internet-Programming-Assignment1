/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.util.*;
import javax.enterprise.context.*;
import javax.faces.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class DetentionListController implements Serializable {
    
    public Collection<Detention> getDetentions() {
        return DetentionListDatabase.findAll();
    }
    
}
