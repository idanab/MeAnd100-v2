package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Idan on 28/01/2016.
 */
public abstract class ReportParameterTry {
    protected Context context;
    protected static HashMap<String, ArrayList<String>> questionsAndAnswers;

    public ReportParameterTry (Context context) {
        this.context = context;
        questionsAndAnswers = new HashMap<String, ArrayList<String>>();
    }

    public static HashMap<String, ArrayList<String>> getQuestionsAndAnswers() {
        return questionsAndAnswers;
    }
}
