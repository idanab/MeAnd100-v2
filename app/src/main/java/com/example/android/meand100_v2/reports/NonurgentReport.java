package com.example.android.meand100_v2.reports;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.android.meand100_v2.GeneralStatics;
import com.example.android.meand100_v2.MainActivity;
import com.example.android.meand100_v2.R;
import com.example.android.meand100_v2.reports.types.Call;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class NonurgentReport extends AppCompatActivity {
    private Bundle extras;
    static final int DATE_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID = 888;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            Button dateBtn = (Button) findViewById(R.id.pick_date_button);
            dateBtn.setText(new StringBuilder().append(day)
                    .append("/").append(month + 1).append("/").append(year)
                    .append(" "));

        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // set selected time into textview
            Button timeBtn = (Button) findViewById(R.id.pick_time_button);
            timeBtn.setText(new StringBuilder().append(hourOfDay)
                    .append(":").append(minute));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonurgent_report);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        String minWithZeroString = null;
        String hourWithZeroString = null;
        if(minute<10) {
            StringBuilder minuteWithZero = new StringBuilder().append("0").append(minute);
            minWithZeroString = minuteWithZero.toString();
        }
        else minWithZeroString = String.valueOf(minute);
        Button timeBtn = (Button) findViewById(R.id.pick_time_button);
        Button dateBtn = (Button) findViewById(R.id.pick_date_button);
        timeBtn.setText(new StringBuilder().append(hour)
                .append(":").append(minWithZeroString));
        dateBtn.setText(new StringBuilder().append(day)
                .append("/").append(month + 1).append("/").append(year)
                .append(" "));

        setSendButtonAction();
        Spinner spinner1 = (Spinner) findViewById(R.id.select_report_type_spinner);
        List<String> list = new ArrayList<String>();
        list.add("בחר סוג אירוע");
        list.add("גניבת אופניים");
        list.add("עבירת תנועה");
        list.add("מפגע רעש");
        list.add("הפרת הסדר הציבורי");
        list.add("אבידה");
        list.add("מציאה");
        list.add("התעללות");
        list.add("הטרדה מינית");
        list.add("מסירת מידע מודיעיני");
        list.add("אחר ");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("place autocomplete:", "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("place autocomplete:", "An error occurred: " + status);
            }
        });

        final Button victimBtn = (Button) findViewById(R.id.victim_btn);
        final Button witnessBtn = (Button) findViewById(R.id.witness_btn);
        final int notBoldText = Color.parseColor("#FFACA0A0");
        final int boldText = Color.parseColor("#000000");
        final int notBoldBackground = Color.parseColor("#FFFFFF");
        final int boldBackground = Color.parseColor("#FFC4BFC1");

        witnessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                witnessBtn.setText(R.string.none_urgent_role_witness_bold);
                victimBtn.setText(R.string.none_urgent_role_victim);
                witnessBtn.setTextColor(boldText);
                victimBtn.setTextColor(notBoldText);
                witnessBtn.setBackgroundColor(boldBackground);
                victimBtn.setBackgroundColor(notBoldBackground);

            }
        });
        victimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                witnessBtn.setText(R.string.none_urgent_role_eye_witness);
                victimBtn.setText(R.string.none_urgent_role_victim_bold);
                victimBtn.setTextColor(boldText);
                witnessBtn.setTextColor(notBoldText);
                victimBtn.setBackgroundColor(boldBackground);
                witnessBtn.setBackgroundColor(notBoldBackground);
            }
        });

    }

    public void showDatePickerDialog(View v) {
        showDialog(DATE_DIALOG_ID);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
            case TIME_DIALOG_ID:
                // set date picker as current date
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // set selected time into textview.
                                Log.e("c","reached timeset!");
                                Button timeBtn = (Button) findViewById(R.id.pick_time_button);
                                String minWithZeroString = null;
                                if(minute<10) {
                                    StringBuilder minuteWithZero = new StringBuilder().append("0").append(minute);
                                    minWithZeroString = minuteWithZero.toString();
                                }
                                else minWithZeroString=String.valueOf(minute);
                                timeBtn.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minWithZeroString));
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
        }
        return null;
    }

    public void showTimePickerDialog(View v) {
        showDialog(TIME_DIALOG_ID);


    }

    private void setSendButtonAction() {
        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFormFilled()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);//go back to main page
                    startActivity(intent);
                    GeneralStatics.makeToast(getApplicationContext(), "דיווח נשלח");
                }
            }
        });
    }

    private boolean isAllFormFilled() {
       return true;
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

}
