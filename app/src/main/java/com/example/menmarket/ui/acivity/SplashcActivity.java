package com.example.menmarket.ui.acivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.example.menmarket.R;
import com.example.menmarket.data.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SplashcActivity extends AppCompatActivity {


    ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoImage=findViewById(R.id.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (loadData().equals(""))
                        startActivity(new Intent(getBaseContext(), UserActivity.class));
                    else
                        startActivity(new Intent(getBaseContext(), HomeActivity.class));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 3 * 1000);
    }

    public String loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE);
        return sharedPreferences.getString("userPhone", "");
    }
    @Override
    public void onBackPressed() {

    }
}
