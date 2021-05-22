package com.example.bottomdemo.login;

import java.io.Serializable;

public class Student_info implements Serializable{
    private String sno;
    private String tid;
    private String tname;
    private String cid;
    private String cname;
    private String status;
    public Student_info(){

    }

    public Student_info(String sno, String tid, String tname, String cid, String cname, String status) {
        this.sno = sno;
        this.tid = tid;
        this.tname = tname;
        this.cid = cid;
        this.cname = cname;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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
}
