package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import com.example.android.meand100_v2.R;

import java.util.ArrayList;

/**
 * Created by Idan on 28/01/2016.
 */
public class AccidentReportParametersTry extends ReportParameterTry {
    public AccidentReportParametersTry(Context context) {
        super(context);
        ArrayList<String> answersTo1 = new ArrayList<String>();
        answersTo1.add(context.getResources().getString(R.string.yes_button));
        answersTo1.add(context.getResources().getString(R.string.no_button));
        ArrayList<String> answersTo2 = new ArrayList<String>();
        answersTo2.add(context.getResources().getString(R.string.accident_type_hit_and_run));
        answersTo2.add(context.getResources().getString(R.string.accident_type_multiple));
        answersTo2.add(context.getResources().getString(R.string.accident_type_burn));
        answersTo2.add(context.getResources().getString(R.string.accident_type_other));
        questionsAndAnswers.put(context.getResources().getString(R.string.casualties_txt), answersTo1);
        questionsAndAnswers.put(context.getResources().getString(R.string.accident_type), answersTo2);
    }
}
