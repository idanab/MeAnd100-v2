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
                intent.putExtra("type" , stories.get(position).getType());
                startActivity(intent);
            }

        });
    }

    private ArrayList<HeaderStory> getStoriesFromServer() {
        storiesArray = new ArrayList<HeaderStory>();
        storiesArray.add(new HeaderStory ("1", "לרגל מרוץ השרון ייחסמו מחר מספר רחובות בעיר", new Location("רמת השרון"), new GregorianCalendar(2016 , 5 , 16 , 11 , 55), " policeNews"));        storiesArray.add(new HeaderStory ("1", "מחר בשעה 10:45 תשמע אזעקה באשדוד כחלק מתרגיל חירום של פיקוד העורף", new Location("אשדוד"), new GregorianCalendar(2016 , 2 , 14 , 10 , 39), "alarm"));
        storiesArray.add(new HeaderStory ("1", "חפץ חשוד אותר בתחנה המרכזית בבאר-שבע, המשטרה מבקשת להימנע מלהגיע לאזור", new Location("באר שבע"), new GregorianCalendar(2016 , 5 , 16 , 10 , 39), " emergencies"));
        storiesArray.add(new HeaderStory ("2", "המשטרה מבקשת את עזרת הציבור בחיפוש אחר הנעדר בסיכון- עדי ליברמן", new Location("ירושלים"), new GregorianCalendar(2016 , 5 , 16 ,9  , 30), ""));
        storiesArray.add(new HeaderStory ("2"," המשטרה מבקשת את עזרת הציבור בחיפושיה אחר שלמה מהצרי –מבוקש החשוד במעשה הונאה" ,new Location("כלל ארצי "), new GregorianCalendar(2016 , 5 , 15 ,9  , 30), "missingPerson"));        storiesArray.add(new HeaderStory ("3", "החשש הביטחוני במתחם שרונה הסתיים, ניתן לחזור לשגרת חיים", new Location("תל אביב"), new GregorianCalendar(2016 , 2 , 14 , 9 , 0 ), "emergencies"));
        storiesArray.add(new HeaderStory ("4", "המפכ\"ל ומשטרת ישראל שולחים ברכה לכלל קציני ושוטרי משטרת ישראל המוסלמים החוגגים את הרמדאן", new Location("אשדוד"), new GregorianCalendar(2016 , 5 , 16 , 8 , 0),"policeNews"));

        return storiesArray;
    }

}