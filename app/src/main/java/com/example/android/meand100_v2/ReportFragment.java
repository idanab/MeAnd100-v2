package com.example.android.meand100_v2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
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

import java.util.ArrayList;

public class ReportFragment extends Fragment {
    GridView gv;
    Context context;
    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.explosion,R.drawable.accident,R.drawable.assult,R.drawable.hijack,R.drawable.fire,R.drawable.robbery};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_reports_acitvity_portal, null);
        Resources res= this.getResources();
        String [] prgmNameList={res.getString(R.string.terrorattack_report_button),res.getString(R.string.accident_report_button),res.getString(R.string.assult_report_button),res.getString(R.string.hijack_report_button),res.getString(R.string.fire_report_button),res.getString(R.string.robbery_report_button)};
        gv=(GridView) root.findViewById(R.id.reports_list_gridview);
        gv.setAdapter(new CustomAdapter(getActivity(), prgmNameList,prgmImages));


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

    private void defineEmergancyDialerListener(ViewGroup root) {
        FloatingActionButton btn = (FloatingActionButton) root.findViewById(R.id.call_100_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = Call.callEmergancyNumber();
                startActivity(callIntent);
            }
        });
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setDuration(3000);
        rotate.setRepeatCount(Animation.INFINITE);
        btn.setAnimation(rotate);
    }

}