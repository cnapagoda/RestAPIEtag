package com.chandana.rest.api;

import java.util.Date;

/**
 * Created by chandana on 9/18/16.
 */
public class User {

    private int id;
    private String fname;
    private String lname;
    private Date lastUpdateTime;

    public User(int id, String fname, String lname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.lastUpdateTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
