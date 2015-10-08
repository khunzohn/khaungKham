package com.hilllander.khunzohn.khwangkham;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, ModifideActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3500);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }

    @Override
    public void onBackPressed() {

    }
}
