package com.example.bottomdemo.login;

import java.io.Serializable;
import java.util.Date;

public class Qdrecord implements Serializable {
    private String qid;
    private String sname;
    private String sno;
    private String cid;
    private Date qtime;
    private String Ing;
    private String lat;
    private String qteachermsg;
    private String address;

    public Qdrecord()
    {

    }

    public Qdrecord(String qid, String sname, String sno, String cid, Date qtime, String ing, String lat, String qteachermsg, String address) {
        this.qid = qid;
        this.sname = sname;
        this.sno = sno;
        this.cid = cid;
        this.qtime = qtime;
        Ing = ing;
        this.lat = lat;
        this.qteachermsg = qteachermsg;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getSno() {
        return sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getQtime() {
        return qtime;
    }

    public void setQtime(Date qtime) {
        this.qtime = qtime;
    }

    public String getIng() {
        return Ing;
    }

    public void setIng(String ing) {
        Ing = ing;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getQteachermsg() {
        return qteachermsg;
    }

    public void setQteachermsg(String qteachermsg) {
        this.qteachermsg = qteachermsg;
    }
}
