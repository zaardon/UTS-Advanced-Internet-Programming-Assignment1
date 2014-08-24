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
    
}
