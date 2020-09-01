package com.example.menmarket.ui.acivity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.menmarket.ui.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {
    public BaseFragment baseFragment;

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }

    public void onBackSuper(){super.onBackPressed();}
}
