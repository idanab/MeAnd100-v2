package com.example.android.meand100_v2.reports.types;

import android.content.Context;

import com.example.android.meand100_v2.R;

/**
 * Created by Idan on 26/01/2016.
 */
public class TerrorAttackReportParameters extends  ReportParameters {
    public TerrorAttackReportParameters(Context context) {
        super(context);
        FIRST_QUESTION = context.getResources().getString(R.string.is_terrorist_neutralize_txt);
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.yes_button));
        possibleFirstAnswersArray.add(context.getResources().getString(R.string.no_button));
        SECOND_QUESTION = context.getResources().getString(R.string.attack_type);
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.attack_type_stabbing));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.attack_type_bomb));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.attack_type_suicide));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.attack_type_bottle));
        possibleSecondAnswersArray.add(context.getResources().getString(R.string.attack_type_stones));

    }




}
