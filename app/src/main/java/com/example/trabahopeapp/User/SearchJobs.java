package com.example.trabahopeapp.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trabahopeapp.DashboardActivity;
import com.example.trabahopeapp.LoginActivity;
import com.example.trabahopeapp.R;
import com.example.trabahopeapp.RegistrationActivity;
import com.example.trabahopeapp.ViewAll.JobDetails;
import com.example.trabahopeapp.ViewAll.Jobs;
import com.example.trabahopeapp.ViewAll.JobsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchJobs extends Fragment implements AdapterView.OnItemClickListener {

    ListView lv;
    ArrayList<Jobs> list = new ArrayList<Jobs>();
    JobsAdapter adapter;
    AlertDialog.Builder builder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_jobs_fragment, container, false);


        this.lv = view.findViewById(R.id.listView);
        adapter = new JobsAdapter(getActivity(), list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);




        String URL_REGIST = "http://192.168.0.102/trabahope_v1/server/controller/jobs_mobile.php";

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());


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
                        String jobCategory = jobs.getString("jobcategory");
                        String jobid = jobs.getString("id");
                        String companyDetails = jobs.getString("jobdetails");
                        JSONObject details = new JSONObject(companyDetails);
                        String jobPostDate = jobs.getString("created");




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
                                jobCategory
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
                data.put("barangayid", preferences.getString("sessionBrgyID", null));
                data.put("function", "get_user_jobs");
                return data;

            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue1.add(stringRequest1);




        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), JobDetails.class);
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

