package com.example.android.meand100_v2.reports.types;

import android.content.Context;

/**
 * Created by Idan on 26/01/2016.
 */
public class RobberyReportParameters extends ReportParameters {
    public RobberyReportParameters(Context context) {
        super(context);
        FIRST_QUESTION = "is there a robbery going on?";
        possibleFirstAnswersArray.add("Yes");
        possibleFirstAnswersArray.add("No");
        SECOND_QUESTION = "what is the type of the robbery?";
        possibleSecondAnswersArray.add("Bank");
        possibleSecondAnswersArray.add("Pickpocket");
        possibleSecondAnswersArray.add("Domestic");
        possibleSecondAnswersArray.add("other");
    }
}
