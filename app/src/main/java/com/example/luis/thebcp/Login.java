package com.example.luis.thebcp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private EditText mEditTextEmail;
    private EditText mEditeTextPassword;
    private Button mButtonLogin;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEditTextEmail = (EditText) findViewById(R.id.et_login_email);
        mEditeTextPassword = (EditText) findViewById(R.id.et_login_password);
        mButtonLogin = (Button) findViewById(R.id.bt_login_login);

        loadingBar = new ProgressDialog(getApplicationContext());

        mButtonLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bt_login_login:
                String email = mEditTextEmail.getText().toString();
                String password = mEditeTextPassword.getText().toString();

                loginUserAccount(email, password);
        }
    }

    private void loginUserAccount(String email, String password) {

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();
        }
        else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are verifying your credentials");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent mainIntent = new Intent(Login.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();
                    }
                    else{

                        Toast.makeText(getApplicationContext(), "Please your email or password", Toast.LENGTH_LONG).show();

                    }
                    loadingBar.dismiss();
                }
            });
        }

    }
}
