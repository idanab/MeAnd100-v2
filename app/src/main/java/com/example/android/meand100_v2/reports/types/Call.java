package com.example.android.meand100_v2.reports.types;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by Idan on 27/01/2016.
 * A class for handling call intents to emergancy numbers
 */
public class Call {

    private static final String emergancyNumber = "100";

    public static Intent callEmergancyNumber() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+emergancyNumber));
        return callIntent;
    }
}
