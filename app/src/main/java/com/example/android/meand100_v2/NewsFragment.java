package com.example.android.meand100_v2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.meand100_v2.news.FullStoryActivity;
import com.example.android.meand100_v2.news.HeaderStory;
import com.example.android.meand100_v2.news.MySimpleArrayAdapter;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class NewsFragment extends Fragment {

    ArrayList<HeaderStory> storiesArray;
    ViewGroup root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_news, container, false);
        populateListViewWithStories();
        return root;
    }

    private void populateListViewWithStories() {
        final ArrayList<HeaderStory> stories = getStoriesFromServer();
        final ListView listview = (ListView) root.findViewById(R.id.stories_listview);
        final MySimpleArrayAdapter adapter =new MySimpleArrayAdapter(getActivity().getApplicationContext(), stories);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FullStoryActivity.class);
                intent.putExtra("storyHeader", stories.get(position).getHeader());
                intent.putExtra("storyDate", stories.get(position).getDate());
                intent.putExtra("storyTime", stories.get(position).getTime());
                intent.putExtra("storyLocation", stories.get(position).getLocation());
                intent.putExtra("storyID", stories.get(position).getId());
                startActivity(intent);
            }

        });
    }

    private ArrayList<HeaderStory> getStoriesFromServer() {
        storiesArray = new ArrayList<HeaderStory>();
        storiesArray.add(new HeaderStory ("1", "המחבל שביצע את פיגוע הירי בתלפיות נתפס ונוטרל. תודה לאזרחים שסייעו בכך.", new Location("Jerusalem"), new GregorianCalendar(2016 , 2 , 14 , 10 , 39)));
        storiesArray.add(new HeaderStory ("2", "המשטרה מבקשת את עזרת הציבור בחיפוש אחר הנעדר בסיכון- עדי ליברמן", new Location(""), new GregorianCalendar(2016 , 2 , 14 ,9  , 30)));
        storiesArray.add(new HeaderStory ("3", "מחר צפוי להיחסם כביש מס' 1 לצורך עבודות תחזוקה החל משעה 22:00 ועד ל5:00 בבוקר ביום שלמחרת", new Location("Jerusalem"), new GregorianCalendar(2016 , 2 , 14 , 9 , 0)));
        storiesArray.add(new HeaderStory ("4", "היום בשעה 12:00 תשמע אזעקת תרגול באזור ירושלים. ", new Location("Jerusalem"), new GregorianCalendar(2016 , 2 , 14 , 8 , 0)));
        storiesArray.add(new HeaderStory ("1", "לרגל יום האישה הבינלאומי אנו רוצים להציג בפניכם שלוש שוטרות המשרתות בתפקידים שונים ומעניינים במשטרת ישראל.", new Location(""), new GregorianCalendar(2016 , 2 , 13 , 20 , 0)));
        /*
        storiesArray.add(new HeaderStory ("2", "Robbery near Electra building", new Location(""), new GregorianCalendar(2016 , 3 , 14 , 9 , 18)));
        storiesArray.add(new HeaderStory ("3", "The increased police activity continues", new Location(""), new GregorianCalendar(2016 , 3 , 14 , 9 , 18)));
        storiesArray.add(new HeaderStory ("4", "250 illegal aliens were arrested", new Location(""), new GregorianCalendar(2016 , 3 , 14 , 9 , 18)));
        storiesArray.add(new HeaderStory ("1", "Breaking News: Shooting in Talpiot", new Location(""), new GregorianCalendar(2016 , 3 , 14 , 9 , 18)));
        storiesArray.add(new HeaderStory ("2", "Robbery near Electra building", new Location(""), new GregorianCalendar(2016 , 3 , 14 , 9 , 18)));
        storiesArray.add(new HeaderStory ("3", "The increased police activity continues", new Location(""), new GregorianCalendar(2016 , 3 , 14 , 9 , 18)));
        storiesArray.add(new HeaderStory ("4", "250 illegal aliens were arrested", new Location(""), new GregorianCalendar(2016 , 3 , 14 , 9 , 18)));
        */
        return storiesArray;
    }

}