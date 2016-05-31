package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import com.example.android.meand100_v2.R;

/**
 * Created by Idan on 26/01/2016.
 */
public class RobberyReportParameters extends ReportParameters {
    public RobberyReportParameters(Context context) {
        super(context);
        FIRST_QUESTION = context.getResources().getString(R.string.is_there_robbery_txt);
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.yes_button));
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.no_button));
        SECOND_QUESTION = context.getResources().getString(R.string.robbery_type);
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.robbery_type_bank));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.robbery_type_pickpocket));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.robbery_type_domestic));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.accident_type_other));
    }
}
