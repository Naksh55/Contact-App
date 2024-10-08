package com.naksh.contactapp;

public class CallModel {
    String calltype,name,callduration,time,callername;

    public CallModel() {
    }

    public CallModel(String calltype, String name, String callduration, String time) {
        this.calltype = calltype;
        this.name = name;
        this.callduration = callduration;
        this.time = time;
    }

    public CallModel(String calltype, String name, String callduration, String time, String callername) {
        this.calltype = calltype;
        this.name = name;
        this.callduration = callduration;
        this.time = time;
        this.callername = callername;
    }

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallduration() {
        return callduration;
    }

    public void setCallduration(String callduration) {
        this.callduration = callduration;
    }

    public String getTime() {
        return time;
    }

    public String getCallername() {
        return callername;
    }

    public void setCallername(String callername) {
        this.callername = callername;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
