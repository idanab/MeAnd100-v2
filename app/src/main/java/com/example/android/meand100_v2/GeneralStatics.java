package com.example.android.meand100_v2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import android.provider.Settings.Secure;


/**
 * Created by Idan on 16/02/2016.
 */
public class GeneralStatics {

    /**
     * using implicit intent to open a url address in a browser
     * @param context you can sent it by typing getActivity()
     * @param url the url to open
     */
    public static void goToWebsite(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    public static String getPhoneID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

    }

    public static void sendReportCancelation(Context context) {
        makeToast(context, "report canceled: "+ getPhoneID(context));
    }

    public static void sendPrimaryReport(Context context, String type, String selection1, String selection2) {
        makeToast(context, "primary report of type: " + type + "- "+ getPhoneID(context)+": " + "ans1: " + selection1 + " and2: " + selection2);
    }

    public static void sendSecondaryReport(Context context, String type, String selection1, String moreInfo) {
        makeToast(context, "secondary report of type: " + type + "- " + getPhoneID(context)+": " + "ans1: " + selection1 + "more info: " + moreInfo);
    }

    public static void makeToast(Context context, String text) {
        //Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        Log.e("Toast:", text);
    }

    public static void sendLocation(Context context, String location) {
        makeToast(context, "a location is being sent from " + getPhoneID(context)+": " + location);
    }

}
