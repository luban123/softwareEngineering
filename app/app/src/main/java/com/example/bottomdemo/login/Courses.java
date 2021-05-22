package com.example.bottomdemo.login;

import java.io.Serializable;

public class Courses implements Serializable {
    private String cid;
    private String cname;
    private String tid;
    private String cstarttime;
    private String cendtime;

    public Courses(){

    }
    public Courses(String cid, String cname, String tid, String cstarttime, String cendtime) {
        this.cid = cid;
        this.cname = cname;
        this.tid = tid;
        this.cstarttime = cstarttime;
        this.cendtime = cendtime;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCstarttime() {
        return cstarttime;
    }

    public void setCstarttime(String cstarttime) {
        this.cstarttime = cstarttime;
    }

    public String getCendtime() {
        return cendtime;
    }

    public void setCendtime(String cendtime) {
        this.cendtime = cendtime;
    }
}
