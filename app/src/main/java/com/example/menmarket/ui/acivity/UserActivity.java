package com.example.menmarket.ui.acivity;

import android.os.Bundle;

import com.example.menmarket.Helper;
import com.example.menmarket.R;
import com.example.menmarket.ui.fragment.userFragment.StartFragment;

public class UserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Helper.replaceFragment(getSupportFragmentManager(),R.id.user_fragment_viewer,new StartFragment(),0,0,0,0);
    }
}
