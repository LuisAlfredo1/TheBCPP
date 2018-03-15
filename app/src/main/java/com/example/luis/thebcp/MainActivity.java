package com.example.luis.thebcp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextViewTitle;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private Button mButtonNews;
    private Button mButtonPrices;
    private Button mButtonPredictions;
    private Button mButtonLearnAbout;
    private Button mButtonComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("The BCP");

        mAuth = FirebaseAuth.getInstance();

        mTextViewTitle = (TextView) findViewById(R.id.tv_title_main_activity);
        mButtonNews = (Button) findViewById(R.id.bt_main_news);
        mButtonPrices = (Button) findViewById(R.id.bt_main_prices);
        mButtonPredictions = (Button) findViewById(R.id.bt_main_predictions);
        mButtonLearnAbout = (Button) findViewById(R.id.bt_main_learn_about);
        mButtonComments = (Button) findViewById(R.id.bt_main_comments);

        mTextViewTitle.setOnClickListener(this);
        mButtonNews.setOnClickListener(this);
        mButtonPrices.setOnClickListener(this);
        mButtonPredictions.setOnClickListener(this);
        mButtonLearnAbout.setOnClickListener(this);
        mButtonComments.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser  = mAuth.getCurrentUser();

        if(currentUser == null){
            logOutUser();
        }

    }

    private void logOutUser(){
        Intent loginSignUpIntent = new Intent(MainActivity.this, LoginSingUp.class);
        loginSignUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginSignUpIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.main_logout_button){
            mAuth.signOut();

            logOutUser();
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bt_main_news:
                FirebaseMessaging.getInstance().subscribeToTopic("test");
                FirebaseInstanceId.getInstance().getToken();
                Intent newsIntent = new Intent(MainActivity.this, News.class);
                startActivity(newsIntent);
                break;
            case R.id.bt_main_prices:
                Intent pricesIntent = new Intent(MainActivity.this, Prices.class);
                startActivity(pricesIntent);
                break;
            case R.id.bt_main_predictions:
                Intent predictionsIntent = new Intent(MainActivity.this, Predictions.class);
                startActivity(predictionsIntent);
                break;
            case R.id.bt_main_learn_about:
                Intent learnAboutIntent = new Intent(MainActivity.this, LearnAbout.class);
                startActivity(learnAboutIntent);
                break;
            case R.id.bt_main_comments:
                Intent commentsIntent = new Intent(MainActivity.this, Comments.class);
                startActivity(commentsIntent);
                break;
            case R.id.tv_title_main_activity:
                FirebaseMessaging.getInstance().subscribeToTopic("test");
                FirebaseInstanceId.getInstance().getToken();
                break;
        }
    }
}
