package com.example.menmarket.ui.fragment.homeFragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.menmarket.R;
import com.example.menmarket.ui.acivity.UserActivity;
import com.example.menmarket.ui.fragment.BaseFragment;

import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends BaseFragment {
    Button signOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inif();
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        signOut = v.findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserActivity.class));
                saveData();
                getActivity().finish();
            }
        });

        return v;
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userPhone", "");
        editor.apply();
    }

}
