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

public class RegistrationActivity extends AppCompatActivity {

    Button backlogin,signup;
    ImageView logo, logoname;
    TextView signText;
    TextInputLayout username, password, lastname, firstname, email, phone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        backlogin = findViewById(R.id.back_login);
        signup = findViewById(R.id.signup);
        logo = findViewById(R.id.logoImage);
        logoname = findViewById(R.id.logoName);
        signText = findViewById(R.id.signup_text);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        lastname = findViewById(R.id.last_name);
        firstname = findViewById(R.id.first_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(logo,"logo_anim");
                pairs[1] = new Pair<View,String>(logoname,"logoname_anim");
                pairs[2] = new Pair<View,String>(signText,"signup_anim");
                pairs[3] = new Pair<View,String>(username,"username_anim");
                pairs[4] = new Pair<View,String>(password,"password_anim");
                pairs[5] = new Pair<View,String>(signup,"logoin_anim");
                pairs[6] = new Pair<View,String>(backlogin,"already_anim");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegistrationActivity.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }


    //Validation
    private Boolean validateLastname(){
        String forLastname = lastname.getEditText().getText().toString();

        if(forLastname.isEmpty()){
            lastname.setError("Field cannot be empty");
            return false;
        }else{
            lastname.setError(null);
            lastname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateFirstname(){
        String forFirstname = firstname.getEditText().getText().toString();

        if(forFirstname.isEmpty()){
            firstname.setError("Field cannot be empty");
            return false;
        }else{
            firstname.setError(null);
            firstname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUsername(){
        String forUsername = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(forUsername.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }else if(forUsername.length()>=15){
            username.setError("Username too long");
            return false;
        }else if(!forUsername.matches(noWhiteSpace)){
            username.setError("White spaces not allowed");
            return false;
        }else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(){
        String forEmail = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\+[a-z]+";

        if(forEmail.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }else if(!forEmail.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhone(){
        String forPhone = phone.getEditText().getText().toString();

        if(forPhone.isEmpty()){
            phone.setError("Field cannot be empty");
            return false;
        }else{
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String forPassword = password.getEditText().getText().toString();
        String passwordVal = "^" +
                             "(?=.*[0-9])" +
                             "(?=.*[a-z])" +
                             "(?=.*[A-Z])" +
                             "(?=.*[a-zA-Z])" +
                             "(?=.*[@#$%^&+=])" +
                             "(?=\\S+$)" +
                             ".{4,}" +
                             "$";

        if(forPassword.isEmpty()){
           password.setError("Field cannot be empty");
            return false;
        }else if(!forPassword.matches(passwordVal)){
            password.setError("Password is too weak");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    //Save
    public void registerUser (View  view){

        if(!validateLastname() | !validateFirstname() |!validateUsername() | !validateEmail() | !validatePhone() | !validatePassword()){
            return;
        }
        String forLastname = lastname.getEditText().getText().toString();
        String forFirstname = firstname.getEditText().getText().toString();
        String forUsername = username.getEditText().getText().toString();
        String forEmail = email.getEditText().getText().toString();
        String forPhone = phone.getEditText().getText().toString();
        String forPassword = password.getEditText().getText().toString();
    }
}