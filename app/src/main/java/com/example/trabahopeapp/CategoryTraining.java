package com.example.trabahopeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabahopeapp.User.JobsByCategory;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryTraining extends AppCompatActivity{

     GridView grid;
    ImageView back_dashboard;

    //    ListView lv;
    CategoryGridAdapter adapter;
    String item_clicked;
    EditText txtSearch;
    String jobcategory = "";
    String categoryTitle = "";
    String jobcatno;

    Intent intent;



    MyCategory[] categoryName= {
            new MyCategory(R.drawable.accounting_finance_white, "Accounting / Finance"),
            new MyCategory(R.drawable.admin_office_white, "Admin / Office / Clerical"),
            new MyCategory(R.drawable.agriculture_vet_white, "Agriculture / Veterinary"),
            new MyCategory(R.drawable.airline_airport_white, "Airline / Airport"),
            new MyCategory(R.drawable.arts_media_white, "Arts / Media / Design"),
            new MyCategory(R.drawable.call_center_bpo_white, "Call Center / BPO"),
            new MyCategory(R.drawable.domestic_caretaker_white, "Domestic / Caretaker"),
            new MyCategory(R.drawable.education_school_white, "Education / Schools"),
            new MyCategory(R.drawable.engineering_architecture_white, "Engineering / Architecture"),
            new MyCategory(R.drawable.food_restaurant_white, "Food / Restaurant"),
            new MyCategory(R.drawable.foreign_language_white, "Foreign Language"),
            new MyCategory(R.drawable.government_nonprofit_white, "Government / Non-profit"),
            new MyCategory(R.drawable.health_medical_science_white, "Health / Medical / Science"),
            new MyCategory(R.drawable.hotel_spa_salon_white, "Hotel / Spa / Salon"),
            new MyCategory(R.drawable.recruitment_hr_training_white, "HR / Recruitment / Training"),
            new MyCategory(R.drawable.it_computers_white, "IT / Computers"),
            new MyCategory(R.drawable.legal_documentation_white, "Legal / Documentation"),
            new MyCategory(R.drawable.logistics_warehousing_white, "Logistics / Warehousing"),
            new MyCategory(R.drawable.maritime_seabased_white, "Maritime / Seabased"),
            new MyCategory(R.drawable.production_manufacturing_white, "Production / Manufacturing"),
            new MyCategory(R.drawable.purchasing_buyer_white, "Purchasing / Buyer"),
            new MyCategory(R.drawable.sales_marketing_retail_white, "Sales / Marketing / Retail"),
            new MyCategory(R.drawable.sports_athletics_white, "Sports / Athletics")
    };

    ArrayList<MyCategory> findlist = new ArrayList<MyCategory>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        txtSearch = (EditText) findViewById(R.id.searchTxt);


        back_dashboard = findViewById(R.id.back_dashboard);

        grid = (GridView) findViewById(R.id.datagrid);
        adapter = new CategoryGridAdapter(this, findlist);

        back_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryTraining.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });


        findlist.add(new MyCategory(R.drawable.accounting_finance_white, "Accounting / Finance"));
        findlist.add(new MyCategory(R.drawable.admin_office_white, "Admin / Office / Clerical"));
        findlist.add(new MyCategory(R.drawable.agriculture_vet_white, "Agriculture / Veterinary"));
        findlist.add(new MyCategory(R.drawable.airline_airport_white, "Airline / Airport"));
        findlist.add(new MyCategory(R.drawable.arts_media_white, "Arts / Media / Design"));
        findlist.add(new MyCategory(R.drawable.call_center_bpo_white, "Call Center / BPO"));
        findlist.add(new MyCategory(R.drawable.domestic_caretaker_white, "Domestic / Caretaker"));
        findlist.add(new MyCategory(R.drawable.education_school_white, "Education / Schools"));
        findlist.add(new MyCategory(R.drawable.engineering_architecture_white, "Engineering / Architecture"));
        findlist.add(new MyCategory(R.drawable.food_restaurant_white, "Food / Restaurant"));
        findlist.add(new MyCategory(R.drawable.foreign_language_white, "Foreign Language"));
        findlist.add(new MyCategory(R.drawable.government_nonprofit_white, "Government / Non-profit"));
        findlist.add(new MyCategory(R.drawable.health_medical_science_white, "Health / Medical / Science"));
        findlist.add(new MyCategory(R.drawable.hotel_spa_salon_white, "Hotel / Spa / Salon"));
        findlist.add(new MyCategory(R.drawable.recruitment_hr_training_white, "HR / Recruitment / Training"));
        findlist.add(new MyCategory(R.drawable.it_computers_white, "IT / Computers"));
        findlist.add(new MyCategory(R.drawable.legal_documentation_white, "Legal / Documentation"));
        findlist.add(new MyCategory(R.drawable.logistics_warehousing_white, "Logistics / Warehousing"));
        findlist.add(new MyCategory(R.drawable.maritime_seabased_white, "Maritime / Seabased"));
        findlist.add(new MyCategory(R.drawable.production_manufacturing_white, "Production / Manufacturing"));
        findlist.add(new MyCategory(R.drawable.purchasing_buyer_white, "Purchasing / Buyer"));
        findlist.add(new MyCategory(R.drawable.sales_marketing_retail_white, "Sales / Marketing / Retail"));
        findlist.add(new MyCategory(R.drawable.sports_athletics_white, "Sports / Athletics"));
        grid.setAdapter(adapter);




        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String categs = findlist.get(position).getCatName();

                switch(categs){
                    case "Accounting / Finance":
                        jobcatno = "1";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Admin / Office / Clerical":
                        jobcatno = "2";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Agriculture / Veterinary":
                        jobcatno = "3";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Airline / Airport":
                        jobcatno = "4";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Arts / Media / Design":
                        jobcatno = "5";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Call Center / BPO":
                        jobcatno = "6";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Domestic / Caretaker":
                        jobcatno = "7";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Education / Schools":
                        jobcatno = "8";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Engineering / Architecture":
                        jobcatno = "9";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Food / Restaurant":
                        jobcatno = "10";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Foreign Language":
                        jobcatno = "11";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Government / Non-profit":
                        jobcatno = "12";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Health / Medical / Science":
                        jobcatno = "13";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Hotel / Spa / Salon":
                        jobcatno = "14";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "HR / Recruitment / Training":
                        jobcatno = "15";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "IT / Computers":
                        jobcatno = "16";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Legal / Documentation":
                        jobcatno = "17";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Logistics / Warehousing":
                        jobcatno = "18";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Maritime / Seabased":
                        jobcatno = "19";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Production / Manufacturing":
                        jobcatno = "20";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Purchasing / Buyer":
                        jobcatno = "21";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Sales / Marketing / Retail":
                        jobcatno = "22";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Skilled Work / Technical":
                        jobcatno = "23";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;
                    case "Sports / Athletics":
                        jobcatno = "24";
                        intent = new Intent(CategoryTraining.this, JobsByCategory.class);
                        intent.putExtra("jobcategory", jobcatno);
                        startActivity(intent);
                        break;



                }



            }
        });







        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {





                findlist.clear();

                //using regex - regular expression

                String ss = s.toString();
                Pattern pattern = Pattern.compile(ss);

                for(int i=0;i<categoryName.length;i++){

                    Matcher match = pattern.matcher(categoryName[i].getCatName().toLowerCase());
                    if(match.find()){

                        findlist.add(categoryName[i]);


                    }


                }
                //update the adapter
                adapter.notifyDataSetChanged();

//                adapter.getFilter().filter(s);



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }







}