package com.example.android.meand100_v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.meand100_v2.reports.GeneralReport;
import com.example.android.meand100_v2.reports.types.AccidentReportParameters;
import com.example.android.meand100_v2.reports.types.AssaultReportParameters;
import com.example.android.meand100_v2.reports.types.FireReportParameters;
import com.example.android.meand100_v2.reports.types.HijackReportParameters;
import com.example.android.meand100_v2.reports.types.ReportParameters;
import com.example.android.meand100_v2.reports.types.RobberyReportParameters;
import com.example.android.meand100_v2.reports.types.TerrorAttackReportParameters;

public class CustomAdapter extends BaseAdapter{

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Activity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.programlist, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                /*Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show()*/;
                detectReportTypeByNumber(position);
            }
        });

        return rowView;
    }

    private void detectReportTypeByNumber(int position) {

        switch(position)

        {
            case 0:
                //terror attack
                TerrorAttackReportParameters terrorAttack = new TerrorAttackReportParameters(this.context.getApplicationContext());
                startNewReportForm(terrorAttack);
                break;
            case 1:
                //car accident
                AccidentReportParameters accident = new AccidentReportParameters(this.context.getApplicationContext());
                startNewReportForm(accident);
                //AccidentReportParametersTry accident = new AccidentReportParametersTry(getActivity().getApplicationContext());
                //startNewReportFormTry(accident);
                break;
            case 2:
                //assault
                AssaultReportParameters assault = new AssaultReportParameters(this.context.getApplicationContext());
                startNewReportForm(assault);
                break;
            case 3:
                //hijack
                HijackReportParameters hijack = new HijackReportParameters(this.context.getApplicationContext());
                startNewReportForm(hijack);
                break;
            case 4:
                //fire
                FireReportParameters fire = new FireReportParameters(this.context.getApplicationContext());
                startNewReportForm(fire);
                break;
            case 5:
                //robbery
                RobberyReportParameters robbery = new RobberyReportParameters(this.context.getApplicationContext());
                startNewReportForm(robbery);
                break;
            default:
                Log.w("problem", "weird case for report, unrecognized!");
        }
    }

    private void startNewReportForm(ReportParameters reportType) {
        Intent intent = new Intent(this.context, GeneralReport.class);
        intent.putExtra("reportType", reportType.getClass().getSimpleName());
        intent.putExtra("firstQuestion", reportType.getFIRST_QUESTION());
        intent.putExtra("possibleFirstAnswersArray", reportType.getPossibleFirstAnswersArray());
        intent.putExtra("secondQuestion", reportType.getSECOND_QUESTION());
        intent.putExtra("possibleSecondAnswersArray", reportType.getPossibleSecondAnswersArray());
        this.context.startActivity(intent);
    }

}