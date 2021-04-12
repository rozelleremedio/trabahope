package com.example.trabahopeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
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
import com.example.trabahopeapp.User.AvailableJobs;
import com.example.trabahopeapp.ViewAll.JobDetails;
import com.example.trabahopeapp.ViewAll.Jobs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DisplacedWorkerProfile extends AppCompatActivity implements View.OnClickListener{

    ImageView back_dashboard, editProfile;
    JSONObject details;


    TextView fName, lName, prevPosition, prevCompany, displacedDate, companyYears, age,  sex, civilStatus, location, barangay, eMail, contactNo, displaceType;

    String sfName, slName, sPrevPosition, sPrevCompany, sDisplacedDate, sCompanyYears, sAge,  sSex, sCivilStatus, sLocation, sBarangay, sEmail, sContactNo, sUserID, companyDetails, sDisplaceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaced_worker_profile);


        fName = (TextView) findViewById(R.id.firstName);
        lName = (TextView) findViewById(R.id.lastName);
        prevPosition = (TextView) findViewById(R.id.prevPosition);
        prevCompany = (TextView) findViewById(R.id.prevCompany);
        displacedDate = (TextView) findViewById(R.id.displacedDate);
        companyYears = (TextView) findViewById(R.id.companyYears);
        age = (TextView) findViewById(R.id.age);
        sex = (TextView) findViewById(R.id.sex);
        civilStatus = (TextView) findViewById(R.id.civilStatus);
        location = (TextView) findViewById(R.id.location);
        barangay = (TextView) findViewById(R.id.barangay);
        eMail = (TextView) findViewById(R.id.eMail);
        contactNo = (TextView) findViewById(R.id.contactNo);
        displaceType = (TextView) findViewById(R.id.displacedType);
        editProfile = (ImageView)findViewById(R.id.editProfile);


        back_dashboard = findViewById(R.id.back_dashboard);

        back_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplacedWorkerProfile.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });

        editProfile.setOnClickListener(this);

        String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/profile_mobile.php";

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);


        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {



            @Override
            public void onResponse(String response1) {


                try {

                    JSONObject json = new JSONObject(response1);
                    JSONArray array = json.getJSONArray("Employee");
                    for(int i=0;i<array.length();i++){

                        JSONObject jobs = array.getJSONObject(i);
                        companyDetails = jobs.getString("details");
                        JSONObject details = new JSONObject(companyDetails);



                        sfName = jobs.getString("firstname");
                        slName = jobs.getString("lastname");
                        sPrevPosition = details.getString("companyposition");
                        sPrevCompany = details.getString("company");
                        sDisplacedDate = details.getString("displacementdate");
                        sCompanyYears = details.getString("yearsincompany");
                        sAge = details.getString("birthday");
                                 sSex = details.getString("gender");
                        sCivilStatus = details.getString("civilstatus");
                        sLocation = details.getString("completeaddress");
                        sBarangay = details.getString("barangay");
                        sEmail = details.getString("email");
                        sContactNo = details.getString("contactnumber");
                        sDisplaceType = details.getString("displacementtype");

                        details.remove("gender");
                        details.put("gender", "Female");

//                        System.out.println(details);


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

                    switch (sPrevCompany) {

                        case "1":
                            break;
                        case "2":
                            break;
                        case "3":
                            prevCompany.setText("University of Cebu");
                            break;
                        case "4":
                            break;
                        case "5":
                            break;

                    }


                    Intent intent = getIntent();

                    sContactNo = intent.getStringExtra("contactnumber");

                    System.out.println(sContactNo);

                    fName.setText(sfName);
                    lName.setText(slName);
                     prevPosition.setText(sPrevPosition);
                     displacedDate.setText(sDisplacedDate);
                       companyYears.setText(sCompanyYears);
                    age.setText(sAge);
                     sex.setText(sSex);
                    civilStatus.setText(sCivilStatus);
                    location.setText(sLocation);
                    eMail.setText(sEmail);
                    contactNo.setText(sContactNo);
                    displaceType.setText(sDisplaceType);



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
//                data.put("jobcategory", categoryjobsno);
                data.put("function", "displaced_profile_get");
                return data;


            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);

    }



    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, UpdateProfile.class);
        intent.putExtra("age", sAge);
        intent.putExtra("sex", sSex);
        startActivity(intent);


    }


}