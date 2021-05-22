package com.example.bottomdemo.login;

import java.io.Serializable;
import java.util.Date;


public class Finish_qd implements Serializable {
    private String cid;
    private String cname;
    private Date qtime;
    private String continue_time;
    public Finish_qd(){

    }
    public Finish_qd(String cid, String cname, Date qtime, String continue_time) {
        this.cid = cid;
        this.cname = cname;
        this.qtime = qtime;
        this.continue_time = continue_time;
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

    public Date getQtime() {
        return qtime;
    }

    public void setQtime(Date qtime) {
        this.qtime = qtime;
    }

    public String getContinue_time() {
        return continue_time;
    }

    public void setContinue_time(String continue_time) {
        this.continue_time = continue_time;
    }
}
