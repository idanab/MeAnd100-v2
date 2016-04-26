package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import com.example.android.meand100_v2.R;

/**
 * Created by Idan on 26/01/2016.
 */
public class AssaultReportParameters extends ReportParameters {
    public AssaultReportParameters(Context context) {
        super(context);
        FIRST_QUESTION = context.getResources().getString(R.string.casualties_txt);
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.yes_button));
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.no_button));
        SECOND_QUESTION = context.getResources().getString(R.string.assault_type);
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.assault_type_street_brawl));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.assault_type_sexual));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.assault_type_domestic));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.assault_type_other));
    }
}
