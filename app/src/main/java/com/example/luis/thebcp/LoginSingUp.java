package com.example.luis.thebcp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginSingUp extends AppCompatActivity implements View.OnClickListener{

    private Button mButtonLogin;
    private Button mButtonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        mButtonLogin = (Button) findViewById(R.id.bt_login);
        mButtonSignUp = (Button) findViewById(R.id.bt_sign_up);

        mButtonLogin.setOnClickListener(this);
        mButtonSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_login:
                Intent loginIntent = new Intent(LoginSingUp.this, Login.class);
                startActivity(loginIntent);
                break;
            case R.id.bt_sign_up:
                Intent signUpIntent = new Intent(LoginSingUp.this, SignUp.class);
                startActivity(signUpIntent);
                break;
        }

    }
}
