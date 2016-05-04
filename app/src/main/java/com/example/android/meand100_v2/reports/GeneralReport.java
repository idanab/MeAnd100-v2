package com.example.android.meand100_v2.reports;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.android.meand100_v2.GeneralStatics;
import com.example.android.meand100_v2.MainActivity;
import com.example.android.meand100_v2.Manifest;
import com.example.android.meand100_v2.MoreDetailsReport;
import com.example.android.meand100_v2.R;
import com.example.android.meand100_v2.reports.types.Call;
import com.example.android.meand100_v2.reports.types.PermissionUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;


import java.util.ArrayList;
import java.util.List;


public class GeneralReport extends AppCompatActivity
        implements
        ActivityCompat.OnRequestPermissionsResultCallback {
    private Bundle extras;
    LocationManager mLocationManager;
    String selection1, selection2;
    Location location;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;
    private GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_report);
        //TODO: send to the police at this point
        extras = getIntent().getExtras();
        //client = new TcpClient();
        //client.connect(getApplicationContext(), "127.0.0.1", 1500);
        //client.send(" INITIALREPORT " + extras.getString("reportType"));
        addQuestions();
        defineEmergancyDialerListener();
        setSendButtonAction();
        setCancelationDialog();
        enableMyLocation();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {
        double lat = 0;
        double lon = 0;
        try {
            lat = location.getLatitude();
            lon = location.getLongitude();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //double lat=32.085300;
        //double lon=34.781768;
        GeneralStatics.sendLocation(getApplicationContext(), "lat: " + lat + " lon: " + lon);
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GeneralReport Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.meand100_v2.reports/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
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
        try {
            RadioGroup group1 = (RadioGroup) findViewById(R.id.first_radio_group);
            String radiovalue1 = ((RadioButton) findViewById(group1.getCheckedRadioButtonId())).getText().toString(); //TODO:not good statement
            RadioGroup group2 = (RadioGroup) findViewById(R.id.second_radio_group);
            String radiovalue2 = ((RadioButton) findViewById(group2.getCheckedRadioButtonId())).getText().toString();
            if ((group1.getCheckedRadioButtonId() > -1) && (group2.getCheckedRadioButtonId() > -1)) {
                return true;
            }
            return false;
        } catch (NullPointerException e) { //happens if one or more questions have not been answered
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please fill out all details and than hit 'send'")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
     *
     * @param radioGroup a group to populate
     * @param array      values to populate to
     */
    private void setRadioButtonsFromArrayToRadioGroup(RadioGroup radioGroup, ArrayList<String> array) {
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (i < array.size()) {
                radioButton.setText(array.get(i));
                radioButton.setVisibility(View.VISIBLE);
            } else
                radioButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * a dialog that is shown when a user cancels the reportndroi
     */
    private void setCancelationDialog() {
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(GeneralReport.this).create(); //Read Update
                alertDialog.setTitle("Cancellation");
                alertDialog.setMessage("dont do that again!");


                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //TODO: send a cancellation message to the police
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);//go back to main page
                        startActivity(intent);
                    }
                });
                alertDialog.show();
                GeneralStatics.sendReportCancelation(getApplicationContext());
            }
        });
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
        Button btn = (Button) findViewById(R.id.call_100_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = Call.callEmergancyNumber();
                startActivity(callIntent);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GeneralReport Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.meand100_v2.reports/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
