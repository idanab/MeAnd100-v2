package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import com.example.android.meand100_v2.R;

/**
 * Created by Idan on 26/01/2016.
 */
public class HijackReportParameters extends ReportParameters {
    public HijackReportParameters(Context context) {
        super(context);
        FIRST_QUESTION = context.getResources().getString(R.string.kidnapping_text);
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.one_kidnapping_txt));
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.two_to_four_kidnapping_txt));
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.five_to_eight_kidnapping_txt));
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.more_than_eight_kidnapping_txt));

        SECOND_QUESTION = context.getResources().getString(R.string.kidnappers_txt);
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.one_kidnappers_txt));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.two_to_four_kidnappers_txt));;
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.two_to_four_kidnappers_txt));;
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.more_than_ten_kidnappers_txt));;

    }
}
