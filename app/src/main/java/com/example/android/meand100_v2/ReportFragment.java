package com.example.android.meand100_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.meand100_v2.reports.GeneralReport;
import com.example.android.meand100_v2.reports.NonurgentReport;
import com.example.android.meand100_v2.reports.types.AccidentReportParameters;
import com.example.android.meand100_v2.reports.types.AssaultReportParameters;
import com.example.android.meand100_v2.reports.types.Call;
import com.example.android.meand100_v2.reports.types.FireReportParameters;
import com.example.android.meand100_v2.reports.types.HijackReportParameters;
import com.example.android.meand100_v2.reports.types.ReportParameters;
import com.example.android.meand100_v2.reports.types.RobberyReportParameters;
import com.example.android.meand100_v2.reports.types.TerrorAttackReportParameters;

public class ReportFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_reports_acitvity_portal, null);
        GridView gridview = (GridView) root.findViewById(R.id.reports_list_gridview);
        gridview.setAdapter(new ImageAdapter(getActivity()));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                detectReportTypeByNumber(position);
            }
        });

        final Button non_urgent_reports_button = (Button) root.findViewById(R.id.non_urgent_button);
        non_urgent_reports_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), NonurgentReport.class);
                startActivity(intent);
            }
        });
        defineEmergancyDialerListener(root);
        return root;
    }


    private void detectReportTypeByNumber(int position) {

        switch(position)

        {
            case 0:
                //terror attack
                TerrorAttackReportParameters terrorAttack = new TerrorAttackReportParameters(getActivity().getApplicationContext());
                startNewReportForm(terrorAttack);
                break;
            case 1:
                //car accident
                AccidentReportParameters accident = new AccidentReportParameters(getActivity().getApplicationContext());
                startNewReportForm(accident);
                //AccidentReportParametersTry accident = new AccidentReportParametersTry(getActivity().getApplicationContext());
                //startNewReportFormTry(accident);
                break;
            case 2:
                //assault
                AssaultReportParameters assault = new AssaultReportParameters(getActivity().getApplicationContext());
                startNewReportForm(assault);
                break;
            case 3:
                //hijack
                HijackReportParameters hijack = new HijackReportParameters(getActivity().getApplicationContext());
                startNewReportForm(hijack);
                break;
            case 4:
                //fire
                FireReportParameters fire = new FireReportParameters(getActivity().getApplicationContext());
                startNewReportForm(fire);
                break;
            case 5:
                //robbery
                RobberyReportParameters robbery = new RobberyReportParameters(getActivity().getApplicationContext());
                startNewReportForm(robbery);
                break;
            default:
                Log.w("problem", "weird case for report, unrecognized!");
        }
}

    private void startNewReportForm(ReportParameters reportType) {
        Intent intent = new Intent(getActivity().getApplicationContext(), GeneralReport.class);
        intent.putExtra("reportType", reportType.getClass().getSimpleName());
        intent.putExtra("firstQuestion", reportType.getFIRST_QUESTION());
        intent.putExtra("possibleFirstAnswersArray", reportType.getPossibleFirstAnswersArray());
        intent.putExtra("secondQuestion", reportType.getSECOND_QUESTION());
        intent.putExtra("possibleSecondAnswersArray", reportType.getPossibleSecondAnswersArray());
        startActivity(intent);
    }

    private void defineEmergancyDialerListener(ViewGroup root) {
        FloatingActionButton btn = (FloatingActionButton) root.findViewById(R.id.call_100_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = Call.callEmergancyNumber();
                startActivity(callIntent);
            }
        });
    }

}