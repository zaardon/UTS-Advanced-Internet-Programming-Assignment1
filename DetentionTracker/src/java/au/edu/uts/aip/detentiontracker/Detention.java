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

public class Detention {
    
    private String fname;
    private String lname;
    private int year;
    private String type;
    private String dept;
    private String reason;
    private DetentionList detentionList;

    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDetentionList(DetentionList newDList){
        if (null != detentionList){
            detentionList.removeTask(this);
        }
        detentionList = newDList;
        detentionList.addTask(this);
    }
}
