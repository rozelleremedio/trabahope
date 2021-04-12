package com.example.trabahopeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.trabahopeapp.User.AvailableJobs;

public class AboutTrabahope extends AppCompatActivity {

    ImageView back_dashboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_trabahope);

        back_dashboard = findViewById(R.id.back_dashboard);

        back_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutTrabahope.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}