package com.example.trabahopeapp.ViewAll;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trabahopeapp.ActivityDisplacedWorker.SavedJobs;
import com.example.trabahopeapp.R;
import com.example.trabahopeapp.User.AvailableJobs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JobDetailsSaved extends AppCompatActivity {
    TextView jobdTitle, jobdSalary, jobDCompanyName, jobDqualifications, jobDresponsibilities, niceToHave, perksBenefits;
    ImageView back_dashboard;
    String jobid, companyID, userID;
    Button applyButton, savejobBtn;
    private String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/job_application_mobile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details_saved);


        back_dashboard = findViewById(R.id.back_dashboard);

        back_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobDetailsSaved.this, SavedJobs.class);
                startActivity(intent);
                finish();

            }
        });

        Intent intent = getIntent();

        jobdTitle = (TextView) findViewById(R.id.jobDTitle);
        jobdSalary = (TextView) findViewById(R.id.Dsalary);
        jobDCompanyName = (TextView) findViewById(R.id.companyDName);
        jobDqualifications = (TextView) findViewById(R.id.qualifactions);
        jobDresponsibilities = (TextView) findViewById(R.id.responsibilities);
        niceToHave = (TextView) findViewById(R.id.nicetoHave);
        perksBenefits = (TextView) findViewById(R.id.perksandBenefits);
//        jobid = (TextView) findViewById(R.id.jobID);
//        companyID = (TextView) findViewById(R.id.companyID);
//        userID = (TextView) findViewById(R.id.userID);



        jobdTitle.setText(intent.getStringExtra("jobtitle"));
        jobDCompanyName.setText(intent.getStringExtra("companyname"));
        jobdSalary.setText("P" + intent.getStringExtra("salary") + ".00");
        jobDqualifications.setText(intent.getStringExtra("qualifications"));
        jobDresponsibilities.setText(intent.getStringExtra("responsibilities"));
        niceToHave.setText(intent.getStringExtra("nicetohave"));
        perksBenefits.setText(intent.getStringExtra("perksbenefits"));

//        jobid.setText(intent.getStringExtra("jobID"));
//        companyID.setText(intent.getStringExtra("companyID"));
//        userID.setText(intent.getStringExtra("sessionID"));


        jobid = intent.getStringExtra("jobID");
        userID = intent.getStringExtra("sessionID");
        companyID = intent.getStringExtra("companyID");





        applyButton = (Button) findViewById(R.id.applyButton);
        savejobBtn = (Button) findViewById(R.id.saveButton);
        savejobBtn.setAlpha(.5f);
        savejobBtn.setClickable(false);


        //volley
        //button - post padung sa php

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(JobDetailsSaved.this);


                builder.setMessage("Are you sure you want to apply to this job?")
                        .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                insertApplicationData(jobid, companyID, userID);


                            }
                        }).setNegativeButton("Cancel", null);

                        AlertDialog alert = builder.create();
                        alert.show();




            }
        });

        savejobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


    }

    private void insertApplicationData(final String jobid, final String companyID, final String userID) {




        System.out.println(jobid);
        System.out.println(companyID);
        System.out.println(userID);


        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {


                    JSONObject jsonobject = new JSONObject(response);
                    if(jsonobject.getString("status").equals("error")){

                        Toast.makeText(JobDetailsSaved.this, jsonobject.getString("message"), Toast.LENGTH_SHORT).show();

                    }
                    else {

                        Toast.makeText(JobDetailsSaved.this, jsonobject.getString("message"), Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();
                param.put("jobid", jobid);
                param.put("companyid", companyID);
                param.put("userid", userID);
                param.put("function", "job_apply_save");
                return param;
            }
        };



        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);




    }








}