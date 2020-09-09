package com.example.menmarket.ui.acivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.menmarket.Helper;
import com.example.menmarket.R;
import com.example.menmarket.ui.fragment.homeFragments.CartFragment;
import com.example.menmarket.ui.fragment.homeFragments.HomeFragment;
import com.example.menmarket.ui.fragment.homeFragments.SettingFragment;
import com.example.menmarket.ui.fragment.homeFragments.OrdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends BaseActivity{
    BottomNavigationView bottomNavigation;
    TextView searchTextView;
    ImageView searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigation=findViewById(R.id.bottom_navigation);
        searchTextView=findViewById(R.id.search_tex_view);
        searchButton=findViewById(R.id.search_buton);
        Helper.replaceFragmentS(getSupportFragmentManager(),R.id.home_fragment_viewer,new HomeFragment());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,SearchProductRecyclerViewActivity.class);
                intent.putExtra("searchKey",searchTextView.getText().toString());
                startActivity(intent);
            }
        });



        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Helper.replaceFragmentS(getSupportFragmentManager(),R.id.home_fragment_viewer,new HomeFragment());
                        break;

                    case R.id.setting:
                        Helper.replaceFragmentS(getSupportFragmentManager(),R.id.home_fragment_viewer,new SettingFragment());
                        break;

                    case R.id.cart:
                        Helper.replaceFragmentS(getSupportFragmentManager(),R.id.home_fragment_viewer,new CartFragment());
                        break;

                    case R.id.orders:
                        Helper.replaceFragmentS(getSupportFragmentManager(),R.id.home_fragment_viewer,new OrdersFragment());
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
