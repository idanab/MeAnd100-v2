package com.example.android.meand100_v2.news;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.meand100_v2.R;

public class FullStoryActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_story);
        Bundle extras = getIntent().getExtras();
        FetchExtrasInTextBoxes(extras);
    }

    private void FetchExtrasInTextBoxes(Bundle extras) {
        TextView headerTextbox = (TextView) findViewById(R.id.header_text);
        headerTextbox.setText(extras.getString("storyHeader"));
        Log.e("header", extras.getString("storyHeader"));
        TextView dateTextbox = (TextView) findViewById(R.id.date_text);
        dateTextbox.setText(extras.getString("storyDate"));
        Log.e("date", extras.getString("storyDate"));
        /*
        TextView timeTextbox = (TextView) findViewById(R.id.time_text);
        timeTextbox.setText(extras.getString("storyTime"));
        Log.e("time", extras.getString("storyTime"));
        */
        TextView locationText = (TextView) findViewById(R.id.location_text);
        locationText.setText(extras.getString("storyLocation"));
        Log.e("location", extras.getString("storyLocation"));

        //TODO: get full article from server by its "storyID" that you get from the intent
        ImageView imageview = (ImageView) findViewById(R.id.story_photo);
//        TextView summaryTextbox = (TextView) findViewById(R.id.summary_text);

        TextView fullArticleTextbox = (TextView) findViewById(R.id.full_article_text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_story, menu);
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
