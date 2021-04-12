package com.example.trabahopeapp;

import androidx.appcompat.app.AppCompatActivity;



import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Button callSigUp, btnLogin;
    ImageView logo, logoname;
    TextView signText;
    TextInputLayout username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        callSigUp = findViewById(R.id.signup);
        btnLogin = findViewById(R.id.btn_login);
        logo = findViewById(R.id.logoImage);
        logoname = findViewById(R.id.logoName);
        signText = findViewById(R.id.signup_text);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        callSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(logo,"logo_anim");
                pairs[1] = new Pair<View,String>(logoname,"logoname_anim");
                pairs[2] = new Pair<View,String>(signText,"signup_anim");
                pairs[3] = new Pair<View,String>(username,"username_anim");
                pairs[4] = new Pair<View,String>(password,"password_anim");
                pairs[5] = new Pair<View,String>(btnLogin,"logoin_anim");
                pairs[6] = new Pair<View,String>(callSigUp,"already_anim");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
                startActivity(intent,options.toBundle());

            }
        });
    }

    //Validate
    private Boolean validateUsername(){
        String forUsername = username.getEditText().getText().toString();

        if(forUsername.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String forPassword = password.getEditText().getText().toString();

        if(forPassword.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){
        if(!validateUsername() | !validatePassword()){
            return;
        }else{
            isUser();
        }
    }
    private void isUser(){

    }
}