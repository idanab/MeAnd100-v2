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


    public HeaderStory(String id, String header, Location location, GregorianCalendar date) {
        super(id);
        this.header = header;
        this.location = location;
        this.date = date;
    }

    public String getHeader() {
        return header;
    }


    public String getLocation() {
        return location.getProvider();
    }

    public String getDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(date);
        String dateFormatted = fmt.format(date.getTime());
        return dateFormatted;
    }

    public String getTime() {
        return  "";
    }
}
