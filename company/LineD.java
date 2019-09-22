package com.company;

import java.util.Date;

public class LineD extends Line{
    private Date dateFrom;
    private Date dateTo;

    LineD() { }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
