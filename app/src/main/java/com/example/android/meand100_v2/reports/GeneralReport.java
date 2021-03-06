package com.example.android.meand100_v2.reports;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.meand100_v2.GeneralStatics;
import com.example.android.meand100_v2.MainActivity;
import com.example.android.meand100_v2.MoreDetailsReport;
import com.example.android.meand100_v2.R;
import com.example.android.meand100_v2.reports.types.Call;


import java.util.ArrayList;
import java.util.List;


public class GeneralReport extends AppCompatActivity  {
    //TcpClient client;
    private Bundle extras;
    LocationManager mLocationManager;
    String selection1, selection2;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_report);
        //TODO: send to the police at this point
        extras = getIntent().getExtras();
        addQuestions();
        defineEmergancyDialerListener();
        setSendButtonAction();
        TextView reportSentTxt = (TextView) findViewById(R.id.report_sent_text);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
        reportSentTxt.startAnimation(hyperspaceJump);
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(GeneralReport.this).create(); //Read Update
        alertDialog.setTitle(R.string.notice);
        alertDialog.setMessage(getString(R.string.block_warning));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);//go back to main page
                startActivity(intent);
                finish();
            }
        });
        alertDialog.show();
        GeneralStatics.sendReportCancelation(getApplicationContext());
    }

    @Override
    protected void onStart() {
        location = getLastKnownLocation();
        double lat = 0;
        double lon = 0;
        try{
            lat = location.getLatitude();
            lon = location.getLongitude();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        GeneralStatics.sendLocation(getApplicationContext(), "lat: " + lat + " lon: " + lon);
        super.onStart();
    }

    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        boolean gps_enabled=false;
        try {
            gps_enabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if(!gps_enabled) {
                Toast.makeText(this, getString(R.string.gps_enable_request), Toast.LENGTH_SHORT).show();
                /*GeneralStatics.makeToast(this,"GPS is not on");
                Intent gpsOptionsIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(gpsOptionsIntent);*/
                return  null;
            }
            else {
                List<String> providers = mLocationManager.getProviders(true);
                Location l =null;
                while (l == null) {
                    l = mLocationManager.getLastKnownLocation(providers.get(0));
                }
                return l;
            }
        } catch(Exception ex) {
            return null;
        }
    }

    private void setSendButtonAction() {
        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFormFilled()) {
                    GeneralStatics.sendPrimaryReport(getApplicationContext(), extras.getString("reportType"), selection1, selection2);
                    Intent intent = new Intent(GeneralReport.this, MoreDetailsReport.class);
                    intent.putExtra("reportType", extras.getString("reportType").toString());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isAllFormFilled() {
        try{
            RadioGroup group1 = (RadioGroup) findViewById(R.id.first_radio_group);
            String radiovalue1 = ((RadioButton)findViewById(group1.getCheckedRadioButtonId())).getText().toString(); //TODO:not good statement
            RadioGroup group2 = (RadioGroup) findViewById(R.id.second_radio_group);
            String radiovalue2 = ((RadioButton)findViewById(group2.getCheckedRadioButtonId())).getText().toString();
            if((group1.getCheckedRadioButtonId()>-1)&&(group2.getCheckedRadioButtonId()>-1)) {
                return true;
            }
            return false;
        }
        catch (NullPointerException e) { //happens if one or more questions have not been answered
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.fill_details)
                    .setPositiveButton(R.string.continue_txt, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return false;
    }

    /**
     * adding the questions of the specific type of report
     */
    private void addQuestions() {
        TextView question1 = (TextView) findViewById(R.id.first_question_header);
        question1.setText(extras.getString("firstQuestion"));
        TextView question2 = (TextView) findViewById(R.id.second_question_header);
        question2.setText(extras.getString("secondQuestion"));
        setRadioButtonsFromArrayToRadioGroup((RadioGroup) findViewById(R.id.first_radio_group), extras.getStringArrayList("possibleFirstAnswersArray"));
        setRadioButtonsFromArrayToRadioGroup((RadioGroup) findViewById(R.id.second_radio_group), extras.getStringArrayList("possibleSecondAnswersArray"));

    }

    /**
     * putting the exact buttons that concerning the specific reports
     * @param radioGroup a group to populate
     * @param array values to populate to
     */
    private void setRadioButtonsFromArrayToRadioGroup(RadioGroup radioGroup, ArrayList<String> array) {
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        for(int i=0; i<radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if(i<array.size()) {
                radioButton.setText(array.get(i));
                radioButton.setVisibility(View.VISIBLE);
            }
            else
                radioButton.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void defineEmergancyDialerListener() {
        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.call_100_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = Call.callEmergancyNumber();
                startActivity(callIntent);
            }
        });
    }
}
