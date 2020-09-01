package com.example.menmarket.ui.fragment;

import androidx.fragment.app.Fragment;

import com.example.menmarket.ui.acivity.BaseActivity;

public class BaseFragment extends Fragment {
    public BaseActivity baseActivity;
    public void inif (){
        baseActivity=(BaseActivity) getActivity();
        baseActivity.baseFragment=this;
    }

    public void onBack(){
    }


}
