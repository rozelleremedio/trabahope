package com.example.trabahopeapp;

import androidx.appcompat.app.AppCompatActivity;



import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button callSigUp, btnLogin;
    ImageView logo, logoname;
    TextView signText;
    TextInputLayout email, password;
    private String semail, spassword;
    private String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/accounts_mobile.php";


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
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        callSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(logo,"logo_anim");
                pairs[1] = new Pair<View,String>(logoname,"logoname_anim");
                pairs[2] = new Pair<View,String>(signText,"signup_anim");
                pairs[3] = new Pair<View,String>(email,"email_anim");
                pairs[4] = new Pair<View,String>(password,"password_anim");
                pairs[5] = new Pair<View,String>(btnLogin,"logoin_anim");
                pairs[6] = new Pair<View,String>(callSigUp,"already_anim");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
                startActivity(intent,options.toBundle());

            }
        });
    }



    public void loginUser(View view){

        semail = email.getEditText().getText().toString();
        spassword = password.getEditText().getText().toString();

        System.out.println(semail);
        System.out.println(spassword);
        if(!semail.equals("") && !spassword.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
//                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
//
//
                    try {
                        JSONObject jsonobject = new JSONObject(response);
                        if(jsonobject.getString("status").equals("error") && jsonobject.getString("routine").equals("registration")){
                            if(jsonobject.getString("routinestatus").equals("1")){
                                //code here for registration 1st step
                            } else if(jsonobject.getString("routinestatus").equals("2")){
                                //code here for registration 2nd step
                            } else if(jsonobject.getString("routinestatus").equals("3")) {
                                //code here for registration 3rd step
                            } else if(jsonobject.getString("routinestatus").equals("4")) {
                                //code here for registration 4th step
                            } else {
                                //Error message here if page not found and go back to login page
                            }
                        } else if (jsonobject.getString("status").equals("error")) {
                            //toast this jsonobject.getString("message")
                            Toast.makeText(LoginActivity.this, jsonobject.getString("message"), Toast.LENGTH_SHORT).show();
                        } else if (jsonobject.getString("status").equals("success")) {
                            Toast.makeText(LoginActivity.this, jsonobject.getString("message"), Toast.LENGTH_SHORT).show();

                            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>(){

                                @Override
                                public void onResponse(String response1) {

                                    try {
                                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                        SharedPreferences.Editor editor = preferences.edit();

                                        JSONObject jsonobject1 = new JSONObject(response1);

                                        editor.putString("sessionID", jsonobject1.getString("userid"));
                                        editor.putString("sessionEmail", jsonobject1.getString("email"));
                                        editor.putString("sessionBrgyID", jsonobject1.getString("id"));

                                        editor.commit();

                                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> data = new HashMap<>();
                                    data.put("email", semail);
                                    data.put("password", spassword);
                                    data.put("function", "user_information");
                                    return data;

                                }
                            };

                            RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                            requestQueue1.add(stringRequest1);



                            //successfull login
                            //you can toast this jsonobject.getString("status") if you want to notify the user if successfully logged in
                            //lets query again to ge the user information using the function "user_information"
                            //then session
                            //then go to user home page
                        } else {
                            //toast an system error message like "There is something wrong while processing your request."
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", semail);
                    data.put("password", spassword);
                    data.put("function", "login");
                    return data;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else {

            Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
        }



    }




//    //Validate
//    private Boolean validateEmail(){
//        String forEmail = email.getEditText().getText().toString();
//        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\+[a-z]+";
//
//        if(forEmail.isEmpty()){
//            email.setError("Field cannot be empty");
//            return false;
//        }else{
//            email.setError(null);
//            email.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private Boolean validatePassword(){
//        String forPassword = password.getEditText().getText().toString();
//
//        if(forPassword.isEmpty()){
//            password.setError("Field cannot be empty");
//            return false;
//        }else{
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    public void loginUser(View view){
//        if(!validateEmail() | !validatePassword()){
//
//
//
//
//            return;
//        }else{
//            isUser();
//        }
//    }
//    private void isUser(){
//        Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
//        startActivity(intent);
//        finish();
//    }
}