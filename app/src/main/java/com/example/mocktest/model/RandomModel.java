package com.example.mocktest.model;

public class RandomModel {


    Throwable throwable  ;


    public RandomModel(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }




    private String time;
    private int RSRP;
    private int RSRQ;
    private int SINR;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRSRP() {
        return RSRP;
    }

    public void setRSRP(int RSRP) {
        this.RSRP = RSRP;
    }

    public int getRSRQ() {
        return RSRQ;
    }

    public void setRSRQ(int RSRQ) {
        this.RSRQ = RSRQ;
    }

    public int getSINR() {
        return SINR;
    }

    public void setSINR(int SINR) {
        this.SINR = SINR;
    }
}
