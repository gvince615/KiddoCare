package com.vintek_ss.vince.kiddocare;

/**
 * Created by VINCE-LTPC on 12/6/2014.
 */
public class customAdapterEvent {
    private String eventTitle;
    private String eventTimeS;
    private String eventTimeE;
    private String eventDate;


    public customAdapterEvent(String eventTitle, String eventTimeS, String eventTimeE, String eventDate) {
        super();
        this.eventTitle = eventTitle;
        this.eventTimeS = eventTimeS;
        this.eventTimeE = eventTimeE;
        this.eventDate = eventDate;
    }

    public String geteventTitle() {
        return eventTitle;
    }


    public String geteventTimeS() {
        return eventTimeS;
    }

    public String geteventTimeE() {
        return eventTimeE;
    }

    public String geteventDate() {
        return eventDate;
    }

}
