package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import com.example.android.meand100_v2.R;

/**
 * Created by Idan on 26/01/2016.
 */
public class FireReportParameters extends ReportParameters {
    public FireReportParameters(Context context) {
        super(context);
        FIRST_QUESTION = context.getResources().getString(R.string.fire_text);
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.yes_button));
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.no_button));
        SECOND_QUESTION = context.getResources().getString(R.string.fire_location);
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.fire_type_privateresidence));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.fire_type_public));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.fire_type_agriculture));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.fire_type_other));
    }
}
