package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Idan on 26/01/2016.
 */
public abstract class ReportParameters {
    protected Context context;
    protected static String FIRST_QUESTION = null;
    protected static ArrayList<String> possibleFirstAnswersArray = null;
    protected static String SECOND_QUESTION = null;
    protected static ArrayList<String> possibleSecondAnswersArray = null;

    public ReportParameters(Context context) {
        this.context = context;
        possibleFirstAnswersArray = new ArrayList<String>();
        possibleSecondAnswersArray = new ArrayList<String>();
    }

    public static String getFIRST_QUESTION() {
        return FIRST_QUESTION;
    }

    public static ArrayList<String> getPossibleFirstAnswersArray() {
        return possibleFirstAnswersArray;
    }

    public static String getSECOND_QUESTION() {
        return SECOND_QUESTION;
    }

    public static ArrayList<String> getPossibleSecondAnswersArray() {
        return possibleSecondAnswersArray;
    }
}
