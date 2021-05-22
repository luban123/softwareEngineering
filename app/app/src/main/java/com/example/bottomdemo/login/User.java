package com.example.bottomdemo.login;

import java.io.Serializable;

public class User implements Serializable {
    private String tid;
    private String tname;
    private String tphone;
    private String tpassword;

    public User() {
    }

    public User(String tid, String tname, String tphone, String tpassword) {
        this.tid = tid;
        this.tname = tname;
        this.tphone = tphone;
        this.tpassword = tpassword;
    }
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }




    @Override
    public String toString() {
        return "User{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tphone='" + tphone + '\'' +
                '}';
    }
}
