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

    public ArrayList<Detention> getAllDetentions() {
            return new DetentionListDatabase().findAllDetentions();
    }
        
    public int sizeOfDetentionList(){
        return getAllDetentions().size();
    }   
}
