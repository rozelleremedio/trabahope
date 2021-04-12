package com.example.trabahopeapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trabahopeapp.Category;
import com.example.trabahopeapp.DashboardActivity;
import com.example.trabahopeapp.ExampleDialog;
import com.example.trabahopeapp.MyCategory;
import com.example.trabahopeapp.R;
import com.example.trabahopeapp.ViewAll.JobDetails;
import com.example.trabahopeapp.ViewAll.Jobs;
import com.example.trabahopeapp.ViewAll.JobsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AvailableJobs extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, ExampleDialog.ExampleDialogListener {

    ImageView back_dashboard;
    ListView lv;
    ArrayList<Jobs> list = new ArrayList<Jobs>();
    TextView dataChecker;
    EditText searchTxt;
    TextView searchFilter;
    List<String> listfilter;
    ImageView close_dialog;
    CheckBox chPart, chFull, chContract, chEntry, chExperienced, chAdvanced, chManager, chTopManager;
    String shPart, sFull, sContract, sEntry, sExperienced, sAdvanced, sManager, sTopManager;
    Button applyFilter;
    String my_search_keyword = "2";
    AlertDialog dialog;
    String jobtype = "";
    String joblevel = "";
    private boolean mCountIncreased;




    JobsAdapter adapter;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall_jobs);


        back_dashboard = findViewById(R.id.back_dashboard);
//        jobFilter = (EditText) findViewById(R.id.jobSearch);

        this.lv = findViewById(R.id.listView);
        adapter = new JobsAdapter(this, list);
        lv.setAdapter(adapter);
        searchFilter = (TextView) findViewById(R.id.searchFiltericon);
        searchTxt = (EditText) findViewById(R.id.searchTxt);
        lv.setTextFilterEnabled(true);



        final ArrayList<Jobs> findlist = new ArrayList<Jobs>();


        searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                adapter.getFilter().filter(s);




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        searchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCustomDialog();


            }
        });


        lv.setOnItemClickListener(this);


        back_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvailableJobs.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });




//        this.my_search_keyword = barangayid;

        String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/jobs_mobile.php";

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);


        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {


            @Override
            public void onResponse(String response1) {

                System.out.println(response1);

                try {

                    JSONObject json = new JSONObject(response1);


                    final JSONArray array = json.getJSONArray("Jobs");


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject jobs = array.getJSONObject(i);

                        String companyName = jobs.getString("firstname");
                        String companyID = jobs.getString("companyid");
                        String jobid = jobs.getString("id");
                        String companyDetails = jobs.getString("jobdetails");
                        String jobPostDate = jobs.getString("created");
                        JSONObject details = new JSONObject(companyDetails);
                        String jc = details.getString("jobcategory");

                        String barangayid = preferences.getString("sessionBrgyID", null);


                        Jobs j = new Jobs(
                                details.getString("jobtitle"), companyName,
                                details.getString("jobtype"),
                                details.getString("salary"),
                                details.getString("qualifications"),
                                details.getString("responsibilities"),
                                details.getString("nicetohave"),
                                details.getString("perksbenefits"),
                                details.getString("barangay"),
                                jobid,
                                companyID,
                                preferences.getString("sessionID", null),
                                jobPostDate,
                                details.getString("jobcategory")
                        );


                        list.add(j);
                        adapter.notifyDataSetChanged();
                    }

                    if (adapter.getCount() == 0) {
                        Toast.makeText(AvailableJobs.this, "No Jobs Available", Toast.LENGTH_SHORT).show();
                        dataChecker.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(AvailableJobs.this, "Jobs Available", Toast.LENGTH_SHORT).show();
                    }


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




                Intent intent = getIntent();
                String jobtypedata = intent.getStringExtra("jobtype");
                String joblevel = intent.getStringExtra("joblevel");



                if(joblevel == null){

                    joblevel = "10";

                }
                if(jobtypedata == null){

                    jobtypedata = "10";

                }




                System.out.println(jobtypedata);
                System.out.println(joblevel);





                Map<String, String> data = new HashMap<>();
                    data.put("barangayid", preferences.getString("sessionBrgyID", null));
//                data.put("jobcategory", categoryjobsno);
                    data.put("jobtype", jobtypedata);
                    data.put("joblevel", joblevel);
                    data.put("function", "get_user_jobs");
                    System.out.println(jobtype);
                    return data;





            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);




}


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, JobDetails.class);
        intent.putExtra("jobtitle", list.get(position).getJobTitle());
        intent.putExtra("companyname", list.get(position).getCompanyName());
        intent.putExtra("salary", list.get(position).getSalary());
        intent.putExtra("qualifications", list.get(position).getQualifications());
        intent.putExtra("responsibilities", list.get(position).getJobDresponsibilities());
        intent.putExtra("nicetohave", list.get(position).getNiceToHave());
        intent.putExtra("perksbenefits", list.get(position).getPerksBenefits());
        intent.putExtra("jobID", list.get(position).getJobid());
        intent.putExtra("companyID", list.get(position).getCompanyid());
        intent.putExtra("sessionID", list.get(position).getUserid());
        startActivity(intent);

    }

    private void showCustomDialog() {

//        chEntry
//        chExperienced
//        chAdvanced
//        chManager
//        chTopManager;

        //Now we need an AlertDialog.Builder object

        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");

//        applyFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                StringBuffer result = new StringBuffer();
//
//                result.append("JoB Type");
//                if(chPart.isChecked()){
//                    jobtype = "2";
//                    result.append(chPart.getText().toString());
//                }
//                if(chFull.isChecked()){
//                    jobtype = "1";
//                    result.append(chFull.getText().toString());
//                }
//                if(chContract.isChecked()){
//                    jobtype = "3";
//                    result.append(chContract.getText().toString());
//                }
//
//                Toast.makeText(AvailableJobs.this, jobtype, Toast.LENGTH_SHORT).show();
//
//            dialog.dismiss();
//            }
//        });
//
//
//        builder.setView(customLayout);
//
//
//        dialog = builder.create();
//        dialog.show();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void applyText(String passjobtype) {

    }


//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//
//        if(buttonView.getId() == R.id.checkPartTime){
//
//            if(isChecked==true) {
//
//                jobtype = "2";
//                Toast.makeText(this, "Part Time", Toast.LENGTH_SHORT).show();
//
//            }
//
//        }
//
//        if(buttonView.getId() == R.id.checkFullTime){
//
//            if(isChecked==true) {
//                jobtype = "1";
//
//
//                Toast.makeText(this, "Full Time", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//        if(buttonView.getId() == R.id.checkContract){
//
//            if(isChecked==true) {
//
//                jobtype = "3";
//
//                Toast.makeText(this, "Contract", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//        if(buttonView.getId() == R.id.checkPartTime || buttonView.getId() == R.id.checkFullTime){
//
//            if(isChecked==true) {
//
//                Toast.makeText(this, "Full Time and Part Time", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//        if(buttonView.getId() == R.id.checkPartTime || buttonView.getId() == R.id.checkFullTime){
//
//            if(isChecked==true) {
//
//                Toast.makeText(this, "Full Time and Part Time", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//        System.out.println(jobtype);
//
//    }
}
