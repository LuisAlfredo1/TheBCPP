package com.example.luis.thebcp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText mEditTextEmail;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private ProgressDialog loadingBar;
    private Button mButtonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        mEditTextEmail = (EditText) findViewById(R.id.et_sign_up_email);
        mEditTextUsername = (EditText) findViewById(R.id.et_sign_up_username);
        mEditTextPassword = (EditText) findViewById(R.id.et_sign_up_password);
        mButtonSignUp = (Button) findViewById(R.id.bt_sign_up);
        loadingBar = new ProgressDialog(this);

        mButtonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bt_sign_up:

                String email = mEditTextEmail.getText().toString();
                String username = mEditTextUsername.getText().toString();
                String password = mEditTextPassword.getText().toString();

                RegisterAccount(email, username, password);

                break;

        }

    }

    private void RegisterAccount(String email, String username, String password) {

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please write your name", Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email", Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password", Toast.LENGTH_LONG).show();
        }
        else{
            loadingBar.setTitle("Creating new Account");
            loadingBar.setMessage("Please wait, while we are creating account for you");
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Intent mainIntent = new Intent(SignUp.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error occured, please try again", Toast.LENGTH_SHORT).show();
                    }
                    loadingBar.dismiss();
                }
            });
        }

    }
}
