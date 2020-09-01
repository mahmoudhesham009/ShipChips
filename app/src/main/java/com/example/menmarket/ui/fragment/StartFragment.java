package com.example.menmarket.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.menmarket.Helper;
import com.example.menmarket.R;

public class StartFragment extends BaseFragment {
    Button logIn;
    Button signUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inif();
        View v= inflater.inflate(R.layout.fragment_start,container,false);
        logIn=v.findViewById(R.id.loginButton_start);
        signUp=v.findViewById(R.id.signupButton_start);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.replaceFragment(getActivity().getSupportFragmentManager(),R.id.user_fragment_viewer,new LogInFragment(),R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.replaceFragment(getActivity().getSupportFragmentManager(),R.id.user_fragment_viewer,new SignUpFragment(),R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            }
        });



    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }
}
