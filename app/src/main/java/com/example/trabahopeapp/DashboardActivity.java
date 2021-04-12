package com.example.trabahopeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabahopeapp.ActivityDisplacedWorker.AppliedJobs;
import com.example.trabahopeapp.ActivityDisplacedWorker.SavedJobs;
import com.example.trabahopeapp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.trabahopeapp.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.trabahopeapp.User.AvailableJobs;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variable
    static final float END_SCALE = 0.7f;


    RecyclerView featuredRecycler, mostviewedRecycler, availablejobRecycler, programsRecyler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;
    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;
    TextView va_jobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        //hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostviewedRecycler = findViewById(R.id.most_view_recycler);
        availablejobRecycler = findViewById(R.id.available_job_recycler);
        programsRecyler = findViewById(R.id.dole_programs_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        //Menu Hook
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //ViewAll Hook
        va_jobs = findViewById(R.id.va_jobs);

        //
        navigationDrawer();

        //
        featuredRecycler();
        mostviewedRecycler();
        availablejobRecycler();
        programsRecyler();

        va_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, AvailableJobs.class);
                startActivity(intent);
                finish();

            }
        });
    }

    //Navigation Drawer Function
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_jobs:
                startActivity(new Intent(getApplicationContext(), Category.class));
                finish();
                break;
            case R.id.appliedJobs:
                startActivity(new Intent(getApplicationContext(), AppliedJobs.class));
                finish();
                break;
            case R.id.savedJobs:
                startActivity(new Intent(getApplicationContext(), SavedJobs.class));
                finish();
                break;
            case R.id.viewjobs:
                startActivity(new Intent(getApplicationContext(), AvailableJobs.class));
                finish();
                break;
            case R.id.nav_training:
               startActivity(new Intent(getApplicationContext(), CategoryTraining.class));
               finish();
               break;
            case R.id.nav_about:
                startActivity(new Intent(getApplicationContext(), AboutTrabahope.class));
                finish();
                break;
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), DisplacedWorkerProfile.class));
                finish();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Successfully Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;

        }
        return true;
    }

    //Recycler View Function
    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredTrainings = new ArrayList<>();

        featuredTrainings.add(new FeaturedHelperClass(R.drawable.training, "Online Training", "Online learning involves courses offered by postsecondary institutions.."));
        featuredTrainings.add(new FeaturedHelperClass(R.drawable.welding, "Welding Training", "A great way to learn welding for all levels of experience..."));
        featuredTrainings.add(new FeaturedHelperClass(R.drawable.plummer, "Plumbing Training", "With our plumbing courses, learn all about plumbing industry safety practices.."));

        adapter = new FeaturedAdapter(featuredTrainings);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }
    private void mostviewedRecycler() {
        mostviewedRecycler.setHasFixedSize(true);
        mostviewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> mostViewed = new ArrayList<>();

        mostViewed.add(new FeaturedHelperClass(R.drawable.training, "Online Training", "Online learning involves courses offered by postsecondary institutions.."));
        mostViewed.add(new FeaturedHelperClass(R.drawable.welding, "Welding Training", "A great way to learn welding for all levels of experience..."));
        mostViewed.add(new FeaturedHelperClass(R.drawable.plummer, "Plumbing Training", "With our plumbing courses, learn all about plumbing industry safety practices.."));

        adapter = new FeaturedAdapter(mostViewed);
        mostviewedRecycler.setAdapter(adapter);


        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }
    private void availablejobRecycler() {
        availablejobRecycler.setHasFixedSize(true);
        availablejobRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> availableJobs = new ArrayList<>();

        availableJobs.add(new FeaturedHelperClass(R.drawable.webdeveloper, "Web Developer Needed", "Online learning involves courses offered by postsecondary institutions.."));
        availableJobs.add(new FeaturedHelperClass(R.drawable.welding, "Hiring Welder", "A great way to learn welding for all levels of experience..."));
        availableJobs.add(new FeaturedHelperClass(R.drawable.plummer, "Experienced Plumbers", "With our plumbing courses, learn all about plumbing industry safety practices.."));

        adapter = new FeaturedAdapter(availableJobs);
        availablejobRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }
    private void programsRecyler() {
        programsRecyler.setHasFixedSize(true);
        programsRecyler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> availableJobs = new ArrayList<>();

        availableJobs.add(new FeaturedHelperClass(R.drawable.program, "PESO Programs", "The Public Employment Service Office (PESO) is a.."));
        availableJobs.add(new FeaturedHelperClass(R.drawable.tulay, "TULAY", "Tulong Alalay Para Sa Taong May Kap   ansanan"));
        availableJobs.add(new FeaturedHelperClass(R.drawable.tupads, "TUPAD", " Tulong Panghanapbuhay sa Ating Disadvantaged/Displaced Workers"));

        adapter = new FeaturedAdapter(availableJobs);
        programsRecyler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }

}