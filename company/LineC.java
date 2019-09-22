package com.company;

import java.util.Date;

public class LineC extends Line {
    private Date date;
    private int waiting_time;
    LineC(){}
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }
}
