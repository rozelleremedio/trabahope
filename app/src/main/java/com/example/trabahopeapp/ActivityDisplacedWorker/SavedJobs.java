package com.example.trabahopeapp.ActivityDisplacedWorker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trabahopeapp.DashboardActivity;
import com.example.trabahopeapp.R;
import com.example.trabahopeapp.ViewAll.JobDetails;
import com.example.trabahopeapp.ViewAll.JobDetailsSaved;
import com.example.trabahopeapp.ViewAll.Jobs;
import com.example.trabahopeapp.ViewAll.JobsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SavedJobs extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ImageView back_dashboard;
    ListView lv;
    EditText jobFilter;
    ArrayList<Jobs> list = new ArrayList<Jobs>();
    final String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/jobs_mobile.php";



    JobsAdapter adapter;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jobs);
        back_dashboard = findViewById(R.id.back_dashboard);

        this.lv = findViewById(R.id.listView);
        adapter = new JobsAdapter(this, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);





        String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/jobs_mobile.php";

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SavedJobs.this);

        back_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SavedJobs.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });



        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>(){



            @Override
            public void onResponse(String response1) {

                System.out.println(response1);

                try {


                    JSONObject json = new JSONObject(response1);
                    JSONArray array = json.getJSONArray("Jobs");
                    for(int i=0;i<array.length();i++){

                        JSONObject jobs = array.getJSONObject(i);

                        String companyName = jobs.getString("firstname");
                        String companyID = jobs.getString("companyid");
                        String jobid = jobs.getString("id");
                        String companyDetails = jobs.getString("jobdetails");
                        String jobPostDate = jobs.getString("created");
                        JSONObject details = new JSONObject(companyDetails);

                        String userids = preferences.getString("sessionID", null);
                        System.out.println(userids);


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
                                jobPostDate,null

                        );


                        list.add(j);
                        adapter.notifyDataSetChanged();
                    }


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
                data.put("userid", preferences.getString("sessionID", null));
                data.put("jobstatus", "Save");
                data.put("function", "get_user_jobs_by_status");
                return data;

            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);




    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, JobDetailsSaved.class);
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
}