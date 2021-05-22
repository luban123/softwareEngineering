package com.example.bottomdemo.login;

import java.io.Serializable;
import java.util.Date;

public class Setqd implements Serializable {
    private String cid;
    private String starttime;
    private String continue_time;
    private String lng,lat;
    private String address;

    public Setqd()
    {

    }

    public Setqd(String cid, String starttime, String continue_time, String lng, String lat) {
        this.cid = cid;
        this.starttime = starttime;
        this.continue_time = continue_time;
        this.lng = lng;
        this.lat = lat;
    }

    public Setqd(String cid, String starttime, String continue_time, String lng, String lat, String address) {
        this.cid = cid;
        this.starttime = starttime;
        this.continue_time = continue_time;
        this.lng = lng;
        this.lat = lat;
        this.address = address;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getContinue_time() {
        return continue_time;
    }

    public void setContinue_time(String continue_time) {
        this.continue_time = continue_time;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
