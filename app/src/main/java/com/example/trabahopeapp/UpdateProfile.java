package com.example.trabahopeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trabahopeapp.ActivityDisplacedWorker.SavedJobs;
import com.example.trabahopeapp.User.AvailableJobs;
import com.example.trabahopeapp.ViewAll.JobDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    ImageView back_dashboard;
    JSONObject jobs, details;
    EditText editContactNo;
    String myJSON, ss, message;

    Button saveButton;
    TextView fName, lName, prevPosition, prevCompany, displacedDate, companyYears, age,  sex, civilStatus, location, barangay, eMail, contactNo, displaceType;

    String sfName, slName, sPrevPosition, sPrevCompany, sDisplacedDate, sCompanyYears, sAge,  sSex, sCivilStatus, sLocation, sBarangay, sEmail, sUserID, companyDetails, sDisplaceType;
    String sContactNo = "321321321";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        age = (TextView) findViewById(R.id.viewAge);
        sex = (TextView) findViewById(R.id.viewSex);
        civilStatus = (TextView) findViewById(R.id.viewCivilStatus);
        location = (TextView) findViewById(R.id.viewLocation);
        barangay = (TextView) findViewById(R.id.viewBarangay);
        eMail = (TextView) findViewById(R.id.viewEmail);
        editContactNo = (EditText) findViewById(R.id.editContact);
        saveButton = (Button) findViewById(R.id.saveButton);


        saveButton.setOnClickListener(this);

        back_dashboard = findViewById(R.id.back_dashboard);

        back_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateProfile.this, DisplacedWorkerProfile.class);
                startActivity(intent);
                finish();

            }
        });

        Intent intent = getIntent();

        sAge = intent.getStringExtra("age");

        age.setText(intent.getStringExtra("age"));
        sex.setText(intent.getStringExtra("sex"));

        System.out.println(sAge);



//        String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/profile_mobile.php";
//
//        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//
//        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
//
//
//
//            @Override
//            public void onResponse(String response1) {
//
//
//                try {
//
//                    JSONObject json = new JSONObject(response1);
//                    JSONArray array = json.getJSONArray("Employee");
//
//                    for(int i=0;i<array.length();i++){
//
//                        jobs = array.getJSONObject(i);
//                        companyDetails = jobs.getString("details");
//                        details = new JSONObject(companyDetails);
//
//
//
//                        sAge = details.getString("birthday");
//                        sSex = details.getString("gender");
//                        sCivilStatus = details.getString("civilstatus");
//                        sLocation = details.getString("completeaddress");
//                        sBarangay = details.getString("barangay");
//                        sEmail = details.getString("email");
//
//                        sDisplaceType = details.getString("displacementtype");
//                        details.put("birthday", "July 32");
//                        details.put("gender", "Female");
//                        details.put("civilstatus", "Single");
//
//
//
//                    }
//
//                    details.put("contactnumber", editContactNo.getText().toString());
//
//
//                    switch (sBarangay) {
//
//                        case "1":
//                            barangay.setText("Tisa");
//                            break;
//                        case "2":
//                            barangay.setText("Guadalupe");
//                            break;
//                        case "3":
//                            barangay.setText("Hipodromo");
//                            break;
//                        case "4":
//                            barangay.setText("Punta Princesa");
//                            break;
//                        case "5":
//                            barangay.setText("Zapatera");
//                            break;
//
//                    }
//
//
//                    age.setText(sAge);
//                    sex.setText(sSex);
//                    civilStatus.setText(sCivilStatus);
//                    location.setText(sLocation);
//                    eMail.setText(sEmail);
//                    editContactNo.setText(sContactNo);
//
//
//                    sUserID = preferences.getString("sessionID", null);
//
//                    System.out.println(sUserID);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> data = new HashMap<>();
//                data.put("userid", preferences.getString("sessionID", null));
////                data.put("jobcategory", categoryjobsno);
//                data.put("function", "displaced_profile_get");
//                return data;
//
//
//            }
//        };
//        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
//        requestQueue1.add(stringRequest1);
//
//    }


    }
    public void updateUsers(){

        final String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/profile_mobile.php";

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);


        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {



            @Override
            public void onResponse(String response1) {

//                System.out.println(response1);

                try {

                    JSONObject json = new JSONObject(response1);
                    JSONArray array = json.getJSONArray("Employee");

                    for(int i=0;i<array.length();i++){

                        jobs = array.getJSONObject(i);
                        companyDetails = jobs.getString("details");
                        details = new JSONObject(companyDetails);

                        message = json.getString("details");

                        JSONObject object = new JSONObject();

//

                        sAge = details.getString("birthday");
                        sSex = details.getString("gender");
                        sCivilStatus = details.getString("civilstatus");
                        sLocation = details.getString("completeaddress");
                       sBarangay = details.getString("barangay");
//                        sEmail = details.getString("email");
                       sContactNo = editContactNo.getText().toString();

                        System.out.println(sContactNo);
//                        sDisplaceType = details.getString("displacementtype");

                          details.put("contactnumber", sContactNo);

                       System.out.println(details);
                        Toast.makeText(UpdateProfile.this, sContactNo, Toast.LENGTH_SHORT).show();


                    }

                    switch (sBarangay) {

                        case "1":
                            barangay.setText("Tisa");
                            break;
                        case "2":
                            barangay.setText("Guadalupe");
                            break;
                        case "3":
                            barangay.setText("Hipodromo");
                            break;
                        case "4":
                            barangay.setText("Punta Princesa");
                            break;
                        case "5":
                            barangay.setText("Zapatera");
                            break;

                    }




                    civilStatus.setText(sCivilStatus);
                    location.setText(sLocation);
                    eMail.setText(sEmail);
                    editContactNo.setText(sContactNo);


                    sUserID = preferences.getString("sessionID", null);

                    System.out.println(sUserID);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> data = new HashMap<>();
                data.put("userid", preferences.getString("sessionID", null));
//                data.put("details", message);
                data.put("function", "displaced_profile_get");
                return data;


            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);

    }


    @Override
    public void onClick(View v) {

        updateUsers();


        Intent intent = new Intent(this, DisplacedWorkerProfile.class);
        intent.putExtra("contactnumber", sContactNo);
        intent.putExtra("sex", sSex);
        startActivity(intent);

        startActivity(new Intent(getApplicationContext(), DisplacedWorkerProfile.class));
        finish();


    }

 }




