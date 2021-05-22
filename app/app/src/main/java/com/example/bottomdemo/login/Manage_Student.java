package com.example.bottomdemo.login;

import java.io.Serializable;

public class Manage_Student implements Serializable {
    private String sno;
    private String cid;
    private String tid;
    private String status;
    public Manage_Student(){

    }
    public Manage_Student(String sno, String cid, String tid, String status) {
        this.sno = sno;
        this.cid = cid;
        this.tid = tid;
        this.status = status;
    }

    public String getSno() {
        return sno;
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
