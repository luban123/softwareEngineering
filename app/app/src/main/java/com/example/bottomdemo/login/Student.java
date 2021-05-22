package com.example.bottomdemo.login;

import java.io.Serializable;

public class Student implements Serializable {

    private String sno;
    private String sname;
    private String sphone;
    private String spassword;
    private String sphoto;
    public Student() {
    }

    public Student(String sno, String sname, String sphone, String spassword,String sphoto) {
        this.sno = sno;
        this.sname = sname;
        this.sphone = sphone;
        this.spassword = spassword;
        this.sphoto=sphoto;
    }
    public String getSphoto() {
        return sphoto;
    }

    public void setSphoto(String sphoto) {
        this.sphoto = sphoto;
    }
    public String getSno() {
        return sno;
    }
    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", sphone='" + sphone + '\'' +
                ", spassword='" + spassword + '\'' +
                '}';
    }
}
