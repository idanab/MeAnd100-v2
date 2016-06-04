package com.example.android.meand100_v2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.meand100_v2.reports.types.Call;


public class MoreDetailsReport extends AppCompatActivity {
    private Bitmap photo;
    String radioValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details_report);
        defineEmergancyDialerListener();
        setTakePhotoListener();
        setTakeVideoListener();
        setSendButtonAction();

    }

    private void setSendButtonAction() {
        Button sendButton = (Button) findViewById(R.id.send_button);
        EditText moreInfoTextObject = (EditText) findViewById(R.id.more_info_edittext);
        final String moreInfoText = moreInfoTextObject.getText().toString();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFormFilled()) {
                    //send report
                    GeneralStatics.sendSecondaryReport(getApplicationContext(), getIntent().getExtras().getString("reportType"), radioValue, moreInfoText);
                    //TODO: go back to main reports fragment
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);//go back to main page
                    startActivity(intent);
                    // Commit the transaction
                }
            }
        });
    }

    private boolean isAllFormFilled() {
        try{
            RadioGroup group1 = (RadioGroup) findViewById(R.id.more_first_radio_group);
            radioValue = ((RadioButton)findViewById(group1.getCheckedRadioButtonId())).getText().toString(); //TODO:not good statement
            if(group1.getCheckedRadioButtonId()>-1) {
                return true;
            }
            return false;
        }
        catch (NullPointerException e) {
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

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(MoreDetailsReport.this).create(); //Read Update
        alertDialog.setTitle("ביטול דיווח");
        alertDialog.setMessage("שים לב, מספר ביטולי דיווח עלולים להוביל לחסימתך");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);//go back to main page
                startActivity(intent);
            }
        });
        alertDialog.show();
        GeneralStatics.sendReportCancelation(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more_details_report, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //photo was taken
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            photo = (Bitmap) extras.get("data");
            //TODO: send photo to server
        }
    }

    /**
     * takes a photo from the local camera
     * after the photo is taken, it will receive in "onActivityResult"
     */
    private void setTakePhotoListener() {
        Button photoButton = (Button) findViewById(R.id.add_photo_button);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });
    }

    private void setTakeVideoListener() {
        Button videoButton = (Button) findViewById(R.id.add_video_button);
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, 1);
                }
            }
        });
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
