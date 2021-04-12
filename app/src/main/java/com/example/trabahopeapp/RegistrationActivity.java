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

    Button next1;

    ImageView logo, logoname, backlogin;
    TextView signText;
    TextInputLayout  etPassword, etLastname, etFirstname, etEmail, etMiddlename, etConfirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        backlogin = findViewById(R.id.back_main);
        next1 = findViewById(R.id.next1);
        logo = findViewById(R.id.logoImage);
        logoname = findViewById(R.id.logoName);
        signText = findViewById(R.id.signup_text);
        etPassword = findViewById(R.id.password);
        etConfirmpassword = findViewById(R.id.confirm_password);
        etFirstname = findViewById(R.id.first_name);
        etMiddlename = findViewById(R.id.middle_name);
        etLastname = findViewById(R.id.last_name);
        etEmail = findViewById(R.id.email);

        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
              //  Pair[] pairs = new Pair[6];
             //   pairs[0] = new Pair<View,String>(logo,"logo_anim");
              //  pairs[1] = new Pair<View,String>(logoname,"logoname_anim");
              //  pairs[2] = new Pair<View,String>(signText,"signup_anim");
              //  pairs[3] = new Pair<View,String>(password,"password_anim");
              //  pairs[4] = new Pair<View,String>(signup,"logoin_anim");
              //  pairs[5] = new Pair<View,String>(backlogin,"already_anim");

            //   ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegistrationActivity.this,pairs);
             // startActivity(intent,options.toBundle());
            }
        });
    }


    //Validation
    private Boolean validateFirstname(){
        String forFirstname = etFirstname.getEditText().getText().toString();

        if(forFirstname.isEmpty()){
            etFirstname.setError("Field cannot be empty");
            return false;
        }else{
            etFirstname.setError(null);
            etFirstname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateMiddlename(){
        String forMiddlename = etMiddlename.getEditText().getText().toString();

        if(forMiddlename.isEmpty()){
            etMiddlename.setError("Field cannot be empty");
            return false;
        }else{
            etMiddlename.setError(null);
            etMiddlename.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateLastname(){
        String forLastname = etLastname.getEditText().getText().toString();

        if(forLastname.isEmpty()){
            etLastname.setError("Field cannot be empty");
            return false;
        }else{
            etLastname.setError(null);
            etLastname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(){
        String forEmail = etEmail.getEditText().getText().toString();
        String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$\"";

        if(forEmail.isEmpty()){
            etEmail.setError("Field cannot be empty");
            return false;
        }else if(forEmail.matches(emailPattern)){
            etEmail.setError("Invalid email address");
            return false;
        }else{
            etEmail.setError(null);
            etEmail.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String forPassword = etPassword.getEditText().getText().toString();
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
            etPassword.setError("Field cannot be empty");
            return false;
        }else if(!forPassword.matches(passwordVal)){
            etPassword.setError("Password is too weak");
            return false;
        }else{
            etPassword.setError(null);
            etPassword.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateConfirmPassword(){
        String forPassword = etPassword.getEditText().getText().toString();
        String forConfirmPassword = etConfirmpassword.getEditText().getText().toString();

        if(forPassword.isEmpty()){
            etConfirmpassword.setError("Field cannot be empty");
            return false;
        }else if(!forConfirmPassword.matches(forPassword)){
            etConfirmpassword.setError("Password not match");
            return false;
        }else{
            etConfirmpassword.setError(null);
            etConfirmpassword.setErrorEnabled(false);
            return true;
        }
    }



    //Save
    public void btnNext (View  view){

        //if(!validateFirstname() |  !validateMiddlename() | !validateLastname() | !validateEmail() | !validatePassword() | validateConfirmPassword()){
      //      return;
        //}
       // String forFirstname = etFirstname.getEditText().getText().toString();
       // String forMiddlename = etMiddlename.getEditText().getText().toString();
       // String forLastname = etLastname.getEditText().getText().toString();
       // String forEmail = etEmail.getEditText().getText().toString();
       // String forPassword = etPassword.getEditText().getText().toString();
      //y  String forConfirmPassword = etConfirmpassword.getEditText().getText().toString();

        Intent intent = new Intent(RegistrationActivity.this,Registration2Activity.class);
        startActivity(intent);
        finish();

    }
}