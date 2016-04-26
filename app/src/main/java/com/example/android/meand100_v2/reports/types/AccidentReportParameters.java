package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import com.example.android.meand100_v2.R;

/**
 * Created by Idan on 26/01/2016.
 */
public class AccidentReportParameters extends ReportParameters {
    public AccidentReportParameters(Context context) {
        super(context);
        FIRST_QUESTION = context.getResources().getString(R.string.casualties_txt);
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.yes_button));
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.no_button));
        SECOND_QUESTION =context.getResources().getString(R.string.accident_type);
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.accident_type_hit_and_run));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.accident_type_multiple));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.accident_type_burn));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.accident_type_other));
    }

}
