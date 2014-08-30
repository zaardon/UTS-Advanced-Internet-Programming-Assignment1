/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.edu.uts.aip.detentiontracker;

import javax.validation.constraints.*;

/**
 *
 * @author Alex
 */
public class Detention {
    
    private int id;
    private String fname;
    private String lname;
    private int year;
    private String type;
    private String dept;
    private String reason;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    //Only allows letters and single white spaces/dashes (in the case of double names)
    @Pattern(regexp="[A-Za-z -]*")
    @Size(min = 1)
    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

    //Only allows letters and single white spaces/dashes (in the case of double names)
    @Pattern(regexp="[A-Za-z -]*")
    @Size(min = 1)
    public String getLName() {
        return lname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    //Only allows numbers, letters, dashes and single white spaces
    @Pattern(regexp="[A-Za-z 0-9-]*")
    @Size(min = 1, max = 40)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
