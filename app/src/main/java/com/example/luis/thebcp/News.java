package com.example.luis.thebcp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class News extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView listView;

    String[] NEWS_NUMBER = {"1", "2", "3", "4", "5"};

    String[] NEWS = {
            "Notica 1", "Noticia 2", "Noticia 3", "Noticia 4", "Noticia 5",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_news);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("News");

        listView = (ListView) findViewById(R.id.lv_news);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return NEWS_NUMBER.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.row_news, null);
            TextView textView_name = (TextView) view.findViewById(R.id.tv_row_news_number);
            TextView textView_description = (TextView) view.findViewById(R.id.tv_row_news_news_title);

            textView_name.setText(NEWS_NUMBER[position]);
            textView_description.setText(NEWS[position]);

            return view;
        }
    }
}
