package com.example.trabahopeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Registration4Activity extends AppCompatActivity {
        ImageView backTostep3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration4);

        backTostep3 = findViewById(R.id.backTostep3);

        backTostep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration4Activity.this,Registration3Activity.class);
                startActivity(intent);
                finish();
                //  Pair[] pairs = new Pair[6];
                //   pairs[0] = new Pair<View,String>(logo,"logo_anim");
                //  pairs[1] = new Pair<View,String>(logoname,"logoname_anim");
                //  pairs[2] = new Pair<View,String>(signText,"signup_anim");
                //  pairs[3] = new Pair<View,String>(password,"password_anim");
                //  pairs[4] = new Pair<View,String>(signup,"logoin_anim");
                //  pairs[5] = new Pair<View,String>(backlogin,"already_anim");

                //   ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegistrationActivity.this,pairs);
                // startActivity(intent,options.toBundle());
            }
        });
        
    }
}