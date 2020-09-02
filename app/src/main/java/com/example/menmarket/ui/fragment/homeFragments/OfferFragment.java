package com.example.menmarket.ui.fragment.homeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.menmarket.R;
import com.example.menmarket.ui.fragment.BaseFragment;

public class OfferFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inif();
        View v=inflater.inflate(R.layout.fragment_offer,container,false);
        return v;
    }
}
