package com.example.android.meand100_v2.news;

import android.location.Location;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Idan on 27/01/2016.
 */
public class HeaderStory extends Story {
    final String header;
    final Location location;
    final GregorianCalendar date;
    final String type;


    public HeaderStory(String id, String header, Location location, GregorianCalendar date , String type) {
        super(id);
        this.header = header;
        this.location = location;
        this.date = date;
        this.type = type;
    }

    public String getHeader() {
        return header;
    }


    public String getLocation() {
        return location.getProvider();
    }

    public String getDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy  HH:mm");
        fmt.setCalendar(date);
        String dateFormatted = fmt.format(date.getTime());
        return dateFormatted;
    }

    public String getTime() {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        fmt.setCalendar(date);
        String dateFormatted = fmt.format(date.getTime());
        return dateFormatted;
    }
    public String getType() {return this.type;}
}
